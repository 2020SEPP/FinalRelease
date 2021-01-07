package com.pclogo.demo.serviceImpl;

import com.pclogo.demo.service.UserService;
import com.pclogo.demo.utils.UserUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class UserServiceImplTest {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;
    private Random random = new Random();
    @BeforeEach
    void setUp() {
        System.out.println("set up");
        userService.testing=true;
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    void tearDown() {
        System.out.println("tear down");
    }

    @Test
    void loginByPhone() {
        userService.loginByPhone("15044341612", "111111");
        userService.loginByPhone("1", "1");
    }

    @Test
    void loginByName() {
        userService.loginByName("xjh", "111111");
        userService.loginByName("-1", "1");
    }

    @Test
    void register() {
        userService.register("xjh", "1", "1");
        userService.register("-1", "10"+random.nextInt(999999999), "1");
        userService.register("ayc"+random.nextInt(999999999), "15"+random.nextInt(999999999), "111111");
    }

    @Test
    void searchByName() {
        userService.searchByName("xjh");
        userService.searchByName("?");
    }

    @Test
    void setAvatar() {
        userService.setAvatar("?", 2);
        userService.setAvatar("?", -1);
    }

    @Test
    void getFriend() {
        userService.getFriend(-1);
        userService.getFriend(2);
        userService.getFriend(1);
    }

    @Test
    void updateInfo() {
        userService.updateInfo(2, "xjh", "111111");
        userService.updateInfo(-1, "xjh", "111111");
    }

    @Test
    void updateAvatar() {
        userService.updateAvatar(2, "666");
        userService.updateAvatar(-1, "666");
    }

    @Test
    void sendInvite() {
        userService.sendInvite(2, 1);
        userService.sendInvite(-1, 1);
        userService.sendInvite(2, -1);
    }

    @Test
    void checkInvite() {
        userService.checkInvite(2);
        userService.checkInvite(-1);
    }

    @Test
    void setFriend() {
        userService.setFriend(1, 2);
        userService.setFriend(-1, 1);
        userService.setFriend(1, -1);
    }

    @Test
    void acceptInvite() {
        userService.acceptInvite(1, 2, 1);
        userService.acceptInvite(1, 2, 0);
        userService.acceptInvite(-1, 2, 1);
        userService.acceptInvite(-1, 2, 0);
        userService.acceptInvite(1, -1, 1);
        userService.acceptInvite(1, -1, 0);
    }

    @Test
    void searchByPhone() {
        userService.searchByPhone("15044341612");
        userService.searchByPhone("-1");
    }

    @Test
    void setToken()
    {
        UserUtil userUtil = null;
        userService.setToken(userUtil);
    }
}