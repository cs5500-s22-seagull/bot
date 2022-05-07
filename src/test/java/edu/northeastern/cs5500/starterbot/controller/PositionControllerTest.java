package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.model.MapNode;
import edu.northeastern.cs5500.starterbot.model.PokeMap;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionControllerTest {

    private PlayerController playerController;
    private PositionController positionController;

    @BeforeEach
    void setUp() {
        playerController = new PlayerController(new InMemoryRepository<>());
        positionController = new PositionController(playerController, new PokeMap());
    }

    @Test
    void testGetPossibleMovesForUser() {
        playerController.getPlayerFromUserId("123");
        List<MapNode> currNeighbor = positionController.getPossibleMovesForUser("123");
        assertThat(currNeighbor.size()).isEqualTo(3);
    }

    @Test
    void testGetPlayerLocation() {
        playerController.getPlayerFromUserId("123");
        MapNode node = positionController.getPlayerLocation("123");
        assertThat(node.getName()).isEqualTo("Tsukushi");
    }
}
