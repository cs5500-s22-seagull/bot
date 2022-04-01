package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

@Singleton
@Slf4j
public class ClearFriendCommand implements Command {

    @Inject PlayerController playerController;

    @Inject
    public ClearFriendCommand() {}

    @Override
    public String getName() {
        return "clearfriend";
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Ask the bot to show the friends UI");
    }

    @Override
    public void onEvent(CommandInteraction event) {
        log.info("event: /clearfriend");
        playerController.setFriendsForPlayer(event.getUser().getId(), new ArrayList<>());
        event.reply("Your friends list has been cleared").queue();
    }
}
