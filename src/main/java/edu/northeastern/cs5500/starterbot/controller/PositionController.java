package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.MapNode;
import edu.northeastern.cs5500.starterbot.model.Player;
import edu.northeastern.cs5500.starterbot.model.PokeMap;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class PositionController {
    private PlayerController playerController;
    private PokeMap pokeMap;

    @Inject
    public PositionController(PlayerController playerController, PokeMap pokeMap) {
        this.playerController = playerController;
        this.pokeMap = pokeMap;
    }

    /**
     * Get possible moves for user
     *
     * @param discordId
     * @return List<MapNode>
     */
    public List<MapNode> getPossibleMovesForUser(String discordId) {
        MapNode node = getPlayerLocation(discordId);
        List<MapNode> currNeighbor = pokeMap.getAdjacentNodes(node);

        return currNeighbor;
    }

    /**
     * Get player's current location
     *
     * @param discordId
     * @return MapNode
     */
    public MapNode getPlayerLocation(String discordId) {
        Player player = playerController.getPlayerFromUserId(discordId);
        Integer location = player.getLocation();
        MapNode node = MapNode.getNodeById(location);

        log.info("Player {} is at {}", discordId, node.getName());
        return node;
    }
}
