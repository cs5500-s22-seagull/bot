package edu.northeastern.cs5500.starterbot.controller;

import com.mongodb.lang.Nullable;
import edu.northeastern.cs5500.starterbot.model.Player;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import java.util.Collection;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import lombok.Data;

@Data
public class PlayerController {

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
    public Player getPlayerFromMemberId(String discordMemberId) {
        Collection<Player> players = playerRepository.getAll();
        for (Player player : players) {
            if (player.getDiscordMemberId().equals(discordMemberId)) {
                return player;
            }
        }

        Player player = new Player();
        player.setDiscordMemberId(discordMemberId);
        playerRepository.add(player);
        return player;
    }
}
