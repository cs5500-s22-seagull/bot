package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.model.Pokedex;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokedexControllerTest {

    private PokedexController pokedexController;

    @BeforeEach
    void setUp() {
        pokedexController = new PokedexController(new InMemoryRepository<>());
    }

    @Test
    void testGetSeen() {
        Pokedex pokedex = new Pokedex();
        pokedex.setSeen(10);
        pokedexController.addToRepo(pokedex);
        assertThat(pokedexController.getSeen(pokedex.getId())).isEqualTo(10);
    }

    @Test
    void testGetCaught() {
        Pokedex pokedex = new Pokedex();
        pokedex.setCaught(9);
        pokedexController.addToRepo(pokedex);
        assertThat(pokedexController.getCaught(pokedex.getId())).isEqualTo(9);
    }
}
