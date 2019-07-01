package com.qf.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qf.dao.UserMapper;
import com.qf.entity.User;
import com.qf.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @user 10395
 * @date 2019/6/29 15:55
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public boolean login(String account, String password) {
        Map map = new HashMap();
        map.put("account",account);
        map.put("password",password);
        List list = userMapper.selectByMap(map);
        System.out.println(list);
        if (!list.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public User getUserByAccout(String account) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("account",account));
    }

    @Override
    public int updataPasswordById(User user) {
        userMapper.updateById(user);
        return 0;
    }


}
