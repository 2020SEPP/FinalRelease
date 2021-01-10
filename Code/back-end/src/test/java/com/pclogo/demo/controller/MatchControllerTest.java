package com.pclogo.demo.controller;

import com.pclogo.demo.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.discovery.SelectorResolver;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class MatchControllerTest {
    @Autowired
    MatchController matchController;

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
    void createRoom() {
        matchController.createRoom(2, 0);
    }

    @Test
    void joinById() {
        matchController.joinById(1, 1001, 0);
    }

    @Test
    void joinSrand() {
        matchController.joinSrand(1, 0);
    }

    @Test
    void sendCommand() {
        matchController.sendCommand(2, 1001, "fd 100");
    }

    @Test
    void getCommand() {
        matchController.getCommand(1, 1001);
    }

    @Test
    void getOtherPlayer() {
        matchController.getOtherPlayer(1, 1001);
    }
}