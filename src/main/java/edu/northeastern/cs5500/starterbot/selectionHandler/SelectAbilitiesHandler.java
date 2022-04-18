package edu.northeastern.cs5500.starterbot.selectionHandler;

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

    @Override
    public String getName() {
        return "menu:abilities";
    }

    @Override
    public void onEvent(SelectionMenuEvent event) {
        String ability = event.getSelectedOptions().get(0).getValue();
        String[] split = ability.split(" ");
        double dmg = Integer.parseInt(split[0]);
        double hitChance = Double.parseDouble(split[1]);
        Combat combat = combatController.getCombatByUserIds(event.getUser().getId());
        String opponent =
                event.getUser().getId().equals(combat.getDiscordUserA())
                        ? combat.getDiscordUserB()
                        : combat.getDiscordUserA();
        combat.setTurn(opponent);
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
                                event.getUser().getAsMention()
                                        + " has defeated <@"
                                        + opponent
                                        + ">")
                        .queue();
                return;
            } else {
                pokemonController.setCurrentHp(pokemonId, currentHp);
                event.getChannel()
                        .sendMessage(
                                event.getUser().getAsMention()
                                        + " dealt "
                                        + dmg
                                        + " damage to <@"
                                        + opponent
                                        + ">"
                                        + " using ability "
                                        + event.getSelectedOptions().get(0).getLabel())
                        .queue();
            }
        } else {
            event.getChannel()
                    .sendMessage(
                            event.getUser().getAsMention()
                                    + " missed with ability "
                                    + event.getSelectedOptions().get(0).getLabel())
                    .queue();
        }
        combatController.setTurn(opponent);
        event.getChannel().sendMessage("It is " + "<@" + opponent + ">" + " 's turn").queue();
        event.reply("waiting opponent's turn").setEphemeral(true).queue();
    }
}
