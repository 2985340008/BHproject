package com.bhsoftware.projectserver.interceptor;


import com.bhsoftware.projectserver.entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse ,Object handler) throws Exception{
        HttpSession session = httpServletRequest.getSession();
        String contextPath = session.getServletContext().getContextPath();
        String[] requireAuthPages = new String[]{
                "index",
        };

        String uri = httpServletRequest.getRequestURI();

        String page = StringUtils.remove(uri, contextPath+"/");

        if (begingWith(page, requireAuthPages)){
            User user = (User) session.getAttribute("user");
            if (user==null){
                httpServletResponse.sendRedirect("login");
                return false;
            }
        }
        return true;
    }

    public boolean begingWith(String page, String[] requiredAuthPages) {
        boolean result = false;
        for (String requiredAuthPage : requiredAuthPages) {
            if (StringUtils.startsWith(page, requiredAuthPage)){
                result = true;
                break;
            }
        }
        return result;
    }
}
