/** This class is used to provide command that sets the name of the user */
package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu.Builder;
import org.bson.types.ObjectId;

@Singleton
@Slf4j
public class SelectPokemonCommand implements Command {

    private PlayerController playerController;
    private PokemonController pokemonController;

    @Inject
    public SelectPokemonCommand(
            PlayerController playerController, PokemonController pokemonController) {
        this.playerController = playerController;
        this.pokemonController = pokemonController;
    }

    @Override
    public String getName() {
        return "selectpokemon";
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Select a pokemon for battle");
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public void onEvent(CommandInteraction event) {
        log.info("event: /selectpokemon");

        List<ObjectId> pokemonList =
                playerController.getPokemonListForPlayer(event.getUser().getId());

        Builder menu = createMenuBuilder(pokemonList);
        event.deferReply(true).addActionRow(menu.build()).queue();
    }

    public Builder createMenuBuilder(List<ObjectId> pokemonList) {
        Builder menu =
                SelectionMenu.create("menu:selectpokemon")
                        .setPlaceholder("Select a pokemon to use in battle");
        for (ObjectId id : pokemonList) {
            menu.addOption(
                    String.format(
                            "%s cp: %s",
                            pokemonController.getName(id), pokemonController.getCp(id)),
                    id.toHexString());
            if (menu.getOptions().size() >= 25) {
                break;
            }
        }
        return menu;
    }
}
