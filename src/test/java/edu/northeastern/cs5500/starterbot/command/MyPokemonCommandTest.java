package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyPokemonCommandTest {

    private MyPokemonCommand command;

    @BeforeEach
    void setUp() {
        command = new MyPokemonCommand();
    }

    @Test
    void testGetCommandDataHasDescription() {
        assertThat(command.getCommandData().getDescription()).isNotNull();
    }

    @Test
    void testGetName() {
        assertThat(command.getName()).isEqualTo("mypokemon");
    }

    @Test
    void testCreateMenu() {
        assertThat(command.createMenuBuilder(new ArrayList<>()).getOptions()).isEmpty();
    }
}
