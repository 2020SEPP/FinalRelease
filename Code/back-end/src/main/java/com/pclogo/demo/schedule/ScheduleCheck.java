package com.pclogo.demo.schedule;

import com.pclogo.demo.match.Room;

import com.pclogo.demo.match.RoomUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ScheduleCheck {
    @Scheduled(fixedRate = 5 * 60 * 1000)
    void distroRoom()
    {
        long current_time = System.currentTimeMillis();
        for(Map.Entry<Integer, RoomUtil> entry : Room.rooms.entrySet())
        {
            if(current_time - entry.getValue().user1LastTime > 5000 * 60 && current_time - entry.getValue().user2LastTime > 5000 * 60)
//            if(entry.getValue().user1 != -1 && current_time - entry.getValue().user1LastTime > 30 * 1000 ||
//                    entry.getValue().user2 != -1 && current_time - entry.getValue().user2LastTime > 30 * 1000)
            {
//                System.out.println("抹除了" + entry.getKey());
                Room.rooms.remove(entry.getKey());
            }
        }
    }
    @Scheduled(fixedRate = 30 * 1000)
    void dropout()
    {
        long current_time = System.currentTimeMillis();
        for(Map.Entry<Integer, RoomUtil> entry : Room.rooms.entrySet())
        {
            if(entry.getValue().user1 != -1 && current_time - entry.getValue().user1LastTime > 30 * 1000)
            {
                RoomUtil roomUtil = entry.getValue();
                roomUtil.user1 = -1;
                roomUtil.user1Commands.clear();
                Room.rooms.put(entry.getKey(), roomUtil);
            }
            else if(entry.getValue().user2 != -1 && current_time - entry.getValue().user2LastTime > 30 * 1000)
            {
                RoomUtil roomUtil = entry.getValue();
                roomUtil.user2 = -1;
                roomUtil.user2Commands.clear();
                Room.rooms.put(entry.getKey(), roomUtil);
            }
        }
    }
}
