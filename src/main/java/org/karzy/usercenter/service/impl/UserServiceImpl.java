package org.karzy.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.karzy.usercenter.model.User;
import org.karzy.usercenter.service.UserService;
import org.karzy.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author refuse
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-10-06 14:36:26
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}




