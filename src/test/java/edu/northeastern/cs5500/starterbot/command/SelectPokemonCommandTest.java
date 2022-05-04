package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

public class SelectPokemonCommandTest {
    @Test
    void testGetCommandDataHasDescription() {
        SelectPokemonCommand command = new SelectPokemonCommand();
        assertThat(command.getCommandData().getDescription()).isNotNull();
    }

    @Test
    void testGetName() {
        SelectPokemonCommand command = new SelectPokemonCommand();
        assertThat(command.getName()).isEqualTo("selectpokemon");
    }
}
