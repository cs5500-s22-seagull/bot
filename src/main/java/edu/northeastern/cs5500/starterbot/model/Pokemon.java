package edu.northeastern.cs5500.starterbot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon implements Model {

    private ObjectId id;
    private int cp;
    private String gender;
    private ObjectId pokemonInfo;
    private int level;
    private int hp;
}
