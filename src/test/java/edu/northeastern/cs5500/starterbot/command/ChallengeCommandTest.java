package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import edu.northeastern.cs5500.starterbot.model.Player;
import java.util.ArrayList;
import java.util.Collection;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;
import org.junit.jupiter.api.Test;

class ChallengeCommandTest {
    @Test
    void testNameMatchesData() {
        ChallengeCommand challengeCommand = new ChallengeCommand();
        String name = challengeCommand.getName();
        CommandData commandData = challengeCommand.getCommandData();

        assertThat(name).isEqualTo(commandData.getName());
    }

    @Test
    void testNoFriendsSelectionMenuCreation() {
        ChallengeCommand challengeCommand = new ChallengeCommand();

        Collection<Player> friends = new ArrayList<Player>();

        SelectionMenu menu = challengeCommand.createBattleMenu(friends);

        assertThat(menu.getOptions()).isEmpty();
        // TODO: does this make sense?
    }

    Player buildPlayer(String name, String discordName, String discordMemberId) {
        Player player = new Player();
        player.setName(name);
        player.setDiscordName(discordName);
        player.setDiscordMemberId(discordMemberId);

        return player;
    }

    @Test
    void testSomeFriendsSelectionMenuCreation() {
        ChallengeCommand challengeCommand = new ChallengeCommand();

        Collection<Player> friends = new ArrayList<Player>();

        friends.add(buildPlayer("a", "a1", "a2"));
        friends.add(buildPlayer("b", "b1", "b2"));
        friends.add(buildPlayer("c", "c1", "c2"));

        SelectionMenu menu = challengeCommand.createBattleMenu(friends);

        assertThat(menu.getOptions()).hasSize(3);
    }

    @Test
    void testTooManyFriendsSelectionMenuCreationFails() {
        ChallengeCommand challengeCommand = new ChallengeCommand();

        Collection<Player> friends = new ArrayList<Player>();

        for (int x = 0; x < 26; x++) {
            friends.add(
                    buildPlayer(
                            String.format("name-%d", x),
                            String.format("discordName-%d", x),
                            String.format("discordMemberId-%d", x)));
        }

        try {
            challengeCommand.createBattleMenu(friends);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail("You cannot have more than 25 options in a selection menu");
    }
}
