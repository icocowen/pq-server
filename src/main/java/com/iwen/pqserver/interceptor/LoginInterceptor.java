package com.iwen.pqserver.interceptor;

import cn.hutool.json.JSONUtil;
import com.iwen.pqserver.api.CommonResult;
import com.iwen.pqserver.utils.Excludes;
import com.iwen.pqserver.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 除了登录的映射不需要，验证token，其他都需要持有合法的token
 *
 * @author i wen
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);


    @Autowired
    private Excludes excludes;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        int idx = url.indexOf(".");
        String sub = url;
        if (idx > 0) {
            LOGGER.info("成功过滤.以后的字符");
            sub = url.substring(0, idx);
        }

        if (!excludes.getPaths().contains(sub)) {
            String token = request.getHeader(tokenHeader);

//            String token2 = request.getHeader("Sec-WebSocket-Protocol");
//
//            if (StringUtils.isEmpty(token)) {
//                token = token2;
//            }

            Claims claims = jwtTokenUtil.getClaimsFromToken(token);

            PrintWriter writer = null;

            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=UTF-8");

            if (StringUtils.isEmpty(token) || claims == null) {
                //提示未登录
                LOGGER.info("token为空");
                CommonResult<Object> failed = CommonResult.validateFailed("账号未登录");
                writer = response.getWriter();

                writer.println(JSONUtil.toJsonStr(failed));
                return false;
            }


            //验证是否过期

            boolean tokenExpired = jwtTokenUtil.isTokenExpired(token);
            if (tokenExpired) {
                //返回过期提示，重新登录
                LOGGER.info("token过期");

                CommonResult<Object> failed = CommonResult.validateFailed("账号登录过期，重新登录");
                writer = response.getWriter();
                writer.println(JSONUtil.toJsonStr(failed));
                return false;
            }
            Claims user = jwtTokenUtil.getClaimsFromToken(token);
            request.setAttribute("user", user.getSubject());
            request.setAttribute("uid", user.getId());
        }

        return true;
    }

}
