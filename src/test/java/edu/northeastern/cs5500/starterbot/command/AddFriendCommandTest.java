package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.Test;

public class AddFriendCommandTest {
    @Test
    void testGetCommandDataHasDescription() {
        AddFriendCommand addFriendCommand = new AddFriendCommand();
        assertThat(addFriendCommand.getCommandData().getDescription()).isNotNull();
    }

    @Test
    void testGetName() {
        AddFriendCommand addFriendCommand = new AddFriendCommand();
        assertThat(addFriendCommand.getName()).isEqualTo("addfriend");
    }
}
