package edu.northeastern.cs5500.starterbot.selectionHandler;

import edu.northeastern.cs5500.starterbot.controller.CombatController;
import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.PokemonInfoController;
import edu.northeastern.cs5500.starterbot.model.Combat;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;

@Slf4j
@Singleton
public class ChallengeFriendSelectionHandler implements SelectionHandler {

    @Inject PlayerController playerController;
    @Inject CombatController combatController;
    @Inject PokemonController pokemonController;
    @Inject PokemonInfoController pokemonInfoController;

    @Inject
    ChallengeFriendSelectionHandler() {}

    @Override
    public String getName() {
        return "menu:challenge";
    }

    @Override
    public void onEvent(SelectionMenuEvent event) {
        String friendDiscordId = event.getSelectedOptions().get(0).getValue();
        Combat combat =
                combatController.getCombatByUserIds(event.getUser().getId(), friendDiscordId);
        log.info("msg " + friendDiscordId);
        event.reply("You have challenged your friend!").setEphemeral(true).queue();
        event.getChannel()
                .sendMessage(
                        event.getUser().getName()
                                + " has challenged "
                                + "<@"
                                + friendDiscordId
                                + ">"
                                + " to a duel!")
                .queue();
    }
}
