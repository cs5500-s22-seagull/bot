package edu.northeastern.cs5500.starterbot.selectionHandler;

import edu.northeastern.cs5500.starterbot.controller.CatchController;
import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import edu.northeastern.cs5500.starterbot.controller.PokedexController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import org.bson.types.ObjectId;

@Slf4j
@Singleton
public class SelectPokemonSelectionHandler implements SelectionHandler {

    @Inject PlayerController playerController;
    @Inject PokedexController pokedexController;
    @Inject PokemonController pokemonController;
    @Inject CatchController catchController;

    @Inject
    SelectPokemonSelectionHandler() {}

    @Override
    public String getName() {
        return "menu:selectpokemon";
    }

    @Override
    public void onEvent(SelectionMenuEvent event) {
        String hexPokemon = event.getSelectedOptions().get(0).getValue();
        ObjectId id = new ObjectId(hexPokemon);
        playerController.setSelectedPokemonForPlayer(id, event.getUser().getId());
        event.reply(
                        String.format(
                                "Succesfully set %s as the active pokemon",
                                pokemonController.getName(id)))
                .queue();
    }
}
