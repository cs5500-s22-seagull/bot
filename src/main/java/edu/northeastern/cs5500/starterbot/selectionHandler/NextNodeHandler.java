package edu.northeastern.cs5500.starterbot.selectionHandler;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import edu.northeastern.cs5500.starterbot.controller.PositionController;
import edu.northeastern.cs5500.starterbot.model.MapNode;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;

@Slf4j
@Singleton
public class NextNodeHandler implements SelectionHandler {

    @Inject PositionController positionController;
    @Inject PlayerController playerController;

    @Inject
    NextNodeHandler() {}

    /**
     * Return the name of the menu item.
     *
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "menu:nextnode";
    }

    /**
     * > When a user selects a location from the menu, move the user to that location
     *
     * @param event The event that triggered the command.
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public void onEvent(SelectionMenuEvent event) {
        log.info("curr loc:" + event.getSelectedOptions().get(0).getValue());
        Integer chosenLocation = Integer.valueOf(event.getSelectedOptions().get(0).getValue());
        MapNode chosenNode = MapNode.getNodeById(chosenLocation);
        log.info("chosen neighbor:" + chosenNode.getName());

        String discordId = event.getUser().getId();

        move(discordId, chosenLocation, playerController);
        event.reply(String.format("You have moved to %s.", chosenNode.getName())).queue();
    }

    /**
     * Move the player with the given discordId to the node with the given chosenLocation.
     *
     * @param discordId The discord id of the player who is moving.
     * @param chosenLocation The location the player wants to move to.
     */
    public void move(String discordId, Integer chosenLocation, PlayerController playerController) {
        playerController.moveToNode(discordId, chosenLocation);
    }
}
