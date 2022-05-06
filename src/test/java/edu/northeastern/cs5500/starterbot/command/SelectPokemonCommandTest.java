package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.PokemonInfoController;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SelectPokemonCommandTest {

    private SelectPokemonCommand command;
    private PokemonInfoController pokemonInfoController;
    private PlayerController playerController;
    private PokemonController pokemonController;

    @BeforeEach
    void setUp() {
        pokemonInfoController = new PokemonInfoController(new InMemoryRepository<>());
        playerController = new PlayerController(new InMemoryRepository<>());
        pokemonController =
                new PokemonController(pokemonInfoController, new InMemoryRepository<>());
        command = new SelectPokemonCommand(playerController, pokemonController);
    }

    @Test
    void testGetCommandDataHasDescription() {
        assertThat(command.getCommandData().getDescription()).isNotNull();
    }

    @Test
    void testGetName() {
        assertThat(command.getName()).isEqualTo("selectpokemon");
    }

    @Test
    void testcreateMenuBuilder() {
        assertThat(command.createMenuBuilder(new ArrayList<>()).getOptions()).isEmpty();
    }
}
