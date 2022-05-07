package edu.northeastern.cs5500.starterbot.selectionHandler;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeFromJacocoGeneratedReport;
import edu.northeastern.cs5500.starterbot.controller.CombatController;
import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.PokemonInfoController;
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

    /**
     * This function returns the name of the command.
     *
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "menu:challenge";
    }

    /**
     * When a user selects a friend from the selection menu, the bot will send a message to the user
     * and the friend, and then start a combat between the two users
     *
     * @param event The event that triggered the listener.
     */
    @Override
    @ExcludeFromJacocoGeneratedReport
    public void onEvent(SelectionMenuEvent event) {
        String friendDiscordId = event.getSelectedOptions().get(0).getValue();
        combatController.getCombatByUserIds(event.getUser().getId(), friendDiscordId);
        log.info("msg " + friendDiscordId);
        event.reply("You have challenged your friend!").setEphemeral(true).queue();
        event.getChannel()
                .sendMessage(
                        String.format(
                                "%s has challenged <@%s> to a duel!",
                                event.getUser().getName(), friendDiscordId))
                .queue();
    }
}
