package com.pclogo.demo.daoImpl;

import com.pclogo.demo.DemoApplicationTests;
import com.pclogo.demo.dao.UserDao;
import com.pclogo.demo.entity.User;
import com.pclogo.demo.entity.UserMongo;
import com.pclogo.demo.repository.UserMongoRepository;
import com.pclogo.demo.repository.UserRepository;
import com.pclogo.demo.utils.UserUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class UserDaoImplTest extends DemoApplicationTests
{
    @Autowired
    UserDaoImpl userDao;

    @Autowired
    UserMongoRepository userMongoRepository;

    @Autowired
    WebApplicationContext context;

//    @MockBean
//    private UserRepository userRepository;

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
    void lookup() {
        userDao.lookup("15044341612");
    }

    @Test
    void lookupname() {
        userDao.lookupname("xjh");
    }

    @Test
//    @Transactional
    void register() {
        userDao.register("xjh1hk", "18011111111", "111111");
    }

    @Test
    void loginByPhone() {
        //userDao.setLoginTool("")
        userDao.loginByPhone("15044341612", "111111");
    }
    @Test
    void setLoginToolTest()
    {
        User user = new User();
        user.setId(1);
        userDao.setLoginTool(user);
    }

    @Test
    void loginByName() {
        userDao.loginByName("xjh", "111111");
    }

    @Test
    void searchByPhone() {
        userDao.searchByPhone("-1");
        userDao.searchByPhone("15044341612");
    }

    @Test
    void searchByName() {
        userDao.searchByName("xjh");
        userDao.searchByName("发生什么事了");
    }

    @Test
    void setAvatar() {
        userDao.setAvatar("666", 2);
        userDao.setAvatar("777", -1);
    }

    @Test
    void getFriendList() {
        userDao.getFriendList(2);
        userDao.getFriendList(-1);
    }

    @Test
    void getFriendByUid() {
        userDao.getFriendByUid(2);
        userDao.getFriendByUid(-1);
    }

    @Test
    void updateInfo() {
        userDao.updateInfo(2, "pocker", "??????");
        userDao.updateInfo(-1, "pocker", "??????");
    }

    @Test
    void updateAvatar() {
        userDao.updateAvatar(2, "667");
        userDao.updateAvatar(-1, "667");
    }

    @Test
    void getById() {
        userDao.getById(2);
        userDao.getById(-1);
    }

    @Test
    void getInvites() {
        userDao.getInvites(2);
        userDao.getInvites(-1);
    }
    @Test
    void test()
    {
        User user = new User();
        user.setName("name");
        user.setPhone("18888888888");
        user.setPassword("password");
//        userRepository.save(user);
//        List<Integer> tm = new ArrayList<>();
        UserMongo userMongo = new UserMongo();

        System.out.println("?");
        userMongo.setId(user.getId());
//        ObjectId idtmp = new ObjectId();
        List<Integer> friends = new ArrayList<>();
        List<Integer> invite = new ArrayList<>();
        userMongo.setFriends(friends);
        userMongo.setInvite(invite);
    }

    @Test
    void setInvites() {
        List<Integer> tmp = new ArrayList<>();
        userDao.setInvites(2, tmp);
        userDao.setInvites(-1, tmp);
    }

    @Test
    void setFriends() {
        List<Integer> tmp = new ArrayList<>();
        userDao.setFriends(2, tmp);
        userDao.setFriends(-1, tmp);
    }

//    @Test
//    void testSetUser()
//    {
//        User user = new User();
//        UserUtil userUtil = new UserUtil();
//        userDao.setUser(user, userUtil);
//    }

}