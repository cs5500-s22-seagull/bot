package edu.northeastern.cs5500.starterbot.selectionHandler;

import edu.northeastern.cs5500.starterbot.controller.CatchController;
import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import edu.northeastern.cs5500.starterbot.controller.PokedexController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.PokemonInfoController;
import javax.inject.Inject;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import org.bson.types.ObjectId;

public class MyPokemonHandler implements SelectionHandler {

    @Inject PlayerController playerController;
    @Inject PokedexController pokedexController;
    @Inject PokemonController pokemonController;
    @Inject CatchController catchController;
    @Inject PokemonInfoController pokemonInfoController;

    @Inject
    MyPokemonHandler() {}

    @Override
    public String getName() {
        return "menu:mypoke";
    }

    @Override
    public void onEvent(SelectionMenuEvent event) {
        String hexPokemon = event.getSelectedOptions().get(0).getValue();
        ObjectId id = new ObjectId(hexPokemon);

        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("" + pokemonController.getName(id));
        info.addField("Gender: ", pokemonController.getGender(id), false);
        info.addField("Level: ", String.valueOf(pokemonController.getLevel(id)), false);
        info.addField("CP: ", String.valueOf(pokemonController.getCp(id)), false);
        info.addField(
                "HP: ",
                String.format(
                        "%s / %s", pokemonController.getCurrentHp(id), pokemonController.getHp(id)),
                false);
        info.setImage(
                pokemonInfoController.getPictureAddress(pokemonController.getPokemonInfo(id)));
        info.setColor(0xf45642);
        event.replyEmbeds(info.build()).queue();
    }
}
