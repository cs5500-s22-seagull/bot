package edu.northeastern.cs5500.starterbot.selectionHandler;

import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;

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
        List<String> friendsList = playerController.getFriendIdsForPlayer(event.getUser().getId());
        for (String friendId : friendsList) {
            if (friendId.equals(
                    playerController.getPlayerFromUserId(friendName).getDiscordUserId())) {
                event.reply("You already added this player as a friend.")
                        .setEphemeral(true)
                        .queue();
                return;
            }
        }
        friendsList.add(playerController.getPlayerFromUserId(friendName).getDiscordUserId());
        playerController.setFriendsForPlayer(event.getUser().getId(), friendsList);
        event.reply("Succesfully added your new friend!").setEphemeral(true).queue();
    }
}
