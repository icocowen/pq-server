package com.iwen.pqserver.config;

import com.iwen.pqserver.controller.ChatGroupWebSocket;
import com.iwen.pqserver.controller.ChatWebSocket;
import com.iwen.pqserver.service.GroupsService;
import com.iwen.pqserver.service.UserService;
import com.iwen.pqserver.utils.JwtTokenUtil;
import com.iwen.pqserver.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {

    @Value("${pqchat.not-group-chat-prefix}")
    private String notGroupChatPrefix;

    @Value("${pqchat.group-chat-prefix}")
    private String groupChatPrefix;

    @Value("${pqchat.chat-group-menbers}")
    private String chatGroupMenbers;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private UserService userService;

    @Autowired
    private GroupsService groupsService;

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Autowired
    public void redisUtil(RedisUtil redisUtil) {
        //单人
        ChatWebSocket.setNotGroupChat(notGroupChatPrefix);
        ChatWebSocket.setRedisUtil(redisUtil);
        ChatWebSocket.setJwtTokenUtil(jwtTokenUtil);
        //群组
        ChatGroupWebSocket.setGroupChat(groupChatPrefix);
        ChatGroupWebSocket.setJwtTokenUtil(jwtTokenUtil);
        ChatGroupWebSocket.setRedisUtil(redisUtil);
        ChatGroupWebSocket.setUserService(userService);
        ChatGroupWebSocket.setGroupsService(groupsService);
        ChatGroupWebSocket.setChatGroupMenbers(chatGroupMenbers);
    }





}
