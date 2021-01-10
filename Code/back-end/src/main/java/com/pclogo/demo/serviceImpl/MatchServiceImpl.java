package com.pclogo.demo.serviceImpl;

import com.pclogo.demo.dao.UserDao;
import com.pclogo.demo.match.Room;
import com.pclogo.demo.match.RoomUtil;
import com.pclogo.demo.service.MatchService;
import com.pclogo.demo.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    UserDao userDao;

    @Override
    public Integer createRoom(Integer uid, Integer isSingle) {
//        System.out.println(Room.current_id);
        Room.current_id++;
        RoomUtil roomUtil = new RoomUtil();
        roomUtil.isSingle = isSingle;
//        roomUtil.users.add(uid);
        roomUtil.user1 = uid;
        Room.rooms.put(Room.current_id, roomUtil);
//        System.out.println(Room.rooms);
        return Room.current_id;
    }

    @Override
    public Boolean joinById(Integer uid, Integer rid, Integer isSingle) {
        RoomUtil roomUtil = Room.rooms.get(rid);
        if(roomUtil == null) return false;
        if(!roomUtil.isSingle.equals(isSingle)) return false;
        else if(roomUtil.user1 != -1 && roomUtil.user2 != -1) return false;
//        roomUtil.users.add(uid);
        if(roomUtil.user1 == -1) roomUtil.user1 = uid;
        else roomUtil.user2 = uid;
        Room.rooms.put(rid, roomUtil);
        return true;
    }

    @Override
    public Integer joinSrand(Integer uid, Integer isSingle) {
        while(Room.using);
        Room.using = true;
        if(Room.rooms.size() == 0) return -1;

        for(Map.Entry<Integer, RoomUtil> entry : Room.rooms.entrySet())
        {
            if(entry.getValue().user1 == -1 && entry.getValue().isSingle.equals(isSingle))
            {
                RoomUtil roomUtil = entry.getValue();
                roomUtil.user1 = uid;
                Integer rid = entry.getKey();
                Room.rooms.put(rid, roomUtil);
                Room.using = false;
                return rid;
            }
            else if(entry.getValue().user2 == -1 && entry.getValue().isSingle.equals(isSingle))
            {
                RoomUtil roomUtil = entry.getValue();
                roomUtil.user2 = uid;
                Integer rid = entry.getKey();
                Room.rooms.put(rid, roomUtil);
                Room.using = false;
                return rid;
            }
        }
        Room.using = false;
        return -1;
    }

    @Override
    public Boolean sendCommand(Integer uid, Integer rid, String command) {

        RoomUtil roomUtil = Room.rooms.get(rid);
        System.out.println(Room.rooms);
        if(roomUtil == null) return false;
        if(roomUtil.user1.equals(uid))
        {
            roomUtil.user1Commands.add(command);
            roomUtil.user1LastTime = System.currentTimeMillis();
        }
        else if(roomUtil.user2.equals(uid))
        {
            roomUtil.user2Commands.add(command);
            roomUtil.user2LastTime = System.currentTimeMillis();
        }
        else return false;
        Room.rooms.put(rid, roomUtil);
        for(Map.Entry<Integer, RoomUtil> entry : Room.rooms.entrySet())
        {
            System.out.println(entry.getValue().user1Commands);
        }
        return true;
    }

    @Override
    public List<String> getCommand(Integer uid, Integer rid) {
        RoomUtil roomUtil = Room.rooms.get(rid);
        List<String> res = new ArrayList<>();
        if(roomUtil == null) return null;
        if(roomUtil.user1.equals(uid))
        {
            for(; roomUtil.user1Read < roomUtil.user2Commands.size(); ++roomUtil.user1Read)
                res.add(roomUtil.user2Commands.get(roomUtil.user1Read));
            roomUtil.user1LastTime = System.currentTimeMillis();
            Room.rooms.put(rid, roomUtil);
            return res;
        }
        else if(roomUtil.user2.equals(uid))
        {
            for(; roomUtil.user2Read < roomUtil.user1Commands.size(); ++roomUtil.user2Read)
                res.add(roomUtil.user1Commands.get(roomUtil.user2Read));
            roomUtil.user2LastTime = System.currentTimeMillis();
            Room.rooms.put(rid, roomUtil);
            return res;
        }
        return null;
    }

    @Override
    public UserUtil getOtherPlayer(Integer uid, Integer rid) {
        Integer dst;
        RoomUtil roomUtil = Room.rooms.get(rid);
        if(roomUtil == null) return null;
        if(roomUtil.user1.equals(uid)) dst = roomUtil.user2;
        else if(roomUtil.user2.equals(uid)) dst = roomUtil.user1;
        else return null;
        return userDao.getById(dst);
    }

    @Override
    public void removeRoom(Integer uid, Integer rid) {
        if(Room.rooms.get(rid) == null) return;
        if(Room.rooms.get(rid).user1.equals(uid)) {
            Room.rooms.remove(rid);
        }
    }
}
