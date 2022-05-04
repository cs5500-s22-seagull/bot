package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

public class LookUpPokemonCommandTest {
    @Test
    void testGetCommandDataHasDescription() {
        LookUpPokemonCommand command = new LookUpPokemonCommand();
        assertThat(command.getCommandData().getDescription()).isNotNull();
    }

    @Test
    void testGetName() {
        LookUpPokemonCommand command = new LookUpPokemonCommand();
        assertThat(command.getName()).isEqualTo("lookuppokemon");
    }
}
