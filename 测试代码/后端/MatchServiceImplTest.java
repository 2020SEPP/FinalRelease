package com.pclogo.demo.serviceImpl;

import com.pclogo.demo.service.MatchService;
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
class MatchServiceImplTest {
    @Autowired
    MatchServiceImpl matchService;

    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;
    private int i=0;
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
        matchService.createRoom(2, 0);
    }

    @Test
    void joinById() {
        matchService.testing=1;
        matchService.createRoom(2, 0);
        matchService.joinById(2, 1001, 0);
        matchService.joinById(1, 1001, 1);
        matchService.joinById(1, 1001, 0);
        matchService.joinById(3, 1001, 0);

    }

    @Test
    void joinSrand() {
        matchService.createRoom(2, 0);
        matchService.joinSrand(2, 0);
        matchService.joinSrand(1, 1);
        matchService.joinSrand(1, 0);
        matchService.joinSrand(3, 0);

    }

    @Test
    void sendCommand() {
        matchService.createRoom(2, 0);
        matchService.sendCommand(2, 1001, "fd 100");
        matchService.joinById(1, 1001, 0);
        matchService.sendCommand(2, 1001, "fd 100");
        matchService.sendCommand(1, 1001, "fd 1000");
    }

    @Test
    void getCommand() {
        matchService.createRoom(2, 0);
        matchService.joinById(1, 1001, 0);
        matchService.sendCommand(2, 1001, "fd 100");
        matchService.sendCommand(1, 1001, "fd 1000");
        matchService.getCommand(1, 1001);
        matchService.getCommand(2, 1001);
        matchService.getCommand(1, 1002);
        matchService.getCommand(2, 1002);

    }

    @Test
    void getOtherPlayer() {
        matchService.createRoom(2, 0);
        matchService.getOtherPlayer(2, 1001);
        matchService.getOtherPlayer(1, 1001);
        matchService.getOtherPlayer(2, 1002);

        matchService.joinById(1, 1001, 0);
        matchService.getOtherPlayer(2, 1001);
        matchService.getOtherPlayer(1, 1001);

    }
}