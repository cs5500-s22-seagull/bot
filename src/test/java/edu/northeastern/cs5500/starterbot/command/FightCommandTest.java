package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.controller.CombatController;
import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.PokemonInfoController;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FightCommandTest {

    private PlayerController playerController;
    private CombatController combatController;
    private PokemonController pokemonController;
    private FightCommand command;

    @BeforeEach
    void setUp() {
        PokemonInfoController pokemonInfoController =
                new PokemonInfoController(new InMemoryRepository<>());
        playerController = new PlayerController(new InMemoryRepository<>());
        pokemonController =
                new PokemonController(pokemonInfoController, new InMemoryRepository<>());
        combatController = new CombatController(new InMemoryRepository<>());
        command = new FightCommand(playerController, combatController, pokemonController);
    }

    @Test
    void testGetCommandDataHasDescription() {
        assertThat(command.getCommandData().getDescription()).isNotNull();
    }

    @Test
    void testGetName() {
        assertThat(command.getName()).isEqualTo("fight");
    }
}
