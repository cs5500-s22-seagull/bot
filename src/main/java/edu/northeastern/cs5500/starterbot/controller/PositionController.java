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
    // @Inject GenericRepository<PokeMap> positionRepository;
    @Inject PlayerController playerController;
    @Inject PokeMap pokeMap;

    @Inject
    PositionController() {}

    public List<MapNode> getPossibleMovesForUser(String discordId) {
        MapNode node = getPlayerLocation(discordId);
        List<MapNode> currNeighbor = pokeMap.getAdjacentNodes(node);

        return currNeighbor;
    }

    public MapNode getPlayerLocation(String discordId) {
        Player player = playerController.getPlayerFromUserId(discordId);
        Integer location = player.getLocation();
        MapNode node = MapNode.getNodeById(location);

        log.info("Player {} is at {}", discordId, node.getName());
        return node;
    }
}
