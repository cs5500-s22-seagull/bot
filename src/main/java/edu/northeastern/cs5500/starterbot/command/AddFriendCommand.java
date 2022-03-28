package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;

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

        EmbedBuilder embedBuilder = new EmbedBuilder();

        event.deferReply()
                .addActionRow(
                        Button.primary("addfriend", "Add Selected as Friend"),
                        Button.primary("removefriend", "Remove Selected as Friend"))
                .addActionRow(
                        SelectionMenu.create("menu:friends")
                                .setPlaceholder("Choose a player")
                                .addOption("Arcane Mage", "mage-arcane")
                                .addOption("Fire Mage", "mage-fire")
                                .addOption("Frost Mage", "mage-frost")
                                .build())
                .addEmbeds(embedBuilder.addField("one", "two", false).build())
                .queue();
    }
}
