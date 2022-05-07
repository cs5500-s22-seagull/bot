package edu.northeastern.cs5500.starterbot.selectionHandler;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.controller.*;
import edu.northeastern.cs5500.starterbot.model.*;
import edu.northeastern.cs5500.starterbot.repository.*;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SelectPokemonSelectionHandlerTest {
    private SelectPokemonSelectionHandler selectPokemonSelectionHandler;
    private PlayerController playerController;
    private Player player1;
    private Pokemon pokemon;
    private PokemonController pokemonController;
    private PokemonInfoController pokemonInfoController;
    private ObjectId pid;

    @BeforeEach
    void setUp() {
        selectPokemonSelectionHandler = new SelectPokemonSelectionHandler();
        playerController = new PlayerController(new InMemoryRepository<>());
        player1 = playerController.getPlayerFromUserId("12345");
        pokemonInfoController = new PokemonInfoController(new InMemoryRepository<>());
        pokemonController =
                new PokemonController(pokemonInfoController, new InMemoryRepository<>());
        pokemon = new Pokemon();
        player1 = playerController.getPlayerFromUserId("12345");
        pokemonController.addPokemon(pokemon);
        ObjectId pid;
    }

    @Test
    void testGetName() {
        assertThat(selectPokemonSelectionHandler.getName()).isEqualTo("menu:selectpokemon");
    }

    @Test
    void testSetSelectedPokemon() {
        pid = pokemon.getId();
        selectPokemonSelectionHandler.setSelectedPokemon(pid, "12345", playerController);
        ObjectId selectedId = playerController.getPlayerFromUserId("12345").getSelectedPokemon();
        Pokemon selectedPokemon = pokemonController.getPokemonByObjectId(selectedId);
        assertThat(selectedPokemon.getCp()).isEqualTo(pokemon.getCp());
        assertThat(selectedPokemon.getHp()).isEqualTo(pokemon.getHp());
        assertThat(selectedPokemon.getCurrentHp()).isEqualTo(pokemon.getCurrentHp());
        assertThat(selectedPokemon.getLevel()).isEqualTo(pokemon.getLevel());
    }
}
