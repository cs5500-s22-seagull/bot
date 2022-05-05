package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

public class ChallengeCommandTest {
    @Test
    void testGetCommandDataHasDescription() {
        ChallengeCommand command = new ChallengeCommand();
        assertThat(command.getCommandData().getDescription()).isNotNull();
    }

    @Test
    void testGetName() {
        ChallengeCommand command = new ChallengeCommand();
        assertThat(command.getName()).isEqualTo("challenge");
    }
}
