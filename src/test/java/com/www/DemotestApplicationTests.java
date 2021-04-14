package com.www;

import com.www.entity.User;
import com.www.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemotestApplicationTests {

    @Autowired
    UserMapper userMapper;

    //修改
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(7L);
        user.setName("汤姆不利多");
        userMapper.updateById(user);
    }


    //添加
    @Test
    public void insert() {
        User user = new User();
        user.setName("李二爷");
        user.setAge(24);
        user.setEmail("1780910546@qq.com");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    //查询
    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

}
