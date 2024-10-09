package org.karzy.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.karzy.usercenter.common.ErrorCode;
import org.karzy.usercenter.exception.BusinessException;
import org.karzy.usercenter.model.domain.User;
import org.karzy.usercenter.service.UserService;
import org.karzy.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.karzy.usercenter.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author karzy
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-10-06 14:36:26
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    private static final String SALT = "karzy";

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        if (StringUtils.isAllBlank(userPassword, userAccount, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号长度过短");
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度过短");
        }

        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入密码不一致");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_account", userAccount);
        long count = this.count(wrapper);
        System.out.println(count);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号已注册");
        }
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/? @#Y‰.()-+|}【】':]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号不能包含特殊字符");
        }
        String newPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(newPassword);
        boolean saved = this.save(user);
        if (!saved) {
            throw new RuntimeException("账号注册失败,请重试");
        }
        return user.getId();
    }

    @Override
    public User dologin(String userAccount, String userPassword, HttpServletRequest request) {
        if (StringUtils.isAllBlank(userPassword, userAccount)) {
            log.info("输入的密码或账号为空");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "输入的密码或账号为空");
        }
        if (userAccount.length() < 4) {
            log.info("账号长度小于4");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号长度小于4");

        }
        if (userPassword.length() < 8) {
            log.info("密码长度小于8");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度小于8");

        }

        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/? @#Y‰.()-+|}【】':]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            log.info("用户名包含特殊字符");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名包含特殊字符");

        }
//        String newPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        String newUserPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_account", userAccount)
                .eq("user_password", newUserPassword);
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            log.info("用户名或密码错误");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名或密码错误");

        }
        User safetyUser = getSafetyUser(user);
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);
        return safetyUser;
    }

    @Override
    public User getSafetyUser(User originalUser) {
        if (originalUser == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户脱敏失败");

        }
        User safetyUser = new User();
        safetyUser.setId(originalUser.getId());
        safetyUser.setUsername(originalUser.getUsername());
        safetyUser.setUserAccount(originalUser.getUserAccount());
        safetyUser.setAvatarUrl(originalUser.getAvatarUrl());
        safetyUser.setGender(originalUser.getGender());
        safetyUser.setPhone(originalUser.getPhone());
        safetyUser.setUserRole(originalUser.getUserRole());
        safetyUser.setEmail(originalUser.getEmail());
        safetyUser.setUserStatus(originalUser.getUserStatus());
        safetyUser.setCreateTime(originalUser.getCreateTime());
        return safetyUser;
    }

    @Override
    public void userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATE);
    }
}




