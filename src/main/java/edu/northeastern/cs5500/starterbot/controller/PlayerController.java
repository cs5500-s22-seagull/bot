/**
 * This class is a controller that is used to store and retrieve Player objects from the database
 */
package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.Player;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

@Singleton
@Slf4j
public class PlayerController {
    private GenericRepository<Player> playerRepository;

    @Inject
    public PlayerController(GenericRepository<Player> playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * This function returns a collection of all the player's discord member id
     *
     * @return A collection of all the player's discord member id.
     */
    public Collection<String> getAllPlayerMemberId() {
        Collection<String> playerId = new ArrayList<String>();
        for (Player player : playerRepository.getAll()) {
            playerId.add(player.getDiscordUserId());
        }
        return playerId;
    }

    /**
     * Given a Discord member ID, return the player's level f
     *
     * @param discordUserId The id of the discord member.
     * @return The level of the player.
     */
    @Nonnull
    public Integer getLevelForPlayer(String discordUserId) {
        return getPlayerFromUserId(discordUserId).getLevel();
    }

    /**
     * This function sets the level of a player
     *
     * @param discordUserId The Discord ID of the player.
     * @param level The level you want to set the player to.
     */
    public void setLevelForPlayer(String discordUserId, Integer level) {
        Player player = getPlayerFromUserId(discordUserId);
        player.setLevel(level);
        playerRepository.update(player);
    }

    /**
     * This function returns the total XP of a player
     *
     * @param discordUserId The id of the discord member you want to get the total xp for.
     * @return The total XP for the player.
     */
    @Nonnull
    public Integer getTotalXpForPlayer(String discordUserId) {
        return getPlayerFromUserId(discordUserId).getTotalXP();
    }

    /**
     * This function sets the total XP for a player
     *
     * @param discordUserId The discord id of the player
     * @param Xp The amount of XP to add to the player.
     */
    public void setTotalXpForPlayer(String discordUserId, Integer Xp) {
        Player player = getPlayerFromUserId(discordUserId);
        player.setTotalXP(Xp);
        playerRepository.update(player);
    }

    /**
     * This function returns a list of all the pokemon that the player has
     *
     * @param discordUserId The id of the discord member.
     * @return A list of pokemon objects.
     */
    @Nonnull
    public List<ObjectId> getPokemonListForPlayer(String discordUserId) {
        return getPlayerFromUserId(discordUserId).getPokemonList();
    }

    /**
     * This function takes in a discordUserId and a list of pokemonIds and sets the player's
     * pokemonList to the list of pokemonIds
     *
     * @param discordUserId The Discord ID of the player.
     * @param pokemonList The list of Pokemon that the player has.
     */
    public void setPokemonListPlayer(String discordUserId, List<ObjectId> pokemonList) {
        Player player = getPlayerFromUserId(discordUserId);
        player.setPokemonList(pokemonList);
        playerRepository.update(player);
    }

    /**
     * add a wew pokemon to the pokemon list
     *
     * @param discordUserId The Discord ID of the player.
     * @param pokemon The pokemon need to add
     */
    public void addNewPokemonInList(String discordUserId, Pokemon pokemon) {
        Player player = getPlayerFromUserId(discordUserId);
        player.getPokemonList().add(pokemon.getId());
        playerRepository.update(player);
    }

    /**
     * Returns the date that the player started playing the game
     *
     * @param discordUserId The id of the discord member.
     * @return The date that the player started playing.
     */
    @Nonnull
    public Date getStartDateForPlayer(String discordUserId) {
        return getPlayerFromUserId(discordUserId).getDate();
    }

    /**
     * This function returns a list of all of the IDs of a player's friends
     *
     * @param discordUserId The id of the discord member you want to get the friends of.
     * @return A list of ObjectIds.
     */
    @Nonnull
    public List<String> getFriendIdsForPlayer(String discordUserId) {
        return getPlayerFromUserId(discordUserId).getFriends();
    }

    /**
     * Return all of the {@link Player}s a user is friends with.
     *
     * @param discordUserId
     * @return
     */
    @Nonnull
    public List<Player> getFriendsForPlayer(String discordUserId) {
        return getFriendIdsForPlayer(discordUserId).stream()
                .map(this::getPlayerFromUserId)
                .collect(Collectors.toList());
    }

    /**
     * This function returns a list of all the items of a player
     *
     * @param discordUserId The id of the discord member you want to get the friends of.
     * @return a HashMap of items
     */
    public HashMap<String, Integer> getItemsForPlayer(String discordUserId) {
        return getPlayerFromUserId(discordUserId).getItems();
    }

    /**
     * It sets the updated items for a player.
     *
     * @param discordUserId The Discord ID of the player.
     * @param updatedItems a HashMap of items
     */
    public void setItemsForPlayer(String discordUserId, HashMap<String, Integer> updatedItems) {
        Player player = getPlayerFromUserId(discordUserId);
        player.setItems(updatedItems);
        playerRepository.update(player);
    }

    /**
     * It sets the friends for a player.
     *
     * @param discordUserId The Discord ID of the player.
     * @param friends A list of ObjectIds of players that are friends with the player.
     */
    public void setFriendsForPlayer(String discordUserId, List<String> friends) {
        Player player = getPlayerFromUserId(discordUserId);
        player.setFriends(friends);
        playerRepository.update(player);
    }

    /**
     * Given a player's discord id and a friend's name, add the friend to the player's friend list
     *
     * @param discordUserId The id of the player who is adding a friend.
     * @param friendId The id of the player you want to add as a friend.
     * @returns false if friendId could not be found
     */
    public boolean addFriendForPlayerByName(String discordUserId, String friendId) {
        Player player = getPlayerFromUserId(discordUserId);
        for (Player findPlayer : playerRepository.getAll()) {
            if (findPlayer.getDiscordUserId().equals(friendId)) {
                player.getFriends().add(findPlayer.getDiscordUserId());
                playerRepository.update(player);
                return true;
            }
        }
        log.info("Could not find a friend matching id {}", friendId);
        return false;
    }

    /**
     * Given a Discord member ID, return the corresponding Player object
     *
     * @param discordUserId The id of the discord member.
     * @return A Player object.
     */
    @Nonnull
    public Player getPlayerFromUserId(String discordUserId) {
        Collection<Player> players = playerRepository.getAll();
        for (Player player : players) {
            if (player.getDiscordUserId().equals(discordUserId)) {
                return player;
            }
        }

        Player player = new Player();
        player.setDiscordUserId(discordUserId);
        player.setDate(new Date(System.currentTimeMillis()));
        player.getItems().put("poke ball", 50);
        player.getItems().put("great ball", 10);
        playerRepository.add(player);
        return player;
    }

    /**
     * Use one poke ball and returns a list of all the items of a player
     *
     * @param discordUserId The id of the discord member.
     * @return a HashMap of items
     */
    public HashMap<String, Integer> usePokeBall(String discordUserId) {
        HashMap<String, Integer> items = getItemsForPlayer(discordUserId);
        items.put("poke ball", items.get("poke ball") - 1);
        setItemsForPlayer(discordUserId, items);
        return items;
    }

    /**
     * Use one great ball and returns a list of all the items of a player
     *
     * @param discordUserId The id of the discord member.
     * @return a HashMap of items
     */
    public HashMap<String, Integer> useGreatBall(String discordUserId) {
        HashMap<String, Integer> items = getItemsForPlayer(discordUserId);
        items.put("great ball", items.get("great ball") - 1);
        setItemsForPlayer(discordUserId, items);
        return items;
    }

    /**
     * Get the player with the given object id.
     *
     * @param id The id of the player to get.
     * @return A Player object
     */
    public Player getPlayerByObjectId(ObjectId id) {
        return playerRepository.get(id);
    }

    /**
     * Get the selected pokemon of the player with the given discord id.
     *
     * @param discordId The discord id of the user
     * @return The selected pokemon's ObjectId
     */
    public ObjectId getSeletedPokemonByDiscordId(String discordId) {
        return getPlayerFromUserId(discordId).getSelectedPokemon();
    }

    /**
     * Set the selected pokemon for the player with the given id to the pokemon with the given id.
     *
     * @param pokemonId The id of the pokemon that the player wants to select
     * @param discordId The id of the player
     */
    public void setSelectedPokemonForPlayer(ObjectId pokemonId, String discordId) {
        Player player = getPlayerFromUserId(discordId);
        player.setSelectedPokemon(pokemonId);
        playerRepository.update(player);
    }

    /**
     * Move to new node
     *
     * @param discordId
     * @param newLocation
     */
    public void moveToNode(String discordId, Integer newLocation) {
        Player player = getPlayerFromUserId(discordId);
        player.setLocation(newLocation);
        playerRepository.update(player);
    }
}
