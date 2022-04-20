package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.PokedexController;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

@Singleton
@Slf4j
public class LookUpPokemonCommand implements Command {

    @Inject PokedexController pokedexController;

    @Inject
    public LookUpPokemonCommand() {}

    @Override
    public String getName() {
        return "lookuppokemon";
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(
                        getName(),
                        "Get general pokemon information by searching the name. (If you owns this pokemon.)")
                .addOptions(
                        new OptionData(
                                        OptionType.STRING,
                                        "pokemonname",
                                        "The bot will send back general pokemon information")
                                .setRequired(true));
    }

    @Override
    public void onEvent(CommandInteraction event) {
        log.info("event: /lookup");
        pokedexController.addInitPokeInfo();
        ArrayList<String> res =
                pokedexController.getGeneralInfo(
                        event.getOption("pokemonname").getAsString().strip());
        if (res != null) {
            String pokemonName = res.get(0);
            String pokemonNumber = res.get(1);
            String introduction = res.get(2);
            String pictureAddress = res.get(3);

            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("Pokemon information");
            info.addField("Pokemon name: ", pokemonName, false);
            info.addField("Pokemon number: ", "#" + pokemonNumber, false);
            info.addField("Introduction: ", introduction, false);
            info.setImage(pictureAddress);
            info.setColor(0xf45642);

            event.replyEmbeds(info.build()).queue();
            info.clear();
        } else {
            event.reply(
                            ":warning: "
                                    + event.getOption("pokemonname").getAsString().strip()
                                    + " not embodied! :warning: Try other Pokemon name!")
                    .queue();
        }
    }
}
