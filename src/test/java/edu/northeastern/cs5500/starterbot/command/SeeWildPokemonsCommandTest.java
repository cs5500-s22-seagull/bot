package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

public class SeeWildPokemonsCommandTest {
    @Test
    void testGetCommandDataHasDescription() {
        SeeWildPokemonsCommand command = new SeeWildPokemonsCommand();
        assertThat(command.getCommandData().getDescription()).isNotNull();
    }

    @Test
    void testGetName() {
        SeeWildPokemonsCommand command = new SeeWildPokemonsCommand();
        assertThat(command.getName()).isEqualTo("seewild");
    }
}
