package edu.northeastern.cs5500.starterbot.model;

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
    List<Pokemon> pokemonList;
}
