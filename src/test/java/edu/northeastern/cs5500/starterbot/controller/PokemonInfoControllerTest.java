package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.model.PokemonInfo;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokemonInfoControllerTest {
    private GenericRepository<PokemonInfo> pokemonInfoRepository;
    private PokemonInfoController pokemonInfoController;

    @BeforeEach
    void setUp() {
        // TODO: this is not a good idea, you shouldn't need to manipulate the repository directly
        // to test the controllers
        pokemonInfoRepository = new InMemoryRepository<>();
        pokemonInfoController = new PokemonInfoController(pokemonInfoRepository);
    }

    @Test
    void testGetMaxCpbyName() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setMaxCP(1233);
        pokemonInfo.setPokemonName("pokemonName");
        pokemonInfoRepository.add(pokemonInfo);
        assertThat(pokemonInfoController.getMaxCpbyName("pokemonName")).isEqualTo(1233);
    }

    @Test
    void testGetPictureAddress() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setPictureAddress("a");
        pokemonInfoRepository.add(pokemonInfo);
        String address = pokemonInfoController.getPictureAddress(pokemonInfo.getId());
        assertThat(address).isEqualTo("a");
    }

    @Test
    void testGetName() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setPokemonName("pokemonName");
        pokemonInfoRepository.add(pokemonInfo);
        assertThat(pokemonInfoController.getName(pokemonInfo.getId())).isEqualTo("pokemonName");
    }

    @Test
    void testGetMaxHp() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setMaxHP(1234);
        pokemonInfoRepository.add(pokemonInfo);
        assertThat(pokemonInfoController.getMaxHp(pokemonInfo.getId())).isEqualTo(1234);
    }

    @Test
    void testGetImage() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setPictureAddress("PictureAddress");
        pokemonInfoRepository.add(pokemonInfo);
        assertThat(pokemonInfoController.getPictureAddress(pokemonInfo.getId()))
                .isEqualTo("PictureAddress");
    }

    @Test
    void testUpdateRepo() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setPokemonName("pokemonName");
        pokemonInfoRepository.add(pokemonInfo);
        pokemonInfo.setPokemonName("newPokemonName");
        pokemonInfoController.updateRepo(pokemonInfo);
        assertThat(pokemonInfoController.getName(pokemonInfo.getId())).isEqualTo("newPokemonName");
    }

    @Test
    void testAddToRepo() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setPokemonName("pokemonName");
        pokemonInfoController.addToRepo(pokemonInfo);
        assertThat(pokemonInfoController.getName(pokemonInfo.getId())).isEqualTo("pokemonName");
    }
}
