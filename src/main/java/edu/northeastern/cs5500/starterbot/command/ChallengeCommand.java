/** This class is used to provide command that sets the name of the user */
package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeClassFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import java.util.ArrayList;
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

@Singleton
@Slf4j
@ExcludeClassFromJacocoGeneratedReport
public class ChallengeCommand implements Command {

    @Inject PlayerController playerController;

    @Inject
    public ChallengeCommand() {}

    /**
     * It returns the name of the command.
     *
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "challenge";
    }

    /**
     * It returns a new CommandData object with the name of the command, and a description of what
     * it does
     *
     * @return The command data object.
     */
    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Challenge a friend");
    }

    /**
     * This function is called when the user types the command /challenge; this command is used to
     * challenge a friend to a battle.
     *
     * @param event The CommandInteraction object that was passed to the command.
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public void onEvent(CommandInteraction event) {
        log.info("event: /challenge");

        List<String> friendIds =
                playerController.getPlayerFromUserId(event.getUser().getId()).getFriends();

        lookupUsersById(event, friendIds);
    }

    @ExcludeFromJacocoGeneratedReport
    private void lookupUsersById(CommandInteraction event, List<String> friendIds) {
        List<CompletableFuture<User>> lookups = new ArrayList<>();
        JDA jda = event.getJDA();
        for (String friendId : friendIds) {
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
                            SelectionMenu menu = createBattleMenu(users);
                            event.deferReply(true).addActionRow(menu).queue();
                            return null;
                        });
    }

    @ExcludeFromJacocoGeneratedReport
    SelectionMenu createBattleMenu(List<User> friends) {
        SelectionMenu.Builder menu =
                SelectionMenu.create("menu:challenge")
                        .setPlaceholder("Choose a friend to challenge");

        for (User user : friends) {
            menu.addOption(user.getName(), user.getId());
            if (menu.getOptions().size() >= 25) {
                break;
            }
        }
        return menu.build();
    }
}
