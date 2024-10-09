package org.karzy.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import org.karzy.usercenter.model.domain.User;

/**
 * @author refuse
 * @description 针对表【user】的数据库操作Service
 * @createDate 2024-10-06 14:36:26
 */
public interface UserService extends IService<User> {
    /**
     *
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 用户校验密码
     * @return 新用户的id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * @param userAccount  用户账号
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    User dologin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     *
     * @param originalUser 为脱敏的用户数据
     * @return 脱敏后的用户数据
     */
    User getSafetyUser(User originalUser);

    void userLogout(HttpServletRequest request);
}
