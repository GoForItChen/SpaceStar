package com.chen.star.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.persistence.*;

/**
 * 前台用户
 *
 * @author harvey
 * @date 2021/08/24
 **/
@Entity
@Data
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 20)
    @TableField(value = "accountname")
    private String accountname;

    @Column(length = 20)
    @TableField(value = "phone")
    private String phone;

    @Column(length = 20)
    @TableField(value = "email")
    private String email;

    @TableField(value = "wechat")
    private String wechat;
}
