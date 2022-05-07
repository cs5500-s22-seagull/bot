package edu.northeastern.cs5500.starterbot.selectionHandler;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;

/** > This class handles the selection of the "Add Friend" button */
@Slf4j
@Singleton
public class AddFriendSelectionHandler implements SelectionHandler {

    @Inject PlayerController playerController;

    @Inject
    AddFriendSelectionHandler() {}

    /**
     * This function returns the name of the menu.
     *
     * @return The name of the menu.
     */
    @Override
    public String getName() {
        return "menu:friends";
    }

    /**
     * This function is called when a user selects a friend from the list of friends. It checks if
     * the user has already added the friend, and if not, adds the friend to the user's list of
     * friends
     *
     * @param event The event that triggered the command.
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public void onEvent(SelectionMenuEvent event) {
        String newFriendName = event.getSelectedOptions().get(0).getValue();
        List<String> friendsList = playerController.getFriendIdsForPlayer(event.getUser().getId());

        if (ifAlreadyFriend(newFriendName, friendsList, playerController) == true) {
            event.reply("You already added this player as a friend.").setEphemeral(true).queue();
            return;
        } else {
            String myId = event.getUser().getId();
            addCurrFriend(myId, newFriendName, friendsList, playerController);
            event.reply("Succesfully added your new friend!").setEphemeral(true).queue();
        }
        return;
    }

    /**
     * This function adds a new friend to the friends list of the player with the given id.
     *
     * @param myId The user id of the player who is adding a friend
     * @param newFriendName The name of the friend you want to add.
     * @param friendsList The list of friends that the player has.
     * @param playerController The PlayerController object that is used to get the player from the
     *     userId
     */
    public void addCurrFriend(
            String myId,
            String newFriendName,
            List<String> friendsList,
            PlayerController playerController) {
        friendsList.add(playerController.getPlayerFromUserId(newFriendName).getDiscordUserId());
        playerController.setFriendsForPlayer(myId, friendsList);
    }

    /**
     * > This function checks if the new friend is already in the friends list
     *
     * @param newFriendName The name of the friend you want to add.
     * @param friendsList The list of friends that the player has.
     * @param playerController The PlayerController object that is used to get the player object
     *     from the user id.
     * @return A boolean value.
     */
    public boolean ifAlreadyFriend(
            String newFriendName, List<String> friendsList, PlayerController playerController) {
        for (String friendId : friendsList) {
            if (friendId.equals(
                    playerController.getPlayerFromUserId(newFriendName).getDiscordUserId())) {
                return true;
            }
        }
        return false;
    }
}
