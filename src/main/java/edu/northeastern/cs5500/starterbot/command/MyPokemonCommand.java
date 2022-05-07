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

@Slf4j
@Singleton
public class MyPokemonCommand implements Command {

    private PlayerController playerController;
    private PokemonController pokemonController;

    @Inject
    public MyPokemonCommand(
            PlayerController playerController, PokemonController pokemonController) {
        this.playerController = playerController;
        this.pokemonController = pokemonController;
    }

    @Override
    public String getName() {
        return "mypokemon";
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "See owned pokemons and check details.");
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public void onEvent(CommandInteraction event) {
        log.info("event: /mypokemon");

        List<ObjectId> pokemonList =
                playerController.getPokemonListForPlayer(event.getUser().getId());

        Builder menu = createMenuBuilder(pokemonList);
        event.deferReply(true).addActionRow(menu.build()).queue();
    }

    /**
     * Create menu builder of pokemonlist
     *
     * @param pokemonList
     * @return Builder
     */
    public Builder createMenuBuilder(List<ObjectId> pokemonList) {
        Builder menu =
                SelectionMenu.create("menu:mypoke").setPlaceholder("Select a pokemon for details");
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
