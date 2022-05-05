package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import org.junit.jupiter.api.Test;

public class ClearFriendCommandTest {

    private PlayerController playerController;

    @Test
    void testClearFriend() {}

    @Test
    void testGetName() {
        ClearFriendCommand clearFriendCommand = new ClearFriendCommand();
        assertThat(clearFriendCommand.getName()).isEqualTo("clearfriend");
    }

    @Test
    void testCommandDataHasDescription() {
        ClearFriendCommand clearFriendCommand = new ClearFriendCommand();
        assertThat(clearFriendCommand.getCommandData().getDescription()).isNotNull();
    }
}
