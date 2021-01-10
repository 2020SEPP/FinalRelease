package com.pclogo.demo.daoImpl;

import com.pclogo.demo.dao.UserDao;
import com.pclogo.demo.entity.User;
import com.pclogo.demo.entity.UserMongo;
import com.pclogo.demo.repository.UserMongoRepository;
import com.pclogo.demo.repository.UserRepository;
import com.pclogo.demo.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMongoRepository userMongoRepository;

    @Override
    public Integer lookup(String phone) {
        return userRepository.lookup(phone);
    }

    @Override
    public Integer lookupname(String name) {
        return userRepository.lookupname(name);
    }

    @Override
    public Boolean register(String name, String phone, String password) {
        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        user.setPassword(password);
        userRepository.save(user);
        UserMongo userMongo = new UserMongo();
        userMongo.setId(user.getId());
        List<Integer> friends = new ArrayList<>();
        List<Integer> invite = new ArrayList<>();
        userMongo.setFriends(friends);
        userMongo.setInvite(invite);
        userMongoRepository.save(userMongo);
        return true;
    }

    private UserUtil setLoginTool(User user)
    {
        UserUtil userUtil = new UserUtil();
        if(user == null)
        {
            userUtil.setJudge(false);
            return userUtil;
        }
        UserMongo userMongo = userMongoRepository.findById(user.getId()).orElse(null);
        assert userMongo != null;
        userUtil.setJudge(true);
        userUtil.setName(user.getName());
        userUtil.setUid(user.getId());
        userUtil.setPhone(user.getPhone());
        userUtil.setFriends(userMongo.getFriends());
        userUtil.setAvatar(userMongo.getAvatar());
        return userUtil;
    }

    @Override
    public UserUtil loginByPhone(String phone, String password) {
        User user = userRepository.loginByPhone(phone, password);
        return setLoginTool(user);
    }

    @Override
    public UserUtil loginByName(String name, String password) {
        User user = userRepository.loginByName(name, password);
        return setLoginTool(user);
    }

    @Override
    public UserUtil searchByPhone(String phone) {
        User user = userRepository.searchByPhone(phone);
        UserUtil userUtil = new UserUtil();
        if(user == null)
        {
            userUtil.setJudge(false);
        }
        else {
            setUser(user, userUtil);
        }
        return userUtil;
    }

    public void setUser(User user, UserUtil userUtil) {
        UserMongo userMongo = userMongoRepository.findById(user.getId()).orElse(null);
        userUtil.setJudge(true);
        userUtil.setUid(user.getId());
        userUtil.setName(user.getName());
        assert userMongo != null;
        userUtil.setAvatar(userMongo.getAvatar());
    }

    @Override
    public List<UserUtil> searchByName(String name) {
        String src = "%";
        src += name;
        src += "%";
        List<User> list = userRepository.searchByName(src);
        if(list == null) return null;
        List<UserUtil> reslist = new ArrayList<>();
        for (User user : list) {
            UserUtil tmp = new UserUtil();
            setUser(user, tmp);
            reslist.add(tmp);
        }
        return reslist;
    }


    @Override
    public Boolean setAvatar(String avatar, Integer uid) {
        UserMongo userMongo = userMongoRepository.findById(uid).orElse(null);
        assert userMongo != null;
        userMongo.setAvatar(avatar);
        userMongoRepository.deleteById(uid);
        userMongoRepository.save(userMongo);
        return true;
    }

    @Override
    public List<Integer> getFriendList(Integer uid) {
        UserMongo userMongo =  userMongoRepository.findById(uid).orElse(null);
        assert userMongo != null;
        return userMongo.getFriends();
    }

    @Override
    public UserUtil getFriendByUid(Integer uid) {
        UserUtil userUtil = new UserUtil();
        User user = userRepository.findById(uid).orElse(null);
        UserMongo userMongo = userMongoRepository.findById(uid).orElse(null);
        assert userMongo != null;
        userUtil.setAvatar(userMongo.getAvatar());
        assert user != null;
        userUtil.setUid(user.getId());
        userUtil.setJudge(true);
        userUtil.setName(user.getName());
        return userUtil;
    }

    @Override
    public Boolean updateInfo(Integer id, String name, String password) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return false;
        user.setName(name);
        user.setPassword(password);
        userRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public Boolean updateAvatar(Integer id, String avatar) {
        UserMongo userMongo = userMongoRepository.findById(id).orElse(null);
        if(userMongo == null) return false;
        userMongo.setAvatar(avatar);
        userMongoRepository.deleteById(id);
        userMongoRepository.save(userMongo);
        return true;
    }

    @Override
    public UserUtil getById(Integer uid) {
        User user = userRepository.findById(uid).orElse(null);
        UserUtil userUtil = new UserUtil();
        if(user == null)
        {
            userUtil.setJudge(false);
        }
        else
        {
            setUser(user, userUtil);
        }
        return userUtil;
    }

    @Override
    public List<Integer> getInvites(Integer uid) {
        UserMongo userMongo = userMongoRepository.findById(uid).orElse(null);
        if(userMongo == null) return null;
        return userMongo.getInvite();
    }

    @Override
    public void setInvites(Integer uid, List<Integer> invites) {
        UserMongo userMongo = userMongoRepository.findById(uid).orElse(null);
        if(userMongo == null) return;
        userMongo.setInvite(invites);
        userMongoRepository.save(userMongo);
    }

    @Override
    public void setFriends(Integer uid, List<Integer> friends) {
        UserMongo userMongo = userMongoRepository.findById(uid).orElse(null);
        if(userMongo == null) return;
        userMongo.setFriends(friends);
        userMongoRepository.save(userMongo);
    }

}
