package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import edu.northeastern.cs5500.starterbot.controller.PositionController;
import edu.northeastern.cs5500.starterbot.model.MapNode;
import edu.northeastern.cs5500.starterbot.model.PokeMap;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovePositionCommandTest {

    private PositionController positionController;
    private PlayerController playerController;
    private MovePositionCommand command;

    @BeforeEach
    void setUp() {
        playerController = new PlayerController(new InMemoryRepository<>());
        positionController = new PositionController(playerController, new PokeMap());
        command = new MovePositionCommand(positionController);
    }

    @Test
    void testGetCommandDataHasDescription() {
        assertThat(command.getCommandData().getDescription()).isNotNull();
    }

    @Test
    void testGetName() {
        assertThat(command.getName()).isEqualTo("moveposition");
    }

    @Test
    void testCreateMenuBuilder() {
        MapNode node = MapNode.BUNGO;
        List<MapNode> neighbors = new ArrayList<MapNode>();
        assertThat(command.createMenuBuilder(neighbors).getOptions()).isEmpty();
        neighbors.add(node);
        assertThat(command.createMenuBuilder(neighbors).getOptions().size()).isEqualTo(1);
    }

    @Test
    void testCreateEmbedBuilder() {
        assertThat(command.createEmbedBuilder(MapNode.BUNGO).getFields().size()).isEqualTo(1);
    }
}
