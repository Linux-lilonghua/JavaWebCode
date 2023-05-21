package org.example.controller;

import org.example.pojo.User;
import org.example.service.UserService;
import org.example.utils.RandomValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户登入Controller
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    /*
    用户登入
     */
    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }
    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request, String checkcode){
        //RandomValidateCode.RANDOMCODEKEY为工具类定义的变量，即验证码图片内正确识别后的数据
        String vcode = (String)
                request.getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY);
        System.out.println(vcode);
        try{
            User u=userService.login(user);
            /*
            用户名和密码是否查询用户信息
            是：跳转到后台首页
            否：跳转到登入页面
             */
                if (u != null) {
                    if (checkcode.equals(vcode)) {
                        request.getSession().setAttribute("USER_SESSION", u);
                        return "main";
                    }
                    else {
                        request.getSession().setAttribute("msg", "验证码错误！");
                        return "redirect:tologin";
                    }
                }
                else {
                    request.getSession().setAttribute("msg", "用户名或密码错误!");
                    return "redirect:tologin";
                }
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("msg","系统出错啦！");
            return "redirect:tologin";
        }
    }
    @RequestMapping("/logout")
    public String  logout(HttpSession session){
        //清除session
        session.invalidate();
        return "redirect:tologin";
    }

    @RequestMapping("/checkCode.login")
    public void checkCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");
        // 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);// 输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
