package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.User;
import com.qf.service.IMailService;
import com.qf.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @version 1.0
 * @user 10395
 * @date 2019/6/29 14:10
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Reference
    private IMailService mailService;

    @Reference
    private IUserService userService;

    @RequestMapping("/sendCode")
    @ResponseBody
    public String sendCode(@RequestParam(name = "email") String email, HttpSession session){
        int code = (int)(1000+Math.random()*(9999-1000+1));
        session.setAttribute("code",code);
        mailService.sendCode(email,code);
        return "index";
    }

    //跳到注册页面
    @RequestMapping("/toRegister")
    public String toregister(){
        return "register";
    }

    //跳到登录页面
    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }

    //注册功能
    @RequestMapping("/register")
    public String register(User user,HttpSession session){
        System.out.println(user);
        System.out.println(user.getCode());
        Integer code = (Integer) session.getAttribute("code");
        if (code.equals(user.getCode())){
            userService.insert(user);
            return "registerOk";
        }
        return "registerSb";
    }


    //登录功能
    @RequestMapping("/login")
    public String login(String account,String password){
        boolean login = userService.login(account, password);
        if (login == true){
            return "homePage";
        }
        return "login";
    }


    //跳到找回密码页面
    @RequestMapping("/toBack")
    public String toBack(){
        return "forget";
    }

    //向邮箱发送找回邮件
    @RequestMapping("/forget")
    public String forget(String account){
        User user = userService.getUserByAccout(account);
        String email = user.getEmail();
        mailService.forgetPassword(account,email);
        return "prompt";
    }

    //重置密码页面   ?传值account
    @RequestMapping("/forgetPassword")
    public String forgetPassword(@RequestParam("account") String account,Model model){
        System.out.println(account);
        model.addAttribute("account",account);
        return "changePassword";
    }

    //设置密码
    @RequestMapping("/setPassword")
    public String setPassword(String account,String password){
        User user = userService.getUserByAccout(account);
        Integer id = user.getId();
        if (password!=""&&password!=null){
            user.setPassword(password);
        }
        user.setId(id);
        userService.updataPasswordById(user);

        return "login";
    }
}
