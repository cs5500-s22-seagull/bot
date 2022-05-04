package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.model.PokemonInfo;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokemonInfoControllerTest {

    private PokemonInfoController pokemonInfoController;
    private PokedexController pokedexController;
    private PokemonController pokemonController;
    private InMemoryRepository inMemoryRepository;

    @BeforeEach
    void setUp() {
        pokemonInfoController = new PokemonInfoController();
        pokedexController = new PokedexController();
        pokemonController = new PokemonController();
        inMemoryRepository = new InMemoryRepository<>();
        pokemonInfoController.pokemonInfoRepository = inMemoryRepository;
        pokedexController.pokedexRepository = inMemoryRepository;
        pokedexController.pokemonInfoRepository = inMemoryRepository;
        pokemonController.pokemonRepository = inMemoryRepository;s
    }

    @Test
    void testGetMaxCpbyName() {
        pokedexController.addInitPokeInfo();
        int maxCp = pokemonInfoController.getMaxCpbyName("Pikachu");
        assertThat(maxCp).isEqualTo(950);
    }

    @Test
    void testGetPictureAddress() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setPictureAddress("a");
        inMemoryRepository.add(pokemonInfo);
        String address = pokemonInfoController.getPictureAddress(pokemonInfo.getId());
        assertThat(address).isEqualTo("a");
    }
}
