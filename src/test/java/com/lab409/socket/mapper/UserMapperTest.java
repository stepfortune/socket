package com.lab409.socket.mapper;

import com.lab409.socket.demoServer.mapper.UserMapper;
import com.lab409.socket.demoServer.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;
    @Test
    public void insert() {
        User user = new User(Long.valueOf(4),"public","123456");
    }
}
