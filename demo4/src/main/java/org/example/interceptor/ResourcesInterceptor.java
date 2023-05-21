package org.example.interceptor;

import org.example.pojo.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResourcesInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler)throws Exception{
        User user=(User)request.getSession().getAttribute("USER_SESSION");
        //如果用户是已登入状态,放行
        if(user!=null){
            return true;
        }
        //获取请求路径
        String url=request.getRequestURI();
        //用户登入的相关请求,放行
        if(url.indexOf("login")>=0 || url.indexOf(".jpg")>=0)
            return true;
        //其他情况都直接跳转到登入页面
        request.setAttribute("msg","您还没有登入,请先登入!");
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        return false;
    }
}
