package com.qf.service;

public interface IMailService {
    void sendCode(String email,int code);
    void forgetPassword(String email,String account);
}
