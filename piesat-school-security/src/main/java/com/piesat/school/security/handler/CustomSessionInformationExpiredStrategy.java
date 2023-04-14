package com.piesat.school.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/04/07/9:40
 * @Description:
 */

@Component
public class CustomSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    private  static ObjectMapper objectMapper = new ObjectMapper();
    @Resource
    CustomizeAccessDeniedHandler customizeAccessDeniedHandler;
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent)
            throws IOException, ServletException {
        UserDetails userDetails= (UserDetails) sessionInformationExpiredEvent.getSessionInformation().getPrincipal();
        AccessDeniedException exception = new AccessDeniedException(
                String.format("[%s] 用户在另外一台电脑登录,您已被下线", userDetails.getUsername()));
        try {
            sessionInformationExpiredEvent.getRequest().setAttribute("toAuthentication",true);
            customizeAccessDeniedHandler.handle(sessionInformationExpiredEvent.getRequest(),sessionInformationExpiredEvent.getResponse(),exception);
        }catch (ServletException e){
            e.printStackTrace();
        }
//        Console.log("你的账号已在其他地方登录");
//
//        // 获取请求对象
//        final HttpServletResponse response = sessionInformationExpiredEvent.getResponse();
//
//        // 返回json字符串提示
//        Dict res = Dict.create().set("code", 1000).set("msg", "你的账号已在其他地方登录");
//        String contentType = ContentType.JSON.toString(CharsetUtil.CHARSET_UTF_8);
//        ServletUtil.write(response, JSONUtil.toJsonStr(res), contentType);
    }
}
