package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

public class FightCommandTest {
    @Test
    void testGetCommandDataHasDescription() {
        FightCommand command = new FightCommand();
        assertThat(command.getCommandData().getDescription()).isNotNull();
    }

    @Test
    void testGetName() {
        FightCommand command = new FightCommand();
        assertThat(command.getName()).isEqualTo("fight");
    }
}
