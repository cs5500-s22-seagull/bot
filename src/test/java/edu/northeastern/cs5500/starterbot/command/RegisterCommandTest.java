package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

public class RegisterCommandTest {
    @Test
    void testGetCommandDataHasDescription() {
        RegisterCommand command = new RegisterCommand();
        assertThat(command.getCommandData().getDescription()).isNotNull();
    }

    @Test
    void testGetName() {
        RegisterCommand command = new RegisterCommand();
        assertThat(command.getName()).isEqualTo("register");
    }
}
