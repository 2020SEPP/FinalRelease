package com.pclogo.demo.serviceImpl;

import com.alibaba.fastjson.JSON;

import com.pclogo.demo.constant.Constant;
import com.pclogo.demo.dao.UserDao;
import com.pclogo.demo.service.UserService;
import com.pclogo.demo.utils.JwtToken;
import com.pclogo.demo.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    public UserUtil setToken(UserUtil userUtil)
    {
        if(userUtil == null)
        {
            userUtil = new UserUtil();
            userUtil.setJudge(false);
        }
        else
        {
            String tmp = userUtil.getAvatar();
            userUtil.setAvatar("");
            userUtil.setToken(JwtToken.createJWT(Constant.JWT_ID, JSON.toJSONString(userUtil), Constant.JWT_TTL));
            userUtil.setAvatar(tmp);
        }
        return userUtil;
    }

    @Override
    public UserUtil loginByPhone(String phone, String password) {
        return setToken(userDao.loginByPhone(phone, password));
    }

    @Override
    public UserUtil loginByName(String name, String password) {
        return setToken(userDao.loginByName(name, password));
    }

    @Override
    public Integer register(String name, String phone, String password) {
        if(userDao.lookup(phone) > 0) return 0;
        else if(userDao.lookupname(name) > 0) return -1;
        if(userDao.register(name, phone, password)) return 1;
        else return -2;
    }

    @Override
    public List<UserUtil> searchByName(String name) {
        return userDao.searchByName(name);
    }

    @Override
    public Boolean setAvatar(String avatar, Integer uid) {
        return userDao.setAvatar(avatar, uid);
    }

    @Override
    public List<UserUtil> getFriend(Integer uid) {
        List<Integer> list = userDao.getFriendList(uid);
        if(list == null) return null;
        List<UserUtil> res = new ArrayList<>();
        for(Integer i:list)
        {
            UserUtil userUtil = userDao.getFriendByUid(i);
            res.add(userUtil);
        }
        return res;
    }

    @Override
    public Boolean updateInfo(Integer id, String name, String password) {
        return userDao.updateInfo(id, name, password);
    }

    @Override
    public Boolean updateAvatar(Integer id, String avatar) {
        return userDao.updateAvatar(id, avatar);
    }

    @Override
    public void sendInvite(Integer uid, Integer touid) {
        if(uid.equals(touid)) return;
        List<Integer> friends = userDao.getFriendList(uid);
        for(Integer friend : friends) if(friend.equals(touid)) return;
        List<Integer> invites = userDao.getInvites(touid);
        invites.add(uid);
        userDao.setInvites(touid, invites);
    }

    @Override
    public List<UserUtil> checkInvite(Integer uid)
    {
        List<Integer> invites = userDao.getInvites(uid);
        List<Integer> friends = userDao.getFriendList(uid);

        for(int i = 0; i < friends.size(); ++i)
        {
            for(int j = 0; j < invites.size(); ++j)
            {
                if(friends.get(i).equals(invites.get(j)))
                {
                    invites.remove(j);
                    break;
                }
            }
        }

        userDao.setInvites(uid, invites);
        List<UserUtil> res = new ArrayList<>();
        for(Integer i : invites)
        {

            UserUtil userUtil = userDao.getById(i);
            res.add(userUtil);
        }
        return res;
    }
    void setFriend(Integer uid, Integer touid)
    {
        List<Integer> friends = userDao.getFriendList(uid);
        friends.add(touid);
        userDao.setFriends(uid, friends);
    }

    @Override
    public Boolean acceptInvite(Integer uid, Integer touid, Integer accept)
    {
        //不论如何处理，都要将申请移除
        List<Integer> invites = userDao.getInvites(uid);
        if(invites == null) return false;
        for(int i = 0; i < invites.size(); ++i)
        {
            if(invites.get(i).equals(touid))
            {
                invites.remove(i);
                i--;
            }

        }
        userDao.setInvites(uid, invites);
        //拒绝
        if(accept == 0) return true;
        //接受
        setFriend(uid, touid);
        setFriend(touid, uid);
        return true;
    }

    @Override
    public UserUtil searchByPhone(String friendPhone) {
        return userDao.searchByPhone(friendPhone);
    }

}
