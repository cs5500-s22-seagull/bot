package edu.northeastern.cs5500.starterbot.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Player implements Model {
    ObjectId id;
    String name;
    String discordMemberId;
    Integer level;
    Integer totalXP;
    Date date;
    List<ObjectId> pokemonList;
    ObjectId friends;
    HashMap<String, Integer> items;
}
