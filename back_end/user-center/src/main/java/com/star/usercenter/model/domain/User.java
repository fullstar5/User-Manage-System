package com.star.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * user_table
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * user id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * user name
     */
    private String username;

    /**
     * user account
     */
    private String userAccount;

    /**
     * user avatar
     */
    private String avatarUrl;

    /**
     * user gender
     */
    private Integer gender;

    /**
     * user password
     */
    private String userPassword;

    /**
     * user phone number
     */
    private String phone;

    /**
     * user email
     */
    private String email;

    /**
     * 0 - normal
1 - ...
     */
    private Integer userStatus;

    /**
     * first create time
     */
    private Date createTime;

    /**
     * last update time
     */
    private Date updateTime;

    /**
     * 0 - not delete
     * 1 - deleted
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}