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
    // the unique key for the database
    ObjectId id;
    // the user's discord member id; this is the snowflake id of the user
    String discordUserId;
    // the user's level
    Integer level = 1;
    // the total XP the user has earned.
    Integer totalXP = 0;
    // the first time user started playing the game
    Date date;
    // a list of `ObjectId`s that are the ids of the pokemon that the user has.
    List<ObjectId> pokemonList = new ArrayList<>();
    // This is a list of discordUserId that are the ids of the friends that the user has.
    List<String> friends = new ArrayList<>();
    // This is a hashmap that is storing the items that the user has.
    HashMap<String, Integer> items = new HashMap<>();
    // This is the id of the pokemon that the user has selected.
    ObjectId selectedPokemon;
    Integer location = PokeMap.STARTING_LOCATION;
}
