package edu.northeastern.cs5500.starterbot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import edu.northeastern.cs5500.starterbot.model.Player;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
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
    void testEquals() {
        assertEquals(playerController, playerController);
        assertNotEquals(playerController, new PlayerController());
    }

    @Test
    void testGetNameForPlayer() {
        Player player = playerController.getPlayerFromMemberId("123");
        player.setName("name1");
        assertEquals("name1", playerController.getNameForPlayer("123"));
    }

    @Test
    void testGetPlayerFromMemberId() {
        Player player = playerController.getPlayerFromMemberId("123");
        assertEquals(player, playerController.getPlayerFromMemberId("123"));
    }

    @Test
    void testGetPlayerRepository() {
        GenericRepository<Player> repo = playerController.playerRepository;
        GenericRepository<Player> newRepo = new InMemoryRepository<>();
        assertNotEquals(repo, newRepo);
        playerController.playerRepository = newRepo;
        assertEquals(newRepo, playerController.getPlayerRepository());
    }

    @Test
    void testHashCode() {
        assertEquals(playerController.hashCode(), playerController.hashCode());
        assertNotEquals(playerController.hashCode(), new PlayerController().hashCode());
    }

    @Test
    void testSetNameForPlayer() {
        playerController.setNameForPlayer("veryGoodId", "veryGoodName");
        assertEquals("veryGoodName", playerController.getNameForPlayer("veryGoodId"));
    }

    @Test
    void testSetPlayerRepository() {
        assertEquals(playerController.playerRepository, playerController.getPlayerRepository());
    }

    @Test
    void testToString() {
        assertEquals(playerController.toString(), playerController.toString());
    }
}
