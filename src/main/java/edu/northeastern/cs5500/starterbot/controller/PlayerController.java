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
import javax.annotation.Nonnull;
import javax.inject.Inject;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class PlayerController {

    // This is a generic repository. It is a repository that can store any type of
    // object. In
    // this case,
    // storing Player objects.
    @Inject GenericRepository<Player> playerRepository;
    @Inject PokemonController pokemonController;

    // This is a constructor injection. The `@Inject` annotation tells Dagger to
    // inject the
    // `PlayerController`
    // object into the `PlayerController` class. The `PlayerController` class is a
    // Dagger
    // controller, which
    // means that
    // Dagger will automatically call the `PlayerController` constructor when the
    // Dagger application
    // starts.
    @Inject
    PlayerController() {}

    /**
     * It sets the name of a player.
     *
     * @param discordMemberId The id of the discord member.
     * @param name The name of the player
     */
    public void setNameForPlayer(String discordMemberId, String name) {
        Player player = getPlayerFromMemberId(discordMemberId);
        player.setName(name);
        playerRepository.update(player);
    }

    /**
     * Get all the player names from the database
     *
     * @return A collection of strings.
     */
    public Collection<String> getAllPlayerNames() {
        Collection<String> playerNames = new ArrayList<String>();
        for (Player player : playerRepository.getAll()) {
            playerNames.add(player.getName());
        }
        return playerNames;
    }

    /**
     * This function returns a collection of all the player's discord member id
     *
     * @return A collection of all the player's discord member id.
     */
    public Collection<String> getAllPlayerMemberId() {
        Collection<String> playerId = new ArrayList<String>();
        for (Player player : playerRepository.getAll()) {
            playerId.add(player.getDiscordMemberId());
        }
        return playerId;
    }

    /**
     * Given a Discord member ID, return the name of the player that Discord member is playing as
     *
     * @param discordMemberId The id of the discord member.
     * @return The name of the player.
     */
    @Nonnull
    public String getNameForPlayer(String discordMemberId) {
        return getPlayerFromMemberId(discordMemberId).getName();
    }

    /**
     * Given a Discord member ID, return the player's level
     *
     * @param discordMemberId The id of the discord member.
     * @return The level of the player.
     */
    @Nonnull
    public Integer getLevelForPlayer(String discordMemberId) {
        return getPlayerFromMemberId(discordMemberId).getLevel();
    }

    /**
     * This function sets the level of a player
     *
     * @param discordMemberId The Discord ID of the player.
     * @param level The level you want to set the player to.
     */
    public void setLevelForPlayer(String discordMemberId, Integer level) {
        Player player = getPlayerFromMemberId(discordMemberId);
        player.setLevel(level);
        playerRepository.update(player);
    }

    /**
     * This function returns the total XP of a player
     *
     * @param discordMemberId The id of the discord member you want to get the total xp for.
     * @return The total XP for the player.
     */
    @Nonnull
    public Integer getTotalXpForPlayer(String discordMemberId) {
        return getPlayerFromMemberId(discordMemberId).getTotalXP();
    }

    /**
     * This function sets the total XP for a player
     *
     * @param discordMemberId The discord id of the player
     * @param Xp The amount of XP to add to the player.
     */
    public void setTotalXpForPlayer(String discordMemberId, Integer Xp) {
        Player player = getPlayerFromMemberId(discordMemberId);
        player.setTotalXP(Xp);
        playerRepository.update(player);
    }

    /**
     * This function returns a list of all the pokemon that the player has
     *
     * @param discordMemberId The id of the discord member.
     * @return A list of pokemon objects.
     */
    @Nonnull
    public List<ObjectId> getPokemonListForPlayer(String discordMemberId) {
        return getPlayerFromMemberId(discordMemberId).getPokemonList();
    }

    /**
     * This function takes in a discordMemberId and a list of pokemonIds and sets the player's
     * pokemonList to the list of pokemonIds
     *
     * @param discordMemberId The Discord ID of the player.
     * @param pokemonList The list of Pokemon that the player has.
     */
    public void setPokemonListPlayer(String discordMemberId, List<ObjectId> pokemonList) {
        Player player = getPlayerFromMemberId(discordMemberId);
        player.setPokemonList(pokemonList);
        playerRepository.update(player);
    }

    /**
     * add a wew pokemon to the pokemon list
     *
     * @param discordMemberId The Discord ID of the player.
     * @param pokemon The pokemon need to add
     */
    public void addNewPokemonInList(String discordMemberId, Pokemon pokemon) {
        Player player = getPlayerFromMemberId(discordMemberId);
        player.getPokemonList().add(pokemon.getId());
        playerRepository.update(player);
    }

    /**
     * Returns the date that the player started playing the game
     *
     * @param discordMemberId The id of the discord member.
     * @return The date that the player started playing.
     */
    @Nonnull
    public Date getStartDateForPlayer(String discordMemberId) {
        return getPlayerFromMemberId(discordMemberId).getDate();
    }

    /**
     * This function returns a list of all the friends of a player
     *
     * @param discordMemberId The id of the discord member you want to get the friends of.
     * @return A list of ObjectIds.
     */
    @Nonnull
    public List<ObjectId> getFriendsForPlayer(String discordMemberId) {
        return getPlayerFromMemberId(discordMemberId).getFriends();
    }

    /**
     * This function returns a list of all the items of a player
     *
     * @param discordMemberId The id of the discord member you want to get the friends of.
     * @return a HashMap of items
     */
    public HashMap<String, Integer> getItemsForPlayer(String discordMemberId) {
        return getPlayerFromMemberId(discordMemberId).getItems();
    }

    /**
     * It sets the updated items for a player.
     *
     * @param discordMemberId The Discord ID of the player.
     * @param updatedItems a HashMap of items
     */
    public void setItemsForPlayer(String discordMemberId, HashMap<String, Integer> updatedItems) {
        Player player = getPlayerFromMemberId(discordMemberId);
        player.setItems(updatedItems);
        playerRepository.update(player);
    }

    /**
     * It sets the friends for a player.
     *
     * @param discordMemberId The Discord ID of the player.
     * @param friends A list of ObjectIds of players that are friends with the player.
     */
    public void setFriendsForPlayer(String discordMemberId, List<ObjectId> friends) {
        Player player = getPlayerFromMemberId(discordMemberId);
        player.setFriends(friends);
        playerRepository.update(player);
    }

    /**
     * Given a player's discord id and a friend's name, add the friend to the player's friend list
     *
     * @param discordMemberId The id of the player who is adding a friend.
     * @param friendName The name of the player you want to add as a friend.
     */
    public void addFriendForPlayerByName(String discordMemberId, String friendName) {
        Player player = getPlayerFromMemberId(discordMemberId);
        for (Player findPlayer : playerRepository.getAll()) {
            if (findPlayer.getName().equals(friendName)) {
                player.getFriends().add(findPlayer.getId());
                playerRepository.update(player);
            }
        }
    }

    /**
     * Given a Discord member ID, return the player's Discord name
     *
     * @param discordMemberId The discord id of the player
     * @return The name of the player.
     */
    public String getDiscordName(String discordMemberId) {
        Player player = getPlayerFromMemberId(discordMemberId);
        return player.getDiscordName();
    }

    /**
     * This function sets the discord name of a player, and add poke balls if it is a new user
     *
     * @param discordMemberId The id of the discord member.
     * @param name The name of the player
     */
    public void setDiscordName(String discordMemberId, String name) {
        Player player = getPlayerFromMemberId(discordMemberId);
        player.setDiscordName(name);
        if (!player.getItems().containsKey("poke ball")) {
            player.getItems().put("poke ball", 50);
        }
        if (!player.getItems().containsKey("great ball")) {
            player.getItems().put("great ball", 10);
        }
        playerRepository.update(player);
    }

    /**
     * Given a Discord member ID, return the corresponding Player object
     *
     * @param discordMemberId The id of the discord member.
     * @return A Player object.
     */
    @Nonnull
    public Player getPlayerFromMemberId(String discordMemberId) {
        Collection<Player> players = playerRepository.getAll();
        for (Player player : players) {
            if (player.getDiscordMemberId().equals(discordMemberId)) {
                return player;
            }
        }

        Player player = new Player();
        player.setDiscordMemberId(discordMemberId);
        player.setDate(new Date(System.currentTimeMillis()));
        playerRepository.add(player);
        return player;
    }

    /**
     * Use one poke ball and returns a list of all the items of a player
     *
     * @param discordMemberId The id of the discord member.
     * @return a HashMap of items
     */
    public HashMap<String, Integer> usePokeBall(String discordMemberId) {
        HashMap<String, Integer> items = getItemsForPlayer(discordMemberId);
        items.put("poke ball", items.get("poke ball") - 1);
        setItemsForPlayer(discordMemberId, items);
        return items;
    }

    /**
     * Use one great ball and returns a list of all the items of a player
     *
     * @param discordMemberId The id of the discord member.
     * @return a HashMap of items
     */
    public HashMap<String, Integer> useGreatBall(String discordMemberId) {
        HashMap<String, Integer> items = getItemsForPlayer(discordMemberId);
        items.put("great ball", items.get("great ball") - 1);
        setItemsForPlayer(discordMemberId, items);
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
        return getPlayerFromMemberId(discordId).getSelectedPokemon();
    }

    /**
     * Set the selected pokemon for the player with the given id to the pokemon with the given id.
     *
     * @param pokemonId The id of the pokemon that the player wants to select
     * @param id The id of the player
     */
    public void setSelectedPokemonForPlayer(ObjectId pokemonId, String id) {
        Player player = getPlayerFromMemberId(id);
        player.setSelectedPokemon(pokemonId);
        playerRepository.update(player);
    }
}
