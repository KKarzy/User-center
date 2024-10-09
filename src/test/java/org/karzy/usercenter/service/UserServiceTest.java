package org.karzy.usercenter.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.karzy.usercenter.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    void testAddUser(){
        User user = new User();
        user.setUsername("test05");
        user.setUserAccount("123");
        user.setAvatarUrl("https://pic.cnblogs.com/face/1132386/20201102145903.png");
        user.setGender(0);
        user.setUserPassword("123");
        user.setPhone("456");
        user.setEmail("12333");
        boolean saved = userService.save(user);
        System.out.println(saved);
    }
    @Test
    void testDeleteUser(){
        boolean removed = userService.removeById(1);
        System.out.println(removed);
    }
    @Test
    void testRegister(){
        String userAccount = "1234567";
        String userPassword = "123456789";
        String checkPassword = "123456789";
        long num = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(9,num);
    }

}