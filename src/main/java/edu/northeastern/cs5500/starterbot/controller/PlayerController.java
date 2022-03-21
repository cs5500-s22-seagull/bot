package edu.northeastern.cs5500.starterbot.controller;

import com.mongodb.lang.Nullable;
import edu.northeastern.cs5500.starterbot.model.Player;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class PlayerController {

    private static final Integer STARTING_LEVEL = 0;
    private static final Integer STARTING_XP = 0;

    @Inject GenericRepository<Player> playerRepository;

    @Inject
    PlayerController() {}

    public void setNameForPlayer(String discordMemberId, String name) {
        Player player = getPlayerFromMemberId(discordMemberId);
        player.setName(name);
        playerRepository.update(player);
    }

    @Nullable
    public String getNameForPlayer(String discordMemberId) {
        return getPlayerFromMemberId(discordMemberId).getName();
    }

    @Nonnull
    public Integer getLevelForPlayer(String discordMemberId) {
        return getPlayerFromMemberId(discordMemberId).getLevel();
    }

    public void setLevelForPlayer(String discordMemberId, Integer level) {
        Player player = getPlayerFromMemberId(discordMemberId);
        player.setLevel(level);
        playerRepository.update(player);
    }

    @Nonnull
    public Integer getTotalXpForPlayer(String discordMemberId) {
        return getPlayerFromMemberId(discordMemberId).getTotalXP();
    }

    public void setTotalXpForPlayer(String discordMemberId, Integer Xp) {
        Player player = getPlayerFromMemberId(discordMemberId);
        player.setTotalXP(Xp);
        playerRepository.update(player);
    }

    @Nonnull
    public List<ObjectId> getPokemonListForPlayer(String discordMemberId) {
        return getPlayerFromMemberId(discordMemberId).getPokemonList();
    }

    public void setPokemonListPlayer(String discordMemberId, List<ObjectId> pokemonList) {
        Player player = getPlayerFromMemberId(discordMemberId);
        player.setPokemonList(pokemonList);
        playerRepository.update(player);
    }

    @Nonnull
    public Date getStartDateForPlayer(String discordMemberId) {
        return getPlayerFromMemberId(discordMemberId).getDate();
    }

    @Nonnull
    public List<ObjectId> getFriendsForPlayer(String discordMemberId) {
        return getPlayerFromMemberId(discordMemberId).getFriends();
    }

    public void setFriendsForPlayer(String discordMemberId, List<ObjectId> friends) {
        Player player = getPlayerFromMemberId(discordMemberId);
        player.setFriends(friends);
        playerRepository.update(player);
    }

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
        player.setLevel(STARTING_LEVEL);
        player.setTotalXP(STARTING_XP);
        player.setPokemonList(new ArrayList<>());
        player.setFriends(new ArrayList<>());
        playerRepository.add(player);
        return player;
    }
}
