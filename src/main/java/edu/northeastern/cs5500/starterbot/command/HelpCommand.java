package edu.northeastern.cs5500.starterbot.command;

import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;


@Singleton
@Slf4j
public class HelpCommand implements Command {

    @Inject
    public HelpCommand() {}

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Show some help about the bot's commands")
                .addOptions();
    }
    
    @Override
    public void onEvent(CommandInteraction event) {
        log.info("event: /help");

        EmbedBuilder info = new EmbedBuilder();
        info.setTitle(":desktop: List of Command");
        info.setDescription("Display a description of each command.");
        info.addField("/myInfo", "Show my infomation such as id, level, totalXP", false);
        info.addField("/friendList", "Show my fiend list", false);
        info.addField("/fightStadium", ":fist: Start fighting the stadium if there is a stadium open for fight in the current location.", false);
        info.addField("/catchPokemon", "Start catching the pokemoon if you meet a wild pokemon in your area.", false);
        info.addField("/shop", ":shopping_bags: Open the shop to see the items for sell.", false);
        info.addField("/items", "Show the items in my bag.", false);
        info.addField("/pokemon", "Show the list of all the Pokemons I have.", false);
        info.addField("/moving <DIRECTION>", "Using w/a/s/d for DIRECTIONs of North/West/South/East. ", false);
        info.setImage("https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/International_Pok%C3%A9mon_logo.svg/2880px-International_Pok%C3%A9mon_logo.svg.png");
        info.setColor(0xf45642);   

        event.replyEmbeds(info.build()).queue();
        info.clear();
    }

}
