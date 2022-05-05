package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClearFriendCommandTest {

    private PlayerController playerController;
    private ClearFriendCommand clearFriendCommand;

    @BeforeEach
    void setUp() {
        playerController = new PlayerController(new InMemoryRepository<>());
        clearFriendCommand = new ClearFriendCommand(playerController);
    }

    @Test
    void testClearFriend() {}

    @Test
    void testGetName() {
        assertThat(clearFriendCommand.getName()).isEqualTo("clearfriend");
    }

    @Test
    void testCommandDataHasDescription() {
        assertThat(clearFriendCommand.getCommandData().getDescription()).isNotNull();
    }
}
