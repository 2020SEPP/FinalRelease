package com.pclogo.demo.controller;

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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class UserControllerTest {
    @Autowired
    UserController userController;

    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        System.out.println("set up");
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    void tearDown() {
        System.out.println("tear down");
    }

    @Test
    void loginByPhone() {
        userController.loginByPhone("15044341612", "111111");
    }

    @Test
    void loginByName() {
        userController.loginByName("xjh", "111111");
    }

    @Test
    void register() {
        userController.register("xjh", "15044341612", "111111");
    }

    @Test
    void searchByPhone() {
        userController.searchByPhone("15044341612");
    }

    @Test
    void searchByName() {
        userController.searchByName("xjh");
        userController.searchByName("-1");

    }

    @Test
    void loadAvatar() {
        userController.loadAvatar("?", 2);
        userController.loadAvatar("?", -1);

    }

    @Test
    void getFriend() {
        userController.getFriend(2);
        userController.getFriend(-1);

    }

    @Test
    void updateInfo() {
        userController.updateInfo(2, "xjh", "111111");
        userController.updateInfo(-1, "xjh", "111111");

    }

    @Test
    void updateAvatar() {
        userController.updateAvatar(2, "?");
        userController.updateAvatar(-1, "?");

    }

    @Test
    void sendInvite() {
        userController.sendInvite(2, 1);
        userController.sendInvite(2, -1);
        userController.sendInvite(-1, 2);
//        userController.sendInvite(2, -1);

    }

    @Test
    void checkInvite() {
        userController.checkInvite(1);
        userController.checkInvite(2);

        userController.checkInvite(-1);
    }

    @Test
    void acceptInvite() {
        userController.acceptInvite(1, 2, 1);
        userController.acceptInvite(1, 2, 0);
        userController.acceptInvite(-1, 2, 1);
        userController.acceptInvite(-1, 2, 0);
        userController.acceptInvite(2, -1, 1);
        userController.acceptInvite(2, -1, 0);

    }
}