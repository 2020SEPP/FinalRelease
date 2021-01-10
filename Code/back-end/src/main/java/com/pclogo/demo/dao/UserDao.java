package com.pclogo.demo.dao;

import com.pclogo.demo.utils.UserUtil;

import java.util.List;

public interface UserDao {
    Integer lookup(String phone);
    Integer lookupname(String name);
    Boolean register(String name, String phone, String password);
    UserUtil loginByPhone(String phone, String password);
    UserUtil loginByName(String phone, String password);
    UserUtil searchByPhone(String phone);
    List<UserUtil> searchByName(String name);
    Boolean setAvatar(String avatar, Integer uid);
    List<Integer> getFriendList(Integer uid);
    UserUtil getFriendByUid(Integer uid);
    Boolean updateInfo(Integer id, String name, String password);
    Boolean updateAvatar(Integer id, String avatar);
    UserUtil getById(Integer uid);
    List<Integer> getInvites(Integer uid);
    void setInvites(Integer uid, List<Integer> invites);
    void setFriends(Integer uid, List<Integer> friends);
}
