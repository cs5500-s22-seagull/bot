package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
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
        return new CommandData(getName(), "Ask the bot to show the friends UI");
    }

    @Override
    public void onEvent(CommandInteraction event) {
        log.info("event: /addfriend");

        Collection<String> friendsId = playerController.getAllPlayerMemberId();
        Builder menu = SelectionMenu.create("menu:friends").setPlaceholder("Choose a player");
        for (String string : friendsId) {
            if (string.equals(event.getUser().getId())) {
                continue;
            }
            menu.addOption(playerController.getNameForPlayer(string), string);
        }
        event.deferReply().addActionRow(menu.build()).queue();
    }
}
