package edu.northeastern.cs5500.starterbot.model;

import java.util.HashMap;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
public class Pokemon implements Model {

    private ObjectId id;
    private int cp;
    private String gender;
    private ObjectId pokemonInfo;
    private int level;
    private int hp;
    private int currentHp;
    private HashMap<String, String> ownedMoves;
}
