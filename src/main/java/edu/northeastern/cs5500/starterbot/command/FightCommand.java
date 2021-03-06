package edu.northeastern.cs5500.starterbot.command;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.CombatController;
import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.model.Combat;
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

    private PlayerController playerController;
    private CombatController combatController;
    private PokemonController pokemonController;

    @Inject
    public FightCommand(
            PlayerController playerController,
            CombatController combatController,
            PokemonController pokemonController) {
        this.playerController = playerController;
        this.combatController = combatController;
        this.pokemonController = pokemonController;
    }

    @Override
    public String getName() {
        return "fight";
    }

    @Override
    public CommandData getCommandData() {
        return new CommandData(getName(), "Select ability");
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public void onEvent(CommandInteraction event) {
        log.info("event: /fight");
        String userId = event.getUser().getId();
        Combat combat = combatController.getCombatByUserIds(userId);
        ObjectId opponent = null;
        if (combat == null) {
            event.reply("There is no challenge directed at you").setEphemeral(true).queue();
            return;
        }
        log.info(combat.toString());
        if (!combat.getTurn().equals(userId)) {
            event.reply("It is not your turn").queue();
            return;
        }
        if (!combat.getDiscordUserA().equals(userId)) {
            opponent = playerController.getPlayerFromUserId(combat.getDiscordUserA()).getId();
        } else {
            opponent = playerController.getPlayerFromUserId(combat.getDiscordUserB()).getId();
        }

        EmbedBuilder embedBuilder = createEmbedBuilder(userId, opponent);
        Builder menu = createMenuBuilder(userId);
        event.replyEmbeds(embedBuilder.build())
                .addActionRow(menu.build())
                .setEphemeral(true)
                .queue();
    }

    public Builder createMenuBuilder(String userId) {
        Builder menu = SelectionMenu.create("menu:abilities").setPlaceholder("Choose an ability");
        String[] res =
                pokemonController.getOwnedMoves(
                        playerController.getSeletedPokemonByDiscordId(userId));

        String[] splitM1 = res[1].split(" ");
        String[] splitM2 = res[3].split(" ");

        menu.addOption(
                String.format("%s - Power: %s /Accuracy: %s", res[0], splitM1[0], splitM1[1]),
                res[1]);
        menu.addOption(
                String.format("%s - Power: %s /Accuracy: %s", res[2], splitM2[0], splitM2[1]),
                res[3]);
        return menu;
    }

    public EmbedBuilder createEmbedBuilder(String userId, ObjectId opponent) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder
                .setTitle("Pokemon battle")
                .addField(
                        "Your pokemon",
                        pokemonController.getName(
                                playerController.getSeletedPokemonByDiscordId(userId)),
                        false)
                .addField(
                        "HP",
                        String.format(
                                "%s / %s",
                                pokemonController.getCurrentHp(
                                        playerController.getSeletedPokemonByDiscordId(userId)),
                                pokemonController.getHp(
                                        playerController.getSeletedPokemonByDiscordId(userId))),
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
                        String.format(
                                "%s / %s",
                                pokemonController.getCurrentHp(
                                        playerController
                                                .getPlayerByObjectId(opponent)
                                                .getSelectedPokemon()),
                                pokemonController.getHp(
                                        playerController.getSeletedPokemonByDiscordId(
                                                playerController
                                                        .getPlayerByObjectId(opponent)
                                                        .getDiscordUserId()))),
                        false)
                .setImage(
                        pokemonController.getImage(
                                playerController.getSeletedPokemonByDiscordId(userId)))
                .setThumbnail(
                        pokemonController.getImage(
                                playerController.getSeletedPokemonByDiscordId(
                                        playerController
                                                .getPlayerByObjectId(opponent)
                                                .getDiscordUserId())));
        return embedBuilder;
    }
}
