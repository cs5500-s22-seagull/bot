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

    @Inject PokemonInfoController pokemonInfoController;

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
        return pokemon.getPokemonInfo();
    }

    public int getLevel(ObjectId id) {
        Pokemon pokemon = pokemonRepository.get(id);
        return pokemon.getLevel();
    }

    public void powerUp(ObjectId id) {
        Pokemon pokemon = pokemonRepository.get(id);
        pokemon.setLevel(pokemon.getLevel() + 1);
        pokemonRepository.update(pokemon);
    }

    public String getName(ObjectId id) {
        return pokemonInfoController.getName(getPokemonInfo(id));

        // return pokemonInfoRepository.get(getPokemonInfo(id)).getPokemonName();
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

    public String[] getOwnedMoves(ObjectId id) {
        Object key1 = getPokemonByObjectId(id).getOwnedMoves().keySet().toArray()[0];
        String valueForKey1 = getPokemonByObjectId(id).getOwnedMoves().get(key1);

        Object key2 = getPokemonByObjectId(id).getOwnedMoves().keySet().toArray()[1];
        String valueForKey2 = getPokemonByObjectId(id).getOwnedMoves().get(key2);

        String[] res = new String[4];
        res[0] = key1.toString();
        res[1] = valueForKey1;
        res[2] = key2.toString();
        res[3] = valueForKey2;

        return res;
    }
}
