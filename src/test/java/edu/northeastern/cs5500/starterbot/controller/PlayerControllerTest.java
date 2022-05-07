package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.model.Player;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerControllerTest {

    private PlayerController playerController;

    @BeforeEach
    void setUp() {
        playerController = new PlayerController(new InMemoryRepository<>());
    }

    @Test
    void testGetAllPlayerMemberId() {
        playerController.getPlayerFromUserId("123");
        Collection<String> players = playerController.getAllPlayerMemberId();
        assertThat(players.size()).isEqualTo(1);
    }

    @Test
    void testGetLevelForPlayer() {
        Player player = playerController.getPlayerFromUserId("123");
        player.setLevel(56);
        assertThat(playerController.getLevelForPlayer("123")).isEqualTo(56);
    }

    @Test
    void testGetDateForPlayer() {
        Player player = playerController.getPlayerFromUserId("123");
        Date date = new Date();
        player.setDate(date);
        Date expected = playerController.getStartDateForPlayer("123");
        assertThat(date).isEqualTo(expected);
    }

    @Test
    void testSetLevelForPlayer() {
        playerController.setLevelForPlayer("123", 12);
        Player player = playerController.getPlayerFromUserId("123");
        assertThat(player.getLevel()).isEqualTo(12);
    }

    @Test
    void testSetTotalXPForPlayer() {
        playerController.getPlayerFromUserId("123");
        playerController.setTotalXpForPlayer("123", 12);
        assertThat(playerController.getTotalXpForPlayer("123")).isEqualTo(12);
    }

    @Test
    void testAddAndGetPokemonListForPlayer() {
        playerController.getPlayerFromUserId("123");
        Pokemon pokemon = new Pokemon();
        playerController.addNewPokemonInList("123", pokemon);
        assertThat(playerController.getPokemonListForPlayer("123").size()).isEqualTo(1);
    }

    @Test
    void testSetPokemonListPlayer() {
        playerController.getPlayerFromUserId("123");
        List<ObjectId> pokemonList = new ArrayList<>();
        playerController.setPokemonListPlayer("123", pokemonList);
        assertThat(playerController.getPokemonListForPlayer("123").size()).isEqualTo(0);
    }

    @Test
    void testGetPlayerFromMemberId() {
        Player player = playerController.getPlayerFromUserId("123");
        assertThat(playerController.getPlayerFromUserId("123")).isEqualTo(player);
    }

    @Test
    void testGetFriendsForPlayer() {
        Player player = playerController.getPlayerFromUserId("123");
        List<String> friends = new ArrayList<>();
        friends.add("friend1");
        player.setFriends(friends);
        assertThat(playerController.getFriendIdsForPlayer("123").get(0)).isEqualTo("friend1");
    }

    @Test
    void testSetAndGetItemsForPlayer() {
        playerController.getPlayerFromUserId("123");
        HashMap<String, Integer> items = new HashMap<>();
        playerController.setItemsForPlayer("123", items);
        assertThat(playerController.getItemsForPlayer("123").size()).isEqualTo(0);
    }

    @Test
    void testUsePokeBall() {
        playerController.getPlayerFromUserId("123");
        HashMap<String, Integer> items = new HashMap<>();
        items.put("poke ball", 5);
        playerController.setItemsForPlayer("123", items);
        assertThat(playerController.usePokeBall("123").get("poke ball")).isEqualTo(4);
    }

    @Test
    void testGreatBall() {
        playerController.getPlayerFromUserId("123");
        HashMap<String, Integer> items = new HashMap<>();
        items.put("great ball", 5);
        playerController.setItemsForPlayer("123", items);
        assertThat(playerController.useGreatBall("123").get("great ball")).isEqualTo(4);
    }

    @Test
    void testGetPlayerByObjectId() {
        Player player = playerController.getPlayerFromUserId("123");
        assertThat(playerController.getPlayerByObjectId(player.getId())).isEqualTo(player);
    }

    @Test
    void testSetAndGetSeletedPokemon() {
        playerController.getPlayerFromUserId("123");
        Pokemon pokemon = new Pokemon();
        playerController.addNewPokemonInList("123", pokemon);
        playerController.setSelectedPokemonForPlayer(pokemon.getId(), "123");
        assertThat(playerController.getSeletedPokemonByDiscordId("123")).isEqualTo(pokemon.getId());
    }

    @Test
    void testAddandGetFriendsForPlayerByName() {
        playerController.getPlayerFromUserId("123");
        Player player2 = playerController.getPlayerFromUserId("234");
        playerController.addFriendForPlayerByName("123", player2.getDiscordUserId());
        assertThat(playerController.getFriendsForPlayer("123").get(0)).isEqualTo(player2);
    }
}
