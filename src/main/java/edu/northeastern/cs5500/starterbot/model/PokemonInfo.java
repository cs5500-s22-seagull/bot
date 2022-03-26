package edu.northeastern.cs5500.starterbot.model;

import java.util.List;
import javax.inject.Inject;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class PokemonInfo implements Model {

    @Inject
    public PokemonInfo() {}

    private ObjectId id;
    private String pokemonName;
    private int pokemonNumber;
    private String gender;
    private List<String> pokemonType;
    private List<PokemonInfo> evolution;
    private String introduction;
}
