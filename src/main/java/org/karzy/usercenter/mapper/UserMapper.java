package org.karzy.usercenter.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.karzy.usercenter.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author refuse
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-10-06 14:36:26
* @Entity org.karzy.usercenter.model.User
*/
public interface UserMapper extends BaseMapper<User> {

}




