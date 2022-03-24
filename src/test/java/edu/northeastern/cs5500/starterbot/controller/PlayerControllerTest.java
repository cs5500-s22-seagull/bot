package edu.northeastern.cs5500.starterbot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import edu.northeastern.cs5500.starterbot.model.Player;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
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
    void testGetDateForPlayer() {
        Player player = playerController.getPlayerFromMemberId("123");
        Date date = new Date();
        player.setDate(date);
        assertEquals(date, playerController.getStartDateForPlayer("123"));
    }

    @Test
    void testGetLevelForPlayer() {
        Player player = playerController.getPlayerFromMemberId("123");
        player.setLevel(12);
        assertEquals(12, playerController.getLevelForPlayer("123"));
    }

    @Test
    void testSetLevelForPlayer() {
        playerController.getPlayerFromMemberId("123");
        playerController.setLevelForPlayer("123", 12);
        assertEquals(12, playerController.getLevelForPlayer("123"));
    }

    @Test
    void testGetTotalXPForPlayer() {
        Player player = playerController.getPlayerFromMemberId("123");
        player.setTotalXP(12);
        assertEquals(12, playerController.getTotalXpForPlayer("123"));
    }

    @Test
    void testSetTotalXPForPlayer() {
        playerController.getPlayerFromMemberId("123");
        playerController.setTotalXpForPlayer("123", 12);
        assertEquals(12, playerController.getTotalXpForPlayer("123"));
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
