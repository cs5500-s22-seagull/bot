package edu.northeastern.cs5500.starterbot.selectionHandler;

import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import javax.inject.Inject;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;

public class AddFriendSelectionHandler implements SelectionHandler {

    @Inject PlayerController playerController;

    @Inject
    AddFriendSelectionHandler() {}

    @Override
    public String getName() {
        return "addfriend";
    }

    @Override
    public void onEvent(SelectionMenuEvent event) {
        // TODO: add friend to user's friend list

    }
}
