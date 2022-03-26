package edu.northeastern.cs5500.starterbot.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Pokedex implements Model {
    private ObjectId id;
    private int seen;
    private int caught;
    private ObjectId pokemonInfo;
}
