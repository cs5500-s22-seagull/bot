/** This class is used to store the information of the players */
package edu.northeastern.cs5500.starterbot.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
public class Player implements Model {
    static final String DEFAULT_NAME = "trainer";
    static final Integer DEFAULT_LEVEL = 1;
    static final Integer DEFAULT_XP = 0;
    static final String DEFAULT_DISCORD_NAME = "default name";

    // the discord snowflake id
    ObjectId id;
    // the name user set for themselves
    String name = DEFAULT_NAME;
    // This is setting the default value of the discordName to "discordName".
    String discordName = DEFAULT_DISCORD_NAME;
    // the user's discord member id
    String discordMemberId;
    // the user's level
    Integer level = DEFAULT_LEVEL;
    // the total XP the user has earned.
    Integer totalXP = DEFAULT_XP;
    // the first time user started playing the game
    Date date;
    // a list of `ObjectId`s that are the ids of the pokemon that the user has.
    List<ObjectId> pokemonList = new ArrayList<>();
    // This is a list of ObjectIds that are the ids of the friends that the user has.
    List<ObjectId> friends = new ArrayList<>();
    // This is a hashmap that is storing the items that the user has.
    HashMap<String, Integer> items = new HashMap<>();
}
