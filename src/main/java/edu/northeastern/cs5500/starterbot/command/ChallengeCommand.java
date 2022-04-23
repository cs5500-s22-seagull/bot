/** This class is used to provide command that sets the name of the user */
package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import edu.northeastern.cs5500.starterbot.model.Player;
import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;

@Singleton
@Slf4j
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
    public void onEvent(CommandInteraction event) {
        log.info("event: /challenge");

        // TODO: if you have more than 25 friends, menu creation will crash.
        // You probably want to do something different...
        Collection<Player> friends = playerController.getFriendsForPlayer(event.getUser().getId());

        SelectionMenu menu = createBattleMenu(friends);

        event.deferReply(true).addActionRow(menu).queue();
    }

    SelectionMenu createBattleMenu(Collection<Player> friends) {
        SelectionMenu.Builder menu =
                SelectionMenu.create("menu:challenge")
                        .setPlaceholder("Choose a friend to challenge");
        for (Player p : friends) {
            menu.addOption(p.getName() + " (" + p.getDiscordName() + ")", p.getDiscordMemberId());
        }
        return menu.build();
    }
}
