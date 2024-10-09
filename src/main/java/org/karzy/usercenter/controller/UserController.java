package org.karzy.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.karzy.usercenter.common.ErrorCode;
import org.karzy.usercenter.common.Rsg;
import org.karzy.usercenter.common.RsgUtils;
import org.karzy.usercenter.exception.BusinessException;
import org.karzy.usercenter.mapper.UserMapper;
import org.karzy.usercenter.model.Request.UserRegisterRequest;
import org.karzy.usercenter.model.domain.User;
import org.karzy.usercenter.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.karzy.usercenter.constant.UserConstant.ADMIN_ROLE;
import static org.karzy.usercenter.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author karzy
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping("/register")
    public Rsg userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if (userRegisterRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAllBlank(checkPassword, userPassword, userAccount)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long userId = userService.userRegister(userAccount, userPassword, checkPassword);
        return RsgUtils.success(userId);
    }
    @PostMapping("/login")
    public Rsg userLogin(@RequestBody UserRegisterRequest userRegisterRequest, HttpServletRequest request){
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        if(StringUtils.isAllBlank(userPassword, userAccount)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.dologin(userAccount, userPassword, request);
        return RsgUtils.success(user);
    }

    @GetMapping("/search")
    public Rsg searchUsers(@RequestParam("userAccount") String userAccount, HttpServletRequest request){
        System.out.println(userAccount);
        if (!isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userAccount)){
            wrapper.like("user_account", userAccount);
        }
        List<User> userList = userService.list(wrapper).stream().map(userService::getSafetyUser).toList();
        return RsgUtils.success(userList);
    }
    @PostMapping()
    public Rsg deleteUsers(@RequestParam("id") long id, HttpServletRequest request) {
        if (!isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (id <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return RsgUtils.success(userService.removeById(id));
    }

    private boolean isAdmin(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        if (user == null){
            return false;
        }
        return user.getUserRole() == ADMIN_ROLE;
    }
    @PostMapping("/logout")
    public Rsg logout(HttpServletRequest request) {
        userService.userLogout(request);
        return RsgUtils.success();
    }
}
