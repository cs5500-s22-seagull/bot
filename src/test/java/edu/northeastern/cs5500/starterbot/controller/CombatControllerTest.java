package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.model.Combat;
import edu.northeastern.cs5500.starterbot.model.Player;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CombatControllerTest {

    private CombatController combatController;
    private PlayerController playerController;

    @BeforeEach
    void setUp() {
        combatController = new CombatController(new InMemoryRepository<>());
        playerController = new PlayerController(new InMemoryRepository<>());
    }

    @Test
    void testGetCombatByUserIds() {
        Player player = playerController.getPlayerFromUserId("123");
        Player player2 = playerController.getPlayerFromUserId("234");
        Combat combat =
                combatController.getCombatByUserIds(
                        player.getDiscordUserId(), player2.getDiscordUserId());
        assertThat(combat.getDiscordUserA()).isEqualTo(player.getDiscordUserId());
    }

    @Test
    void testGetCombatByUserIds2() {
        Player player = playerController.getPlayerFromUserId("123");
        Player player2 = playerController.getPlayerFromUserId("234");
        combatController.getCombatByUserIds(player.getDiscordUserId(), player2.getDiscordUserId());
        Combat combat2 = combatController.getCombatByUserIds(player2.getDiscordUserId());
        assertThat(combat2.getDiscordUserA()).isEqualTo(player.getDiscordUserId());
    }

    @Test
    void testSetTurn() {
        Player player = playerController.getPlayerFromUserId("123");
        Player player2 = playerController.getPlayerFromUserId("234");
        Combat combat =
                combatController.getCombatByUserIds(
                        player.getDiscordUserId(), player2.getDiscordUserId());
        combatController.setTurn(player.getDiscordUserId());
        assertThat(combat.getTurn()).isEqualTo(player.getDiscordUserId());
    }
}
