package com.pclogo.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class UserUtil {
    String name;
    Boolean judge;
    String token;
    Integer uid;
    String phone;
    List<Integer> friends;
    String avatar;
}
