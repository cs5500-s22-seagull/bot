package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.model.Player;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.junit.jupiter.api.Test;

class ChallengeCommandTest {
    @Test
    void testNameMatchesData() {
        ChallengeCommand challengeCommand = new ChallengeCommand();
        String name = challengeCommand.getName();
        CommandData commandData = challengeCommand.getCommandData();

        assertThat(name).isEqualTo(commandData.getName());
    }

    // @Test
    // void testNoFriendsSelectionMenuCreation() {
    //     ChallengeCommand challengeCommand = new ChallengeCommand();

    //     Collection<Player> friends = new ArrayList<Player>();

    //     SelectionMenu menu = challengeCommand.createBattleMenu(friends);

    //     assertThat(menu.getOptions()).isEmpty();
    //     // TODO: does this make sense?
    // }

    Player buildPlayer(String discordUserId) {
        Player player = new Player();
        player.setDiscordUserId(discordUserId);

        return player;
    }

    // @Test
    // void testSomeFriendsSelectionMenuCreation() {
    //     ChallengeCommand challengeCommand = new ChallengeCommand();

    //     Collection<Player> friends = new ArrayList<Player>();

    //     friends.add(buildPlayer("a2"));
    //     friends.add(buildPlayer("b2"));
    //     friends.add(buildPlayer("c2"));

    //     SelectionMenu menu = challengeCommand.createBattleMenu(friends);

    //     assertThat(menu.getOptions()).hasSize(3);
    // }

    // @Test
    // void testTooManyFriendsSelectionMenuCreationFails() {
    //     ChallengeCommand challengeCommand = new ChallengeCommand();

    //     Collection<Player> friends = new ArrayList<Player>();

    //     for (int x = 0; x < 26; x++) {
    //         friends.add(
    //                 buildPlayer(
    //                        String.format("discordMemberId-%d", x)));
    //     }

    //     try {
    //         challengeCommand.createBattleMenu(friends);
    //     } catch (IllegalArgumentException e) {
    //         return;
    //     }
    //     fail("You cannot have more than 25 options in a selection menu");
    // }
}
