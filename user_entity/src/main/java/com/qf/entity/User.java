package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @version 1.0
 * @user 10395
 * @date 2019/6/29 16:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String account;
    private String password;
    private String username;
    private String email;

    @TableField(exist = false)
    private Integer code;
}
