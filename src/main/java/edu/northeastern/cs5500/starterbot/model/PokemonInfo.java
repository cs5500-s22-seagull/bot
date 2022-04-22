package edu.northeastern.cs5500.starterbot.model;

import java.util.HashMap;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
public class PokemonInfo implements Model {

    private ObjectId id;
    private String pokemonName;
    private int pokemonNumber;
    private List<String> pokemonType;
    private List<String> evolution;
    private String introduction;
    private String pictureAddress;
    private int maxHP;
    private int maxCP;
    private HashMap<String, String> moves; // Integer[0]:hurt; Integer[1]:accuracy
}
