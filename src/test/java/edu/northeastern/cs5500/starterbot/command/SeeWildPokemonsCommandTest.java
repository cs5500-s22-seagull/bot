package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SeeWildPokemonsCommandTest {

    private SeeWildPokemonsCommand command;

    @BeforeEach
    void setUp() {
        command = new SeeWildPokemonsCommand();
    }

    @Test
    void testGetCommandDataHasDescription() {
        assertThat(command.getCommandData().getDescription()).isNotNull();
    }

    @Test
    void testGetName() {
        assertThat(command.getName()).isEqualTo("seewild");
    }

    @Test
    void testCreateMenu() {
        assertThat(command.createMenuBuilder(new ArrayList<>()).getOptions()).isEmpty();
        ArrayList<String> poke = new ArrayList<>();
        poke.add("poke1");
        poke.add("poke2");
        assertThat(command.createMenuBuilder(poke).getOptions().size()).isEqualTo(2);
    }
}
