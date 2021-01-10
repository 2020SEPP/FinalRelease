package com.pclogo.demo.match;

import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Room {
    public static Boolean using;
    public static Integer current_id;
    public static Map<Integer, RoomUtil> rooms;
    public Room()
    {
        using = false;
        current_id = 1000;
        rooms = new HashMap<>();
    }
}
