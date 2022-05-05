package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import java.util.Arrays;
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
    void testClearFriend() {
        playerController.getPlayerFromUserId("userId");
        playerController.setFriendsForPlayer("userId", Arrays.asList("friend1", "friend2"));
        assertThat(playerController.getFriendIdsForPlayer("userId")).isNotEmpty();
        clearFriendCommand.clearFriend("userId");
        assertThat(playerController.getFriendIdsForPlayer("userId")).isEmpty();
    }

    @Test
    void testGetName() {
        assertThat(clearFriendCommand.getName()).isEqualTo("clearfriend");
    }

    @Test
    void testCommandDataHasDescription() {
        assertThat(clearFriendCommand.getCommandData().getDescription()).isNotNull();
    }
}
