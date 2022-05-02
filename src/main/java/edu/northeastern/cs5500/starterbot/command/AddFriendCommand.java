package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu.Builder;

@Singleton
@Slf4j
public class AddFriendCommand implements Command {

    @Inject PlayerController playerController;

    @Inject
    public AddFriendCommand() {}

    @Override
    public String getName() {
        return "addfriend";
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Look for new friends to add");
    }

    @Override
    public void onEvent(CommandInteraction event) {
        log.info("event: /addfriend");

        Collection<String> friendsId = playerController.getAllPlayerMemberId();
        Builder menuBuilder =
                SelectionMenu.create("menu:friends").setPlaceholder("Choose a player");
        ArrayList<CompletableFuture<User>> lookups = new ArrayList<>();
        String currentUserId = event.getUser().getId();
        List<String> currentFriends = playerController.getFriendIdsForPlayer(currentUserId);
        JDA jda = event.getJDA();
        for (String friendId : friendsId) {
            if (currentUserId.equals(friendId)) {
                continue;
            }
            if (currentFriends.contains(friendId)) {
                continue;
            }
            if (lookups.size() >= 25) {
                // Can only show up to 25 options in a menu
                break;
            }
            lookups.add(jda.retrieveUserById(friendId).submit());
        }

        CompletableFuture<?>[] lookupArray = new CompletableFuture<?>[lookups.size()];
        lookups.toArray(lookupArray);

        CompletableFuture.allOf(lookupArray)
                .thenApply(
                        ignored -> {
                            List<User> users =
                                    lookups.stream()
                                            .map(future -> future.join())
                                            .collect(Collectors.toList());

                            for (User user : users) {
                                menuBuilder.addOption(user.getName(), user.getId());
                            }
                            event.deferReply(true).addActionRow(menuBuilder.build()).queue();
                            return null;
                        });
    }
}
