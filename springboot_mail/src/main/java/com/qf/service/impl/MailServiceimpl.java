package com.qf.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @version 1.0
 * @user 10395
 * @date 2019/6/29 11:45
 */
@Service
public class MailServiceimpl implements IMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    //登录发送验证码
    @Override
    public void sendCode(String email,int code){
        //创建一封邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        //创建一个spring提供的邮件帮助对象
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            //设置发送方
            mimeMessageHelper.setFrom("t1039546957@sina.com");

            //设置接收方
            mimeMessageHelper.addTo(email,"用户验证");

            //设置标题
            mimeMessageHelper.setSubject("邮箱验证");

            //设置内容
            mimeMessage.setText("验证码:"+code);

            //设置时间
            mimeMessageHelper.setSentDate(new Date());

            //发送邮件
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void forgetPassword(String account,String email) {

        MimeMessage mimeMessage =javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            //设置发送方
            mimeMessageHelper.setFrom("t1039546957@sina.com");

            //设置接收方
            mimeMessageHelper.addTo(email,"重置密码");

            //设置标题
            mimeMessageHelper.setSubject("重置密码");

            //设置内容
            mimeMessage.setText("localhost:8080/user/setPassword?account="+account);

            //设置时间
            mimeMessageHelper.setSentDate(new Date());

            //发送邮件
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
