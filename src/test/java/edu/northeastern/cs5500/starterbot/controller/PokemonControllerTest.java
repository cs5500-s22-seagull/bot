package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.model.PokemonInfo;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokemonControllerTest {
    private PokemonController pokemonController;
    private PokemonInfoController pokemonInfoController;

    @BeforeEach
    void setUp() {
        pokemonController = new PokemonController();
        pokemonInfoController = new PokemonInfoController();
        pokemonController.pokemonRepository = new InMemoryRepository<>();
        pokemonInfoController.pokemonInfoRepository = new InMemoryRepository<>();
        pokemonController.pokemonInfoController = pokemonInfoController;
    }

    @Test
    void testGetPokemonByObjectId() {
        Pokemon pokemon = new Pokemon();
        pokemonController.pokemonRepository.add(pokemon);
        Pokemon pokemon2 = pokemonController.getPokemonByObjectId(pokemon.getId());
        assertThat(pokemon2).isEqualTo(pokemon);
    }

    @Test
    void testGetGender() {
        Pokemon pokemon = new Pokemon();
        pokemon.setGender("male");
        pokemonController.pokemonRepository.add(pokemon);
        String gender = pokemonController.getGender(pokemon.getId());
        assertThat(gender).isEqualTo("male");
    }

    @Test
    void testGetCp() {
        Pokemon pokemon = new Pokemon();
        pokemon.setCp(123);
        pokemonController.pokemonRepository.add(pokemon);
        int cp = pokemonController.getCp(pokemon.getId());
        assertThat(cp).isEqualTo(123);
    }

    @Test
    void testGetPokemonInfo() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfoController.pokemonInfoRepository.add(pokemonInfo);
        Pokemon pokemon = new Pokemon();
        pokemon.setPokemonInfo(pokemonInfo.getId());
        pokemonController.pokemonRepository.add(pokemon);
        ObjectId info = pokemonController.getPokemonInfo(pokemon.getId());
        assertThat(info).isEqualTo(pokemonInfo.getId());
    }

    @Test
    void testGetLevel() {
        Pokemon pokemon = new Pokemon();
        pokemon.setLevel(66);
        pokemonController.pokemonRepository.add(pokemon);
        int level = pokemonController.getLevel(pokemon.getId());
        assertThat(level).isEqualTo(66);
    }

    @Test
    void testPowerUp() {
        Pokemon pokemon = new Pokemon();
        pokemon.setLevel(67);
        pokemonController.pokemonRepository.add(pokemon);
        pokemonController.powerUp(pokemon.getId());
        assertThat(pokemon.getLevel()).isEqualTo(68);
    }

    @Test
    void testGetName() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setPokemonName("testPokemonName");
        pokemonInfoController.pokemonInfoRepository.add(pokemonInfo);
        Pokemon pokemon = new Pokemon();
        pokemon.setPokemonInfo(pokemonInfo.getId());
        pokemonController.pokemonRepository.add(pokemon);
        assertThat(pokemonController.getName(pokemon.getId())).isEqualTo("testPokemonName");
    }

    @Test
    void testGetHp() {
        Pokemon pokemon = new Pokemon();
        pokemon.setHp(100);
        pokemonController.pokemonRepository.add(pokemon);
        assertThat(pokemonController.getHp(pokemon.getId())).isEqualTo(100);
    }

    @Test
    void testGetMaxHp() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setMaxHP(1200);
        pokemonInfoController.pokemonInfoRepository.add(pokemonInfo);
        Pokemon pokemon = new Pokemon();
        pokemon.setPokemonInfo(pokemonInfo.getId());
        pokemonController.pokemonRepository.add(pokemon);
        assertThat(pokemonController.getMaxHp(pokemon.getId())).isEqualTo(1200);
    }

    @Test
    void testGetImage() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setPictureAddress("pictureAddress");
        pokemonInfoController.pokemonInfoRepository.add(pokemonInfo);
        Pokemon pokemon = new Pokemon();
        pokemon.setPokemonInfo(pokemonInfo.getId());
        pokemonController.pokemonRepository.add(pokemon);
        assertThat(pokemonController.getImage(pokemon.getId())).isEqualTo("pictureAddress");
    }

    @Test
    void testGetCurrentHp() {
        Pokemon pokemon = new Pokemon();
        pokemon.setCurrentHp(1201);
        pokemonController.pokemonRepository.add(pokemon);
        assertThat(pokemonController.getCurrentHp(pokemon.getId())).isEqualTo(1201);
    }

    @Test
    void testSetCurrentHp() {
        Pokemon pokemon = new Pokemon();
        pokemon.setCurrentHp(1001);
        pokemonController.pokemonRepository.add(pokemon);
        pokemonController.setCurrentHp(pokemon.getId(), 1021);
        assertThat(pokemonController.getCurrentHp(pokemon.getId())).isEqualTo(1021);
    }
}