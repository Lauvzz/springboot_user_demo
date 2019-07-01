package com.qf.service;


import com.qf.entity.User;

public interface IUserService {
    int insert(User user);
    boolean login(String account,String password);
    User getUserByAccout(String account);
    int updataPasswordById(User user);
}
