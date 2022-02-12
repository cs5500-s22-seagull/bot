package edu.northeastern.cs5500.starterbot.command;

import javax.inject.Inject;
import javax.inject.Singleton;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

@Singleton
public class SayCommand implements Command {

    @Inject
    public SayCommand() {}

    @Override
    public String getName() {
        return "say";
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Ask the bot to reply with the provided text")
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "content",
                                        "The bot will reply to your command with the provided text")
                                .setRequired(true));
    }

    @Override
    public void onEvent(CommandInteraction event) {
        event.reply(event.getOption("content").getAsString()).queue();
    }
}
