package edu.northeastern.cs5500.starterbot.model;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonInfo implements Model {
    private ObjectId id;

    private String pokemonName;
    private int pokemonNumber;
    private String gender;
    private List<String> pokemonType = new ArrayList<>();

    // each ObjectId should point to a Pokemon
    // TODO: How to represent Eevee?
    private List<ObjectId> evolution = new ArrayList<>();
    private String introduction;
}
