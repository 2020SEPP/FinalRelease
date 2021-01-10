package com.pclogo.demo.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@Document("userMongo")
public class UserMongo {
    @Id
    Integer id;

    @Field("friends")
    List<Integer> friends = new ArrayList<Integer>();

    @Field("invite")
    List<Integer> invite = new ArrayList<Integer>();

    @Field("avatar")
    String avatar = "";
}
