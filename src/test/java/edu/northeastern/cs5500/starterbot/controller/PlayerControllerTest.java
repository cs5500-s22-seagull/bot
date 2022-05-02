package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import edu.northeastern.cs5500.starterbot.model.Player;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerControllerTest {

    private PlayerController playerController;

    @BeforeEach
    void setUp() {
        playerController = new PlayerController();
        playerController.playerRepository = new InMemoryRepository<>();
    }

    @Test
    void testGetDateForPlayer() {
        Player player = playerController.getPlayerFromUserId("123");
        Date date = new Date();
        player.setDate(date);
        Date expected = playerController.getStartDateForPlayer("123");
        assertThat(date).isEqualTo(expected);
    }

    @Test
    void testSetLevelForPlayer() {
        playerController.setLevelForPlayer("123", 12);
        Player player = playerController.getPlayerFromUserId("123");
        assertThat(player.getLevel()).isEqualTo(12);
    }

    @Test
    void testSetTotalXPForPlayer() {
        playerController.getPlayerFromUserId("123");
        playerController.setTotalXpForPlayer("123", 12);
        assertEquals(12, playerController.getTotalXpForPlayer("123"));
    }

    @Test
    void testGetPlayerFromMemberId() {
        Player player = playerController.getPlayerFromUserId("123");
        assertEquals(player, playerController.getPlayerFromUserId("123"));
    }

    @Test
    void testHashCode() {
        assertEquals(playerController.hashCode(), playerController.hashCode());
        assertNotEquals(playerController.hashCode(), new PlayerController().hashCode());
    }

    @Test
    void testToString() {
        assertEquals(playerController.toString(), playerController.toString());
    }
}
