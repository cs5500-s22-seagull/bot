package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.model.PokemonInfo;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokemonControllerTest {
    private GenericRepository<PokemonInfo> pokemonInfoRepository;
    private GenericRepository<Pokemon> pokemonRepository;
    private PokemonInfoController pokemonInfoController;
    private PokemonController pokemonController;

    @BeforeEach
    void setUp() {
        // TODO: this is not a good idea, you shouldn't need to manipulate the repository directly
        // to test the controllers
        pokemonInfoRepository = new InMemoryRepository<>();
        pokemonRepository = new InMemoryRepository<>();

        pokemonInfoController = new PokemonInfoController(pokemonInfoRepository);
        pokemonController = new PokemonController(pokemonInfoController, pokemonRepository);
    }

    @Test
    void testGetPokemonByObjectId() {
        Pokemon pokemon = new Pokemon();
        pokemonRepository.add(pokemon);
        Pokemon pokemon2 = pokemonController.getPokemonByObjectId(pokemon.getId());
        assertThat(pokemon2).isEqualTo(pokemon);
    }

    @Test
    void testGetGender() {
        Pokemon pokemon = new Pokemon();
        pokemon.setGender("male");
        pokemonRepository.add(pokemon);
        String gender = pokemonController.getGender(pokemon.getId());
        assertThat(gender).isEqualTo("male");
    }

    @Test
    void testGetCp() {
        Pokemon pokemon = new Pokemon();
        pokemon.setCp(123);
        pokemonRepository.add(pokemon);
        int cp = pokemonController.getCp(pokemon.getId());
        assertThat(cp).isEqualTo(123);
    }

    @Test
    void testGetPokemonInfo() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfoRepository.add(pokemonInfo);
        Pokemon pokemon = new Pokemon();
        pokemon.setPokemonInfo(pokemonInfo.getId());
        pokemonRepository.add(pokemon);
        ObjectId info = pokemonController.getPokemonInfo(pokemon.getId());
        assertThat(info).isEqualTo(pokemonInfo.getId());
    }

    @Test
    void testGetLevel() {
        Pokemon pokemon = new Pokemon();
        pokemon.setLevel(66);
        pokemonRepository.add(pokemon);
        int level = pokemonController.getLevel(pokemon.getId());
        assertThat(level).isEqualTo(66);
    }

    @Test
    void testPowerUp() {
        Pokemon pokemon = new Pokemon();
        pokemon.setLevel(67);
        pokemonRepository.add(pokemon);
        pokemonController.powerUp(pokemon.getId());
        assertThat(pokemon.getLevel()).isEqualTo(68);
    }

    @Test
    void testGetName() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setPokemonName("testPokemonName");
        pokemonInfoRepository.add(pokemonInfo);
        Pokemon pokemon = new Pokemon();
        pokemon.setPokemonInfo(pokemonInfo.getId());
        pokemonRepository.add(pokemon);
        assertThat(pokemonController.getName(pokemon.getId())).isEqualTo("testPokemonName");
    }

    @Test
    void testGetHp() {
        Pokemon pokemon = new Pokemon();
        pokemon.setHp(100);
        pokemonRepository.add(pokemon);
        assertThat(pokemonController.getHp(pokemon.getId())).isEqualTo(100);
    }

    @Test
    void testGetMaxHp() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setMaxHP(1200);
        pokemonInfoRepository.add(pokemonInfo);
        Pokemon pokemon = new Pokemon();
        pokemon.setPokemonInfo(pokemonInfo.getId());
        pokemonRepository.add(pokemon);
        assertThat(pokemonController.getMaxHp(pokemon.getId())).isEqualTo(1200);
    }

    @Test
    void testGetImage() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setPictureAddress("pictureAddress");
        pokemonInfoRepository.add(pokemonInfo);
        Pokemon pokemon = new Pokemon();
        pokemon.setPokemonInfo(pokemonInfo.getId());
        pokemonRepository.add(pokemon);
        assertThat(pokemonController.getImage(pokemon.getId())).isEqualTo("pictureAddress");
    }

    @Test
    void testGetCurrentHp() {
        Pokemon pokemon = new Pokemon();
        pokemon.setCurrentHp(1201);
        pokemonRepository.add(pokemon);
        assertThat(pokemonController.getCurrentHp(pokemon.getId())).isEqualTo(1201);
    }

    @Test
    void testSetCurrentHp() {
        Pokemon pokemon = new Pokemon();
        pokemon.setCurrentHp(1001);
        pokemonRepository.add(pokemon);
        pokemonController.setCurrentHp(pokemon.getId(), 1021);
        assertThat(pokemonController.getCurrentHp(pokemon.getId())).isEqualTo(1021);
    }
}
