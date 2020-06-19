package com.iwen.pqserver.controller;

import com.iwen.pqserver.api.CommonResult;
import com.iwen.pqserver.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户登录后相关的操作
 *
 * @author i wen
 */
@RestController
@Api(tags = "PqUserController", description = "登录后用户相关操作" )
@RequestMapping("/user")
public class PqUserController {
    public static final Logger LOGGER = LoggerFactory.getLogger(PqUserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取好友列表")
    @GetMapping("/friends")
    public CommonResult friends(HttpServletRequest request, String time) {
        Map<String, Object> friends = userService.getUserFriends((String) request.getAttribute("user"), time);
        return CommonResult.success(friends);
    }


    @ApiOperation(value = "获取群列表")
    @GetMapping("/groups")
    public CommonResult groups(HttpServletRequest request, String time) {
        Map<String, Object> groups = userService.getUserGroups((String) request.getAttribute("user"), time);
        return CommonResult.success(groups);
    }


    @ApiOperation(value = "获取群友列表")
    @GetMapping("/groups/members")
    public CommonResult groupsMembers(HttpServletRequest request, String gid) {
        List<Map<String, String>> groupMembers = userService.getUserGroupMembers((String) request.getAttribute("uid"), gid);
        return CommonResult.success(groupMembers);
    }

    /**
     * 获取最新还没接受的消息
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "获取最新消息")
    @GetMapping("/hot/message")
    public CommonResult hotMessage(HttpServletRequest request) {
        Set<Object> hotMessage = userService.getUnrecvMessage((String) request.getAttribute("uid"));
        return CommonResult.success(hotMessage);
    }


    /**
     * 返回格式
     * "{\"fromUserId\":\"1\",
     * \"contentText\":\"hello websocket\",
     * \\"toGroup\":\"1\",
     * \"sendTime\":\"1591255488930\"}"
     *
     * @param request
     * @param gid
     * @return
     */
    @ApiOperation(value = "获取最新组消息")
    @GetMapping("/hot/group/message")
    public CommonResult hotGroupMessage(HttpServletRequest request, String gid) {
        Set<Object> hotGroupMessage = userService.getUnrecvGroupMessage(gid, (String) request.getAttribute("uid"));
        return CommonResult.success(hotGroupMessage);
    }

    @ApiOperation(value = "查找好友")
    @GetMapping("/search")
    public CommonResult search(String key) {
        Map<String, Object> friends = userService.search(key);
        return CommonResult.success(friends);
    }

    @ApiOperation(value = "添加好友")
    @GetMapping("/apply/friend")
    public CommonResult applyFriend(HttpServletRequest request, String friendId, String sayHello) {
        userService.applyFriend( (String) request.getAttribute("uid"), friendId, sayHello);
        return CommonResult.success("申请成功");
    }

    @ApiOperation(value = "获取未处理的好友申请")
    @GetMapping("/apply/friend/list")
    public CommonResult applyFriendList(HttpServletRequest request) {
        List<Map<String, Object>> mapList = userService.applyFriendList((String) request.getAttribute("uid"));
        return CommonResult.success(mapList);
    }


    @ApiOperation(value = "同意好友申请")
    @GetMapping("/agree/friend/apply")
    public CommonResult agreeFriendApply(HttpServletRequest request, String friendId) {
        userService.agreeFriendApply((String) request.getAttribute("uid"), friendId);
        return CommonResult.success("同意");
    }
}
