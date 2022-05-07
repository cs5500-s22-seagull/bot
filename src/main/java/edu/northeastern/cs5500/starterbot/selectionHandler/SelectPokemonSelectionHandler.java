package edu.northeastern.cs5500.starterbot.selectionHandler;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
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

    /**
     * This function returns the name of the command.
     *
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "menu:selectpokemon";
    }

    /**
     * > When a user selects a pokemon from the selection menu, set that pokemon as the active
     * pokemon for that user
     *
     * @param event The event that triggered the command.
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public void onEvent(SelectionMenuEvent event) {
        String hexPokemon = event.getSelectedOptions().get(0).getValue();
        ObjectId id = new ObjectId(hexPokemon);

        setSelectedPokemon(id, event.getUser().getId(), playerController);

        event.reply(
                        String.format(
                                "Succesfully set %s as the active pokemon",
                                pokemonController.getName(id)))
                .queue();
    }

    /**
     * Set the selected Pokemon for the player with the given UserId to the Pokemon with the given
     * PokemonId.
     *
     * @param PokemonId The id of the pokemon you want to set as the selected pokemon.
     * @param UserId The id of the user that is currently logged in.
     */
    public void setSelectedPokemon(
            ObjectId PokemonId, String UserId, PlayerController playerController) {
        playerController.setSelectedPokemonForPlayer(PokemonId, UserId);
    }
}
