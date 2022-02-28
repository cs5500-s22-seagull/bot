package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.UserPreferenceController;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

@Singleton
@Slf4j
public class PreferredNameCommand implements Command {

    @Inject UserPreferenceController userPreferenceController;

    @Inject
    public PreferredNameCommand() {}

    @Override
    public String getName() {
        return "preferredname";
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Tell the bot what name to address you with")
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "name",
                                        "The bot will use this name to talk to you going forward")
                                .setRequired(true));
    }

    @Override
    public void onEvent(CommandInteraction event) {
        log.info("event: /preferredname");
        String preferredName = event.getOption("name").getAsString();

        String discordUserId = event.getUser().getId();

        String oldPreferredName = userPreferenceController.getPreferredNameForUser(discordUserId);

        userPreferenceController.setPreferredNameForUser(discordUserId, preferredName);

        if (oldPreferredName == null) {
            event.reply("Your preferred name has been set to " + preferredName).queue();
        } else {
            event.reply(
                            "Your preferred name has been changed from "
                                    + oldPreferredName
                                    + " to "
                                    + preferredName)
                    .queue();
        }
    }
}
