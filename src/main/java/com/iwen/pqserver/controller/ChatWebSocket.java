package com.iwen.pqserver.controller;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.iwen.pqserver.api.CommonResult;
import com.iwen.pqserver.utils.JwtTokenUtil;
import com.iwen.pqserver.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * 非群聊
 * 对方不在线，消息最多保存在redis中2周
 * 规则：
 *  10分钟之内保存在同一个zset
 *
 * 发送的规则：
 * 1. 对方在线，直接转发
 * 2. 对方不在线，存入redis2周
 *格式
 * {"fromUserId":"","contentText":"","toUserId":""}
 *
 *
 * @author i wen
 */
@ServerEndpoint(value = "/pqserver/{token}")
@Slf4j
@Component
@Api(tags = "ChatWebSocket", description = "webSocket聊天转发")
public class ChatWebSocket {

    /*
    * 非群聊前缀
    * */
    private static String notGroupChat;

    private static RedisUtil redisUtil;

    private static JwtTokenUtil jwtTokenUtil;
    /*
       2 周
     */
    private static final int DEFAULT_EXPIRE = 60*24*14;

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, ChatWebSocket> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userId
     */
    private String userId = "";

    public static void setRedisUtil(RedisUtil redisUtil) {
        ChatWebSocket.redisUtil = redisUtil;
    }

    public static void setNotGroupChat(String notGroupChat) {
        ChatWebSocket.notGroupChat = notGroupChat;
    }

    public static void setJwtTokenUtil(com.iwen.pqserver.utils.JwtTokenUtil jwtTokenUtil) {
        ChatWebSocket.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    @ApiOperation(value = "初始化连接时调用onOpen")
    public void onOpen(Session session, @PathParam("token") String token) {
        this.session = session;



        Claims claims = jwtTokenUtil.getClaimsFromToken(token);
        if (claims == null) {
            throw new IllegalArgumentException("token不正确");
        }
        this.userId = claims.getId();

        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            webSocketMap.put(userId, this);
        }else {
            webSocketMap.put(userId, this);
        }

        log.info("用户连接: {}", userId);


    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    @ApiOperation(value = "关闭连接时调用onClose")
    public void onClose() {

        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
        }

        log.error("关闭连接:{}",userId);

    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    @ApiOperation(value = "转发消息时调用onMessage")
    public void onMessage(String message, Session session) {
        log.info("用户消息:"+userId+",报文:"+message);
        if ("ping".equals(message)) {
            try {
                webSocketMap.get(userId).sendMessage("pong");
                log.info("发送pong成功，{}",userId);
            } catch (IOException e) {
                log.error("发送pong失败，{}",userId);
            }
            return;
        }

        //好友申请


        if(!StringUtils.isEmpty(message)) {
            try {
                //解析发送的报文
                JSONObject jsonObject = JSONUtil.parseObj(message);
                long sendTime = System.currentTimeMillis();

                //追加发送人(防止串改)
                jsonObject.put("fromUserId", this.userId);
                jsonObject.put("sendTime", String.valueOf(sendTime));

                String toUserId = (String) jsonObject.get("toUserId");

                //传送给对应toUserId用户的websocket
                List<JSONObject> lj = new ArrayList<>();
                lj.add(jsonObject);
                String msg = JSONUtil.toJsonStr(jsonObject);
                String sendMsg = JSONUtil.toJsonStr(CommonResult.success(lj));
                log.error("msg: {}" , msg);
                if (!StringUtils.isEmpty(toUserId) && webSocketMap.containsKey(toUserId)) {
                    webSocketMap.get(toUserId).sendMessage(sendMsg);
                } else {
                    log.error("请求的userId:" + toUserId + "不在该服务器上");
                    //否则不在这个服务器上，发送到redis

                    redisUtil.zSetAdd(notGroupChat +":"+ toUserId, sendTime, msg);
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
    @ApiOperation(value = "发生错误时调用onError")
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.userId + ",原因:" + error.getMessage());

    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    public boolean sendFriendApply(List<Map<String, Object>> msg,String friendId) {
        if (webSocketMap.containsKey(friendId)) {
            ChatWebSocket socket = webSocketMap.get(friendId);
            try {
                socket.sendMessage(JSONUtil.toJsonStr(CommonResult.success(msg)));
            } catch (IOException e) {
                log.warn("添加好友失败：对方可能已经下线");
            }

            return true;
        }
        return false;
    }


    public boolean sendAgreeFriendApply(String friendId) {
        if (webSocketMap.containsKey(friendId)) {
            ChatWebSocket socket = webSocketMap.get(friendId);

            try {
                socket.sendMessage("agreedFriendApply");
            } catch (IOException e) {
                log.warn("对方可能已经下线");
            }

            return true;
        }
        return false;
    }


}
