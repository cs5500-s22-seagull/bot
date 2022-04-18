package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.model.PokemonInfo;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import javax.inject.Inject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

@Slf4j
@Data
public class PokemonController {

    @Inject
    PokemonController() {}

    @Inject GenericRepository<Pokemon> pokemonRepository;
    @Inject GenericRepository<PokemonInfo> pokemonInfoRepository;

    public Pokemon getPokemonByObjectId(ObjectId id) {
        return pokemonRepository.get(id);
    }

    public String getGender(ObjectId id) {
        Pokemon pokemon = pokemonRepository.get(id);
        return pokemon.getGender();
    }

    public int getCp(ObjectId id) {
        Pokemon pokemon = pokemonRepository.get(id);
        return pokemon.getCp();
    }

    public ObjectId getPokemonInfo(ObjectId id) {
        Pokemon pokemon = pokemonRepository.get(id);
        PokemonInfo pokemonInfo = pokemonInfoRepository.get(pokemon.getPokemonInfo());
        return pokemonInfo.getId();
    }

    public int getLevel(ObjectId id) {
        Pokemon pokemon = pokemonRepository.get(id);
        return pokemon.getLevel();
    }

    public void powerUp(ObjectId id) {
        Pokemon pokemon = pokemonRepository.get(id);
        pokemon.setCp(pokemon.getCp() + 1);
    }

    public void evolve(ObjectId id) {
        Pokemon pokemon = pokemonRepository.get(id);
        pokemon.setLevel(pokemon.getLevel() + 1);
    }

    public String getName(ObjectId id) {
        return pokemonInfoRepository.get(getPokemonInfo(id)).getPokemonName();
    }

    public int getHp(ObjectId id) {
        return getPokemonByObjectId(id).getHp();
    }

    public int getMaxHp(ObjectId id) {
        return pokemonInfoRepository.get(getPokemonInfo(id)).getMaxHP();
    }

    public String getImage(ObjectId id) {
        return pokemonInfoRepository.get(getPokemonInfo(id)).getPictureAddress();
    }

    public int getCurrentHp(ObjectId id) {
        return getPokemonByObjectId(id).getCurrentHp();
    }

    public void setCurrentHp(ObjectId id, int hp) {
        Pokemon pokemon = getPokemonByObjectId(id);
        pokemon.setCurrentHp(hp);
        pokemonRepository.update(pokemon);
    }
}
