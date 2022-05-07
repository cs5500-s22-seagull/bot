package edu.northeastern.cs5500.starterbot.selectionHandler;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.controller.*;
import edu.northeastern.cs5500.starterbot.model.*;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NextNodeHandlerTest {
    private NextNodeHandler nextNodeHandler;
    private PlayerController playerController;
    private Player player1;

    @BeforeEach
    void setUp() {
        nextNodeHandler = new NextNodeHandler();
        playerController = new PlayerController(new InMemoryRepository<>());
        player1 = playerController.getPlayerFromUserId("12345");
    }

    @Test
    void testGetName() {
        assertThat(nextNodeHandler.getName()).isEqualTo("menu:nextnode");
    }

    @Test
    void testMove() {
        player1.setLocation(1);
        nextNodeHandler.move("12345", 2, playerController);
        assertThat(player1.getLocation()).isEqualTo(2);
    }
}
