package edu.northeastern.cs5500.starterbot.selectionHandler;

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

    @Override
    public String getName() {
        return "menu:nextnode";
    }

    @Override
    public void onEvent(SelectionMenuEvent event) {
        log.info("curr loc:" + event.getSelectedOptions().get(0).getValue());
        Integer chosenLocation = Integer.valueOf(event.getSelectedOptions().get(0).getValue());
        MapNode chosenNode = MapNode.getNodeById(chosenLocation);
        log.info("chosen neighbor:" + chosenNode.getName());

        String discordId = event.getUser().getId();
        playerController.moveToNode(discordId, chosenLocation);
        event.reply(String.format("You have moved to %s.", chosenNode.getName())).queue();
    }
}
