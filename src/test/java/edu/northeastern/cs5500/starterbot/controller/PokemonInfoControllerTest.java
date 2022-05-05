package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.model.PokemonInfo;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokemonInfoControllerTest {

    private PokemonInfoController pokemonInfoController;

    @BeforeEach
    void setUp() {
        pokemonInfoController = new PokemonInfoController();
        pokemonInfoController.pokemonInfoRepository = new InMemoryRepository<>();
    }

    @Test
    void testGetMaxCpbyName() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setMaxCP(1233);
        pokemonInfo.setPokemonName("pokemonName");
        pokemonInfoController.pokemonInfoRepository.add(pokemonInfo);
        assertThat(pokemonInfoController.getMaxCpbyName("pokemonName")).isEqualTo(1233);
    }

    @Test
    void testGetPictureAddress() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setPictureAddress("a");
        pokemonInfoController.pokemonInfoRepository.add(pokemonInfo);
        String address = pokemonInfoController.getPictureAddress(pokemonInfo.getId());
        assertThat(address).isEqualTo("a");
    }

    @Test
    void testGetName() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setPokemonName("pokemonName");
        pokemonInfoController.pokemonInfoRepository.add(pokemonInfo);
        assertThat(pokemonInfoController.getName(pokemonInfo.getId())).isEqualTo("pokemonName");
    }

    @Test
    void testGetMaxHp() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setMaxHP(1234);
        pokemonInfoController.pokemonInfoRepository.add(pokemonInfo);
        assertThat(pokemonInfoController.getMaxHp(pokemonInfo.getId())).isEqualTo(1234);
    }

    @Test
    void testGetImage() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setPictureAddress("PictureAddress");
        pokemonInfoController.pokemonInfoRepository.add(pokemonInfo);
        assertThat(pokemonInfoController.getPictureAddress(pokemonInfo.getId()))
                .isEqualTo("PictureAddress");
    }
}
