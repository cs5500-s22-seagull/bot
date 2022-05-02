package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

@Singleton
@Slf4j
public class RegisterCommand implements Command {

    @Inject PlayerController playerController;

    @Inject
    public RegisterCommand() {}

    @Override
    public String getName() {
        return "register";
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Register new user to the game");
    }

    @Override
    public void onEvent(CommandInteraction event) {
        log.info("event: /register");
        String userId = event.getUser().getId();
        playerController.getPlayerFromUserId(userId);
        event.reply("You have been registered").setEphemeral(true).queue();
    }
}
