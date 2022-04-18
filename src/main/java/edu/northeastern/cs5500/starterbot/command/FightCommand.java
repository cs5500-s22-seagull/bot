package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.controller.CombatController;
import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.model.Combat;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu.Builder;
import org.bson.types.ObjectId;

@Singleton
@Slf4j
public class FightCommand implements Command {

    @Inject PlayerController playerController;
    @Inject CombatController combatController;
    @Inject PokemonController pokemonController;

    @Inject
    public FightCommand() {}

    @Override
    public String getName() {
        return "fight";
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Accept pokemon battle challenge");
    }

    @Override
    public void onEvent(CommandInteraction event) {
        log.info("event: /fight");
        Combat combat = combatController.getCombatByUserIds(event.getUser().getId());
        ObjectId opponent = null;
        if (combat == null) {
            event.reply("There is no challenge directed at you").setEphemeral(true).queue();
            return;
        }
        log.info(combat.toString());
        if (!combat.getTurn().equals(event.getUser().getId())) {
            event.reply("It is not your turn").queue();
            return;
        }
        if (!combat.getDiscordUserA().equals(event.getUser().getId())) {
            opponent = playerController.getPlayerFromMemberId(combat.getDiscordUserA()).getId();
        } else {
            opponent = playerController.getPlayerFromMemberId(combat.getDiscordUserB()).getId();
        }

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder
                .setTitle("Pokemon battle")
                .addField(
                        "Your pokemon",
                        pokemonController.getName(
                                playerController.getSeletedPokemonByDiscordId(
                                        event.getUser().getId())),
                        false)
                .addField(
                        "HP",
                        pokemonController.getCurrentHp(
                                        playerController.getSeletedPokemonByDiscordId(
                                                event.getUser().getId()))
                                + "/"
                                + pokemonController.getHp(
                                        playerController.getSeletedPokemonByDiscordId(
                                                event.getUser().getId())),
                        false)
                .addField(
                        "Enemy Pokemon",
                        pokemonController.getName(
                                playerController
                                        .getPlayerByObjectId(opponent)
                                        .getSelectedPokemon()),
                        false)
                .addField(
                        "HP",
                        pokemonController.getCurrentHp(
                                        playerController
                                                .getPlayerByObjectId(opponent)
                                                .getSelectedPokemon())
                                + "/"
                                + pokemonController.getHp(
                                        playerController.getSeletedPokemonByDiscordId(
                                                playerController
                                                        .getPlayerByObjectId(opponent)
                                                        .getDiscordMemberId())),
                        false)
                .setImage(
                        pokemonController.getImage(
                                playerController.getSeletedPokemonByDiscordId(
                                        event.getUser().getId())))
                .setThumbnail(
                        pokemonController.getImage(
                                playerController.getSeletedPokemonByDiscordId(
                                        playerController
                                                .getPlayerByObjectId(opponent)
                                                .getDiscordMemberId())));

        Collection<String> abilities = new ArrayList<>();
        abilities.add("smash");
        abilities.add("hit");
        Builder menu = SelectionMenu.create("menu:abilities").setPlaceholder("Choose an ability");
        // for (String string : abilities) {
        //     menu.addOption(string, "10 0.8");
        // }
        menu.addOption("ability a 10 damage 80% hit chance", "10 0.8");
        menu.addOption("ability b 15 damage 60% hit chance", "15 0.6");

        event.replyEmbeds(embedBuilder.build())
                .addActionRow(menu.build())
                .setEphemeral(true)
                .queue();
    }
}
