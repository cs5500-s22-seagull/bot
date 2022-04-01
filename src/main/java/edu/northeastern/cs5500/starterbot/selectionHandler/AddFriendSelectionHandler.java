package edu.northeastern.cs5500.starterbot.selectionHandler;

import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import org.bson.types.ObjectId;

@Slf4j
@Singleton
public class AddFriendSelectionHandler implements SelectionHandler {

    @Inject PlayerController playerController;

    @Inject
    AddFriendSelectionHandler() {}

    @Override
    public String getName() {
        return "menu:friends";
    }

    @Override
    public void onEvent(SelectionMenuEvent event) {
        String friendName = event.getSelectedOptions().get(0).getValue();
        log.info(friendName);
        List<ObjectId> friendsList = playerController.getFriendsForPlayer(event.getUser().getId());
        for (ObjectId objectId : friendsList) {
            if (objectId.equals(playerController.getPlayerFromMemberId(friendName).getId())) {
                event.reply(
                                "You already added "
                                        + playerController.getNameForPlayer(friendName)
                                        + " as a friend")
                        .queue();
                return;
            }
        }
        friendsList.add(playerController.getPlayerFromMemberId(friendName).getId());
        playerController.setFriendsForPlayer(event.getUser().getId(), friendsList);
        event.reply(
                        "Succesfully added "
                                + playerController.getNameForPlayer(friendName)
                                + " as a friend")
                .queue();
        ;
    }
}
