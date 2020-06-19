package com.iwen.pqserver.controller;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.iwen.pqserver.service.GroupsService;
import com.iwen.pqserver.service.UserService;
import com.iwen.pqserver.utils.JwtTokenUtil;
import com.iwen.pqserver.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 *格式
 * {"fromUserId":"","contentText":"","toUserId":""}
 * {"contentText":"hello websocket"}
 *
 *
 * @author i wen
 */
@ServerEndpoint(value = "/pqserver/group/{groupId}/{token}" )
@Slf4j
@Component
@Api(tags = "ChatGroupWebSocket", description = "webSocket群聊天转发")
public class ChatGroupWebSocket {

    /*
     * 群聊前缀
     * */
    private static String groupChat;

    private static RedisUtil redisUtil;

    private static JwtTokenUtil jwtTokenUtil;

    private static UserService userService;

    private static GroupsService groupsService;

    private static String chatGroupMenbers;



    public static void setUserService(UserService userService) {
        ChatGroupWebSocket.userService = userService;
    }


    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, ChatGroupWebSocket>> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userId
     */
    private String userId = "";

    private String groupId = "";

    private  final Set<String> memberSet = new HashSet<>();

    private static final int DEFAULT_EXPIRE = 60* 60 * 24 * 14;
    private static final String MEMBERS = "members";


    public static void setRedisUtil(RedisUtil redisUtil) {
        ChatGroupWebSocket.redisUtil = redisUtil;
    }

    public static void setGroupChat(String groupChat) {
        ChatGroupWebSocket.groupChat = groupChat;
    }

    public static void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        ChatGroupWebSocket.jwtTokenUtil = jwtTokenUtil;
    }

    public static void setGroupsService(GroupsService groupsService) {
        ChatGroupWebSocket.groupsService = groupsService;
    }

    public static void setChatGroupMenbers(String chatGroupMenbers) {
        ChatGroupWebSocket.chatGroupMenbers = chatGroupMenbers;
    }


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    @ApiOperation(value = "初始化连接时调用onOpen")
    public void onOpen(Session session, @PathParam("groupId") String groupId,@PathParam("token") String token) {
        this.session = session;
        this.groupId = groupId;


        Claims claims = jwtTokenUtil.getClaimsFromToken(token);
        if (claims == null) {
            throw new IllegalArgumentException("token不正确");
        }
        Map<String, Object> groupsInfo = userService.getUserGroups(claims.getSubject(), "0");
        this.userId = claims.getId();

        List<Map<String, String>> userGroups = (List<Map<String, String>>) groupsInfo.get("groups");
        boolean[] flags = {false};
        userGroups.forEach(d -> {
            if (String.valueOf(d.get("id")).equals(groupId)) {
                flags[0] = true;
                return;
            }
        });
        if (!flags[0]) {
            throw new IllegalArgumentException("不能发送信息到没加入的组内");
        }


        if (webSocketMap.containsKey(groupId)) {
            webSocketMap.get(groupId).put(userId, this);
        }else {
            ConcurrentHashMap<String, ChatGroupWebSocket> members = new ConcurrentHashMap<>();
            members.put(userId, this);
            webSocketMap.put(groupId, members);
        }

        log.info("组连接: {}", groupId);


    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    @ApiOperation(value = "关闭连接时调用onClose")
    public void onClose() {

        if(webSocketMap.containsKey(groupId)){
            webSocketMap.get(groupId).remove(userId);

            if (webSocketMap.get(groupId).isEmpty()) {
                webSocketMap.remove(groupId);
            }
        }



    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    @ApiOperation(value = "转发消息时调用onMessage")
    public void onMessage(String message, Session session) {
        log.info("组用户消息:"+groupId+",报文:"+message);

        if(!StringUtils.isEmpty(message)) {
            try {
                //解析发送的报文
                JSONObject jsonObject = JSONUtil.parseObj(message);
                long sendTime = System.currentTimeMillis();

                //追加发送人(防止串改)
                jsonObject.put("fromUserId", this.userId);
                jsonObject.put("sendTime", String.valueOf(sendTime));
                jsonObject.put("toGroup", String.valueOf(this.groupId));


                //传送给对应toUserId用户的websocket
                String msg = JSONUtil.toJsonStr(jsonObject);
                Collection<ChatGroupWebSocket> groupSet = null;
                if (webSocketMap.containsKey(groupId)) {
                    groupSet = webSocketMap.get(groupId).values();
                    for (ChatGroupWebSocket chatGroup : groupSet) {
                        if (chatGroup != this) {
                            chatGroup.sendMessage(msg);
                        }
                    }
                }



                //从redis中找出组成员
                String members = "[]";
                JSONArray objects = null;
                if (!redisUtil.hasKey(chatGroupMenbers + ":" + groupId)) {
                    members = groupsService.getGroupMembers(Integer.valueOf(groupId));
                    redisUtil.set(chatGroupMenbers + ":" + groupId, members, DEFAULT_EXPIRE);
                }else {
                    members = redisUtil.get(chatGroupMenbers + ":" + groupId).toString();
                }
                if (members.length() != 2) {
                    objects = JSONUtil.parseArray(members);
                }
                if (objects != null) {
                    for (Object obj : objects) {
                        if (!webSocketMap.get(groupId).containsKey(obj.toString())) {
                            //拿了之后删除
                            redisUtil.sSet(groupChat +":"+ groupId + ":" +MEMBERS, obj.toString());
                            redisUtil.zSetAdd( groupChat +":"+ groupId, sendTime, msg);
                        }
                    }
                }

            } catch (Exception e) {
                log.error("用户消息解析不正确:{}", e.getMessage());
            }
        }
    }


    /**
     * @param session
     * @param error
     */
    @OnError
    @ApiOperation(value = "组聊天发生错误时调用onError")
    public void onError(Session session, Throwable error) throws IOException {
        log.error("用户组聊天错误:" + this.userId + ",原因:" + error.getMessage());
        session.getBasicRemote().sendText(error.getMessage());
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


}
