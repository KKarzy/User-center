package org.karzy.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String userAccount;

    /**
     *
     */
    private String avatarUrl;

    /**
     *
     */
    private Integer gender;

    /**
     *
     */
    private String userPassword;

    /**
     *
     */
    private String phone;

    /**
     *
     */
    private String email;

    /**
     * 状态 0 正常
     */
    private Integer userStatus;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;
    /**
     * 0 - 默认用户  1 - 管理员
     */
    private Integer userRole;

    /**
     *
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}