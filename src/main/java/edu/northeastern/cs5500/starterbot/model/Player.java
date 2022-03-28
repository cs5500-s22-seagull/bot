/** This class is used to store the information of the players */
package edu.northeastern.cs5500.starterbot.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
public class Player implements Model {

    // the discord snowflake id
    ObjectId id;
    // the name user set for themselves
    String name;
    // the user's discord member id
    String discordMemberId;
    // the user's level
    Integer level;
    // the total XP the user has earned.
    Integer totalXP;
    // the first time user started playing the game
    Date date;
    // a list of `ObjectId`s that are the ids of the pokemon that the user has.
    List<ObjectId> pokemonList;
    // This is a list of ObjectIds that are the ids of the friends that the user has.
    List<ObjectId> friends;
    // This is a hashmap that is storing the items that the user has.
    HashMap<String, Integer> items;
}
