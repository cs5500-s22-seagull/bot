package edu.northeastern.cs5500.starterbot.selectionHandler;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.CombatController;
import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.PokemonInfoController;
import edu.northeastern.cs5500.starterbot.model.Combat;
import java.util.Random;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import org.bson.types.ObjectId;

@Slf4j
@Singleton
public class SelectAbilitiesHandler implements SelectionHandler {

    @Inject PlayerController playerController;
    @Inject CombatController combatController;
    @Inject PokemonController pokemonController;
    @Inject PokemonInfoController pokemonInfoController;

    @Inject
    SelectAbilitiesHandler() {}

    /**
     * This function returns the name of the command.
     *
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "menu:abilities";
    }

    /**
     * It takes the ability selected by the user, calculates the damage, and then deals the damage
     * to the opponent
     *
     * @param event The event that triggered the listener
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public void onEvent(SelectionMenuEvent event) {
        String ability = event.getSelectedOptions().get(0).getValue();
        String[] split = ability.split(" ");

        double rate =
                pokemonController.getLevel(
                                playerController.getSeletedPokemonByDiscordId(
                                        event.getUser().getId()))
                        / 100.0;
        double dmg = (int) (Integer.parseInt(split[0]) * 0.2 * rate);
        double hitChance = Double.parseDouble(split[1]) / 100.0;
        Combat combat = combatController.getCombatByUserIds(event.getUser().getId());
        String opponent =
                event.getUser().getId().equals(combat.getDiscordUserA())
                        ? combat.getDiscordUserB()
                        : combat.getDiscordUserA();
        Random random = new Random();
        double roll = random.nextDouble();
        ObjectId pokemonId = playerController.getSeletedPokemonByDiscordId(opponent);
        int currentHp = pokemonController.getCurrentHp(pokemonId);
        ObjectId myPokemonId =
                playerController.getSeletedPokemonByDiscordId(event.getUser().getId());
        if (hitChance > roll) {
            currentHp -= dmg;
            if (currentHp <= 0) {
                pokemonController.setCurrentHp(pokemonId, pokemonController.getHp(pokemonId));
                pokemonController.setCurrentHp(myPokemonId, pokemonController.getHp(myPokemonId));
                combatController.resolveCombat(combat.getId());
                event.getChannel()
                        .sendMessage(
                                String.format(
                                        "%s has defeated <@%s>",
                                        event.getUser().getAsMention(), opponent))
                        .queue();
                event.reply("The fight has concluded");
                return;
            } else {
                pokemonController.setCurrentHp(pokemonId, currentHp);
                event.getChannel()
                        .sendMessage(
                                String.format(
                                        "%s dealt %s damage to <@%s> using ability %s",
                                        event.getUser().getAsMention(),
                                        dmg,
                                        opponent,
                                        event.getSelectedOptions().get(0).getLabel()))
                        .queue();
            }
        } else {
            event.getChannel()
                    .sendMessage(
                            String.format(
                                    "%s missed with ability %s",
                                    event.getUser().getAsMention(),
                                    event.getSelectedOptions().get(0).getLabel()))
                    .queue();
        }
        combatController.setTurn(opponent);
        event.getChannel().sendMessage(String.format("It is <@%s>'s turn", opponent)).queue();
        event.reply("waiting opponent's turn").setEphemeral(true).queue();
    }
}
