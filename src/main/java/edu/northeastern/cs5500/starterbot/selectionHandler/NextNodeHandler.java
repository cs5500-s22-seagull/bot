package edu.northeastern.cs5500.starterbot.selectionHandler;

import edu.northeastern.cs5500.starterbot.controller.CatchController;
import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import edu.northeastern.cs5500.starterbot.controller.PokedexController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.PositionController;
import edu.northeastern.cs5500.starterbot.model.MapNode;

import java.util.ArrayList;
import java.util.HashMap;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.mongodb.client.model.geojson.Position;

import org.bson.types.ObjectId;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu.Builder;

@Slf4j
@Singleton
public class NextNodeHandler implements SelectionHandler {

    @Inject PlayerController playerController;
    @Inject PokedexController pokedexController;
    @Inject PokemonController pokemonController;
    @Inject PositionController positionController;
    @Inject CatchController catchController;

    @Inject
    NextNodeHandler() {}

    @Override
    public String getName() {
        return "menu:nextnode";
    }

    @Override
    public void onEvent(SelectionMenuEvent event) {
        Integer chosenNeighborChoice = Integer.valueOf(event.getSelectedOptions().get(0).getValue());
        MapNode chosenNeighbor = positionController.getNodeByID(chosenNeighborChoice);
        log.info("chosen neighbor:" + chosenNeighbor.getLocName());
        String discordId = event.getUser().getId();
        if(chosenNeighborChoice>0 && chosenNeighborChoice<9){
        positionController.MoveToNode(discordId, chosenNeighborChoice);
        event.reply(
            "Successfully move to "
            + chosenNeighbor.getLocName()
            + "!").queue();
        }
    }
}
