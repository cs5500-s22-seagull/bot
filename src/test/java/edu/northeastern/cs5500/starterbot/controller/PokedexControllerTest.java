package edu.northeastern.cs5500.starterbot.controller;

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
    void testGetSeen() {}
}
