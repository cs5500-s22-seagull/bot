package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

@Slf4j
@Singleton
public class PokemonController {
    private PokemonInfoController pokemonInfoController;
    private GenericRepository<Pokemon> pokemonRepository;

    @Inject
    public PokemonController(
            PokemonInfoController pokemonInfoController,
            GenericRepository<Pokemon> pokemonRepository) {
        this.pokemonInfoController = pokemonInfoController;
        this.pokemonRepository = pokemonRepository;
    }

    /**
     * Get Pokemon By ObjectId
     *
     * @param id
     * @return Pokemon
     */
    public Pokemon getPokemonByObjectId(ObjectId id) {
        return pokemonRepository.get(id);
    }

    /**
     * Get Gender of a Pokemon
     *
     * @param id
     * @return String
     */
    public String getGender(ObjectId id) {
        Pokemon pokemon = pokemonRepository.get(id);
        return pokemon.getGender();
    }

    /**
     * Get Cp of a Pokemon
     *
     * @param id
     * @return int
     */
    public int getCp(ObjectId id) {
        Pokemon pokemon = pokemonRepository.get(id);
        return pokemon.getCp();
    }

    /**
     * Get the PokemonInfo of a Pokemon in objectid
     *
     * @param id
     * @return ObjectId
     */
    public ObjectId getPokemonInfo(ObjectId id) {
        Pokemon pokemon = pokemonRepository.get(id);
        return pokemon.getPokemonInfo();
    }

    /**
     * Get leval of a Pokemon
     *
     * @param id
     * @return int
     */
    public int getLevel(ObjectId id) {
        Pokemon pokemon = pokemonRepository.get(id);
        return pokemon.getLevel();
    }

    /**
     * Power up a pokemon
     *
     * @param id
     */
    public void powerUp(ObjectId id) {
        Pokemon pokemon = pokemonRepository.get(id);
        pokemon.setLevel(pokemon.getLevel() + 1);
        pokemonRepository.update(pokemon);
    }

    /**
     * Get Pokemon's name
     *
     * @param id
     * @return String
     */
    public String getName(ObjectId id) {
        return pokemonInfoController.getName(getPokemonInfo(id));
    }

    /**
     * Get hp of a Pokemon
     *
     * @param id
     * @return int
     */
    public int getHp(ObjectId id) {
        return getPokemonByObjectId(id).getHp();
    }

    /**
     * Get max hp of a Pokemon
     *
     * @param id
     * @return int
     */
    public int getMaxHp(ObjectId id) {
        return pokemonInfoController.getMaxHp(getPokemonInfo(id));
    }

    /**
     * Get the image address of a Pokemon
     *
     * @param id
     * @return String
     */
    public String getImage(ObjectId id) {
        return pokemonInfoController.getImage(getPokemonInfo(id));
    }

    /**
     * Get current hp of a Pokemon
     *
     * @param id
     * @return int
     */
    public int getCurrentHp(ObjectId id) {
        return getPokemonByObjectId(id).getCurrentHp();
    }

    /**
     * Set current hp of a Pokemon
     *
     * @param id
     * @param hp
     */
    public void setCurrentHp(ObjectId id, int hp) {
        Pokemon pokemon = getPokemonByObjectId(id);
        pokemon.setCurrentHp(hp);
        pokemonRepository.update(pokemon);
    }

    /**
     * Get owned moves of a pokemon
     *
     * @param id
     * @return String[]
     */
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

    /**
     * Add Pokemon to repository
     *
     * @param pokemon
     */
    public void addPokemon(Pokemon pokemon) {
        pokemonRepository.add(pokemon);
    }
}
