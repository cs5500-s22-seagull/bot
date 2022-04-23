package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.junit.jupiter.api.Test;

class RegisterCommandTest {
    @Test
    void testNameMatchesData() {
        RegisterCommand registerCommand = new RegisterCommand();
        String name = registerCommand.getName();
        CommandData commandData = registerCommand.getCommandData();

        assertThat(name).isEqualTo(commandData.getName());
    }
}
