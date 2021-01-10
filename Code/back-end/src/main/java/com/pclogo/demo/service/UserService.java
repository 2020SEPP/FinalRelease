package com.pclogo.demo.service;

import com.pclogo.demo.utils.UserUtil;

import java.util.List;

public interface UserService {
    UserUtil loginByPhone(String phone, String password);
    UserUtil loginByName(String phone, String password);
    Integer register(String name, String phone, String password);
    UserUtil searchByPhone(String friendPhone);
    List<UserUtil> searchByName(String name);
    Boolean setAvatar(String avatar, Integer uid);
    List<UserUtil> getFriend(Integer uid);
    Boolean updateInfo(Integer id, String name, String password);
    Boolean updateAvatar(Integer id, String avatar);
    void sendInvite(Integer uid, Integer touid);
    List<UserUtil> checkInvite(Integer uid);
    Boolean acceptInvite(Integer uid, Integer touid, Integer accept);
}
