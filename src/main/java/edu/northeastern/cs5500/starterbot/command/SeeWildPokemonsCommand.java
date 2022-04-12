package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.CatchController;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

@Singleton
@Slf4j
public class SeeWildPokemonsCommand implements Command {

    @Inject CatchController catchController;

    @Inject
    public SeeWildPokemonsCommand() {}

    @Override
    public String getName() {
        return "seewild";
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Ask the bot to reply with the provided text");
    }

    @Override
    public void onEvent(CommandInteraction event) {
        log.info("event: /seewild");
        catchController.seeWildPokemons();

        // event.reply("").queue();
    }
}
