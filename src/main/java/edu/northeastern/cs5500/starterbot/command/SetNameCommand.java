/** This class is used to provide command that sets the name of the user */
package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

@Singleton
@Slf4j
public class SetNameCommand implements Command {

    @Inject PlayerController playerController;

    @Inject
    public SetNameCommand() {}

    /**
     * It returns the name of the command.
     *
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "setname";
    }

    /**
     * It returns a new CommandData object with the name of the command, and a description of what
     * it does
     *
     * @return The command data object.
     */
    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Ask the bot to set your name")
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "name",
                                        "The bot will use this name to address you")
                                .setRequired(true));
    }

    /**
     * This function is called when the user types the command /setname and allows the user to set
     * their name
     *
     * @param event The CommandInteraction object that was passed to the command.
     */
    @Override
    public void onEvent(CommandInteraction event) {
        log.info("event: /setname");

        String name = event.getOption("name").getAsString();
        String discordUserId = event.getUser().getId();
        String oldName = playerController.getNameForPlayer(discordUserId);
        playerController.setNameForPlayer(discordUserId, name);
        if (oldName == null) {
            event.reply("Your name has been set to " + name).queue();
        } else {
            event.reply("Your name has been changed from " + oldName + " to " + name).queue();
        }
        log.info("name: " + playerController.getNameForPlayer(discordUserId));
        log.info("discordUserID: " + discordUserId);
    }
}
