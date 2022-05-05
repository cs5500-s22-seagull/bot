package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

public class MovePositionCommandTest {
    @Test
    void testGetCommandDataHasDescription() {
        MovePositionCommand command = new MovePositionCommand();
        assertThat(command.getCommandData().getDescription()).isNotNull();
    }

    @Test
    void testGetName() {
        MovePositionCommand command = new MovePositionCommand();
        assertThat(command.getName()).isEqualTo("moveposition");
    }
}
