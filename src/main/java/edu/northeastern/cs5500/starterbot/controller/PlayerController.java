/**
 * This class is a controller that is used to store and retrieve Player objects from the database
 */
package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.Player;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class PlayerController {

    // This is a generic repository. It is a repository that can store any type of object. In
    // this case,
    // storing Player objects.
    @Inject GenericRepository<Player> playerRepository;

    // This is a constructor injection. The `@Inject` annotation tells Dagger to inject the
    // `PlayerController`
    // object into the `PlayerController` class. The `PlayerController` class is a Dagger
    // controller, which
    // means that
    // Dagger will automatically call the `PlayerController` constructor when the Dagger application
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
     * Add a friend for a player
     *
     * @param discordMemberId The Discord ID of the player who is adding a friend.
     * @param friend The friend's ObjectId
     */
    public void addFriendForPlayer(String discordMemberId, ObjectId friend) {
        Player player = getPlayerFromMemberId(discordMemberId);
        player.getFriends().add(friend);
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
}
