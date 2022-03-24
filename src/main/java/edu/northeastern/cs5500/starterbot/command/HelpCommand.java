package edu.northeastern.cs5500.starterbot.command;

import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

@Singleton
@Slf4j
public class HelpCommand implements Command {

    @Inject
    public HelpCommand() {}

    @Override
    public String getName() {
        return "help";
    }

    // @Override
    // public CommandData getCommandData() {
    //     return new CommandData(getName(), "Ask the bot to reply with the provided text");
    // }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Ask the bot to reply with the provided text")
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "content",
                                        "The bot will reply to your command with the provided text")
                                );
    }
    
    @Override
    public void onEvent(CommandInteraction event) {
        log.info("event: /help");
        event.reply("HELP!!!!!!!!!!!!!!!!!!!!!!!!!!!").queue();
    }
}
