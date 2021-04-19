package com.www;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.www.entity.User;
import com.www.mapper.UserMapper;
import net.sf.jsqlparser.util.deparser.UpsertDeParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class DemotestApplicationTests {

    @Autowired
    UserMapper userMapper;


    //多个id批量查询
    @Test
    public void select1() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(users);
    }

    //简单的条件查询
    @Test
    public void select2() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Jack");
        map.put("age", 20);
        List<User> users = userMapper.selectByMap(map);
        System.out.println(users);
    }

    //分页查询
    @Test
    public void selectPage() {
        Page<User> page = new Page<>(1, 3);
        Page<User> userPage = userMapper.selectPage(page, null);
        //返回对象得到分页所有数据
        long pages = userPage.getPages(); //总分页
        long current = userPage.getCurrent(); //当前分页
        List<User> records = userPage.getRecords(); //当前数据集合
        long total = userPage.getTotal(); //总记录数
        boolean hasNext = userPage.hasNext(); //下一页
        boolean hasPrevious = userPage.hasPrevious(); //上一页

        System.out.println("pages="+pages);
        System.out.println("current="+current);
        System.out.println("records="+records);
        System.out.println("total="+total);
        System.out.println("hasNext="+hasNext);
        System.out.println("hasPrevious="+hasPrevious);
    }

    //测试乐观锁
    @Test
    public void testOptimisticLock() {
        //根据id查询
        User user = userMapper.selectById(8L);
        user.setName("不开心");
        //修改
        userMapper.updateById(user);
    }

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
        user.setName("王五");
        user.setAge(24);
        user.setEmail("645@qq.com");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    //查询所有
    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }


    //根据id删除
    @Test
    public void testDeleteById() {
        int result = userMapper.deleteById(10L);
        System.out.println("result="+result);
    }

    //批量删除
    @Test
    public void testDeleteBatchIds() {
        int result = userMapper.deleteBatchIds(Arrays.asList(7, 8));
        System.out.println("result="+result);
    }

    //简单条件删除
    @Test
    public void testDeleteByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "李四");
        map.put("age", 24);
        int result = userMapper.deleteByMap(map);
        System.out.println("result="+result);
    }


    //mp的复杂查询
    @Test
    public void testSelect() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //ge、gt、le、lt
//        queryWrapper.ge("age", 12);
        //eq、ne
        queryWrapper.eq("name", "Tom");

        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }
}
