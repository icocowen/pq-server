package com.iwen.pqserver.controller;

import com.iwen.pqserver.api.CommonResult;
import com.iwen.pqserver.entity.PqUser;
import com.iwen.pqserver.service.SendEmailService;
import com.iwen.pqserver.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.Map;

/**
 * @author i wen
 */
@RestController
@RequestMapping("/user")
@Api(tags = "PqUserManagementController", description = "用户管理")
public class PqUserManagementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PqUserManagementController.class);


    @Autowired
    private UserService userService;



    @Value("${jwt.tokenHeader}")
    private String tokenHeader;



    @GetMapping
    public CommonResult hello() {
        return CommonResult.success("hello");
    }

    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login")
    public CommonResult login(String email,String password) {
        Map<String, String> userInfo = userService.login(email, password);
        if (userInfo == null) {
            LOGGER.info("登录email为 {} 账号或密码不正确",email);
            return CommonResult.failed("账号或密码不正确");
        }

        LOGGER.info("登录成功,token为: {}", userInfo.get("token"));
        userInfo.put("tokenHead", tokenHeader);
        return CommonResult.success(userInfo);
    }


    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public CommonResult register(String code, String password , String nickName) {
        boolean hasUser = userService.register(code, password, nickName);
        if (! hasUser ) {
            LOGGER.info("用户注册");
            return CommonResult.failed("注册失败");
        }
        LOGGER.info("用户注册");
        return CommonResult.success("注册成功");
    }


    @ApiOperation(value = "邮箱注册码")
    @GetMapping(value = "/verify/code")
    public CommonResult verifyCode(String mail) {
       return userService.sendRegisterVerifyCode(mail);
    }

    @ApiOperation(value = "找回密码验证码")
    @GetMapping(value = "/verify/code/forget")
    public CommonResult verifyCodeForget(String mail) {
        return userService.sendForgetPasswordVerifyCode(mail);
    }


    @ApiOperation(value = "找回密码")
    @PostMapping(value = "/forget/password")
    public CommonResult forgetPassword(String code, String password ) {
        boolean canChange = userService.forgetPassword(code, password);
        if (! canChange ) {
            LOGGER.info("找回密码失败");
            return CommonResult.failed("找回密码失败");
        }
        return CommonResult.success("找回密码成功");
    }

    @ApiOperation(value = "删除好友")
    @PostMapping(value = "/delete/friend")
    public CommonResult deleteFriend(HttpServletRequest request, String targetId) {
        userService.deleteFriend((String)request.getAttribute("uid"),targetId);
        return CommonResult.success("删除成功");
    }


}
