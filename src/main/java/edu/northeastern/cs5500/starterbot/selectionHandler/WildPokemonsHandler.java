package edu.northeastern.cs5500.starterbot.selectionHandler;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.CatchController;
import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import edu.northeastern.cs5500.starterbot.controller.PokedexController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.PokemonInfoController;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import java.util.HashMap;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu.Builder;

@Slf4j
@Singleton
public class WildPokemonsHandler implements SelectionHandler {

    @Inject PlayerController playerController;
    @Inject PokedexController pokedexController;
    @Inject PokemonController pokemonController;
    @Inject CatchController catchController;
    @Inject PokemonInfoController pokemonInfoController;

    @Inject
    WildPokemonsHandler() {}

    @Override
    public String getName() {
        return "menu:wildpokemons";
    }

    public EmbedBuilder createEmbedBuilder(Pokemon caughtPoke, String wildPoke) {
        EmbedBuilder info = new EmbedBuilder();
        info.setTitle(
                String.format(
                        "Succesfully caught %s !",
                        wildPoke.substring(wildPoke.lastIndexOf(" ") + 1)));
        info.addField("Gender: ", caughtPoke.getGender(), false);
        info.addField("CP: ", "" + caughtPoke.getCp(), false);
        info.addField("HP: ", "" + caughtPoke.getHp(), false);
        info.setImage(pokemonInfoController.getPictureAddress(caughtPoke.getPokemonInfo()));
        info.setColor(0xf45642);
        return info;
    }

    public Builder createMenu(HashMap<String, Integer> items, String wildPoke) {
        Builder menu = SelectionMenu.create("menu:wildpokemons").setPlaceholder("Choose a ball");
        int pokeBallNum = 0;
        int greatBallNum = 0;
        if (items.containsKey("poke ball") && items.get("poke ball") > 0) {
            pokeBallNum = items.get("poke ball");
            menu.addOption("poke ball - remaining: " + pokeBallNum, "pokeball " + wildPoke);
        }
        if (items.containsKey("great ball") && items.get("great ball") > 0) {
            greatBallNum = items.get("great ball");
            menu.addOption("great ball - remaining: " + greatBallNum, "greatball " + wildPoke);
        }
        return menu;
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public void onEvent(SelectionMenuEvent event) {
        String wildPoke = event.getSelectedOptions().get(0).getValue();
        log.info(wildPoke);

        HashMap<String, Integer> items =
                playerController.getItemsForPlayer(event.getUser().getId());

        double randomNumber =
                catchController.getCatchChance(wildPoke); // chance to catch the wild pokemon
        String arr[] = wildPoke.split(" ", 2); // split the wildPoke for getting first words
        if (arr[0].equals("greatball") && items.get("great ball") > 0 && randomNumber >= 0.2) {
            items = playerController.useGreatBall(event.getUser().getId());
            Pokemon caughtPoke = catchController.catchPokemon(event.getUser().getId(), wildPoke);
            EmbedBuilder info = createEmbedBuilder(caughtPoke, wildPoke);
            event.replyEmbeds(info.build()).queue();
        } else if (arr[0].equals("pokeball") && items.get("poke ball") > 0 && randomNumber >= 0.3) {
            items = playerController.usePokeBall(event.getUser().getId());
            Pokemon caughtPoke = catchController.catchPokemon(event.getUser().getId(), wildPoke);
            EmbedBuilder info = createEmbedBuilder(caughtPoke, wildPoke);
            event.replyEmbeds(info.build()).queue();
        } else {
            if (arr[0].equals("greatball") && items.get("great ball") > 0 && randomNumber < 0.2) {
                items = playerController.useGreatBall(event.getUser().getId());
            }
            if (arr[0].equals("pokeball") && items.get("poke ball") > 0 && randomNumber < 0.3) {
                items = playerController.usePokeBall(event.getUser().getId());
            }
            Builder menu = createMenu(items, wildPoke);
            event.deferReply(true).addActionRow(menu.build()).queue();
        }
    }
}
