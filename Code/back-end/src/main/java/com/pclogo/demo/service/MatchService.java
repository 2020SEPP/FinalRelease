package com.pclogo.demo.service;

import com.pclogo.demo.utils.UserUtil;

import java.util.List;

public interface MatchService {
    Integer createRoom(Integer uid, Integer isSingle);
    Boolean joinById(Integer uid, Integer rid, Integer isSingle);
    Integer joinSrand(Integer uid, Integer isSingle);
    Boolean sendCommand(Integer uid, Integer rid, String command);
    List<String> getCommand(Integer uid, Integer rid);
    UserUtil getOtherPlayer(Integer uid, Integer rid);
    void removeRoom(Integer uid, Integer rid);
}
