package edu.northeastern.cs5500.starterbot.model;

import java.util.List;
import org.bson.types.ObjectId;

public class PokemonInfo implements Model {

    private ObjectId id;

    private String pokemonName;
    private int pokemonNumber;
    private String gender;
    private List<String> pokemonType;
    private List<PokemonInfo> evolution;
    private String introduction;

    public PokemonInfo(
            ObjectId id,
            String pokemonName,
            String gender,
            int pokemonNumber,
            List<String> pokemonType,
            List<PokemonInfo> evolution,
            String introduction) {
        this.id = id;
        this.pokemonName = pokemonName;
        this.gender = gender;
        this.pokemonNumber = pokemonNumber;
        this.pokemonType = pokemonType;
        this.evolution = evolution;
        this.introduction = introduction;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public int getPokemonNumber() {
        return pokemonNumber;
    }

    public void setPokemonNumber(int pokemonNumber) {
        this.pokemonNumber = pokemonNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getPokemonType() {
        return pokemonType;
    }

    public void setPokemonType(List<String> pokemonType) {
        this.pokemonType = pokemonType;
    }

    public List<PokemonInfo> getEvolution() {
        return evolution;
    }

    public void setEvolution(List<PokemonInfo> evolution) {
        this.evolution = evolution;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public ObjectId getId() {
        // TODO Auto-generated method stub
        return this.id;
    }

    @Override
    public void setId(ObjectId id) {
        this.id = id;
    }
}
