package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.controller.CombatController;
import edu.northeastern.cs5500.starterbot.controller.PlayerController;
import edu.northeastern.cs5500.starterbot.controller.PokemonController;
import edu.northeastern.cs5500.starterbot.controller.PokemonInfoController;
import edu.northeastern.cs5500.starterbot.model.Player;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.model.PokemonInfo;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FightCommandTest {

    private PlayerController playerController;
    private CombatController combatController;
    private PokemonController pokemonController;
    private PokemonInfoController pokemonInfoController;
    private FightCommand command;

    @BeforeEach
    void setUp() {
        pokemonInfoController = new PokemonInfoController(new InMemoryRepository<>());
        playerController = new PlayerController(new InMemoryRepository<>());
        pokemonController =
                new PokemonController(pokemonInfoController, new InMemoryRepository<>());
        combatController = new CombatController(new InMemoryRepository<>());

        command = new FightCommand(playerController, combatController, pokemonController);
    }

    @Test
    void testGetCommandDataHasDescription() {
        assertThat(command.getCommandData().getDescription()).isNotNull();
    }

    @Test
    void testGetName() {
        assertThat(command.getName()).isEqualTo("fight");
    }

    @Test
    void testCreateMenuBuilder() {
        Pokemon pokemon = new Pokemon();

        HashMap<String, String> ownedMoves = new HashMap<String, String>();
        ownedMoves.put("Volt Switch", "70 100");
        ownedMoves.put("Thunderbolt", "90 100");

        pokemon.setOwnedMoves(ownedMoves);
        pokemonController.addPokemon(pokemon);

        Player player = playerController.getPlayerFromUserId("123");
        playerController.setSelectedPokemonForPlayer(pokemon.getId(), "123");
        assertThat(command.createMenuBuilder(player.getDiscordUserId()).getOptions().size())
                .isEqualTo(2);
    }

    @Test
    void testCreateEmbedBuilder() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setPokemonName("pokeName2");
        pokemonInfo.setMaxHP(33);
        pokemonInfoController.addToRepo(pokemonInfo);

        Pokemon pokemon = new Pokemon();
        pokemon.setCp(12);
        pokemon.setHp(12);
        pokemon.setPokemonInfo(pokemonInfo.getId());
        pokemonController.addPokemon(pokemon);

        Pokemon pokemon2 = new Pokemon();
        pokemon2.setCp(12);
        pokemon2.setHp(12);
        pokemon2.setPokemonInfo(pokemonInfo.getId());
        pokemonController.addPokemon(pokemon2);

        playerController.getPlayerFromUserId("123");
        playerController.setSelectedPokemonForPlayer(pokemon.getId(), "123");

        Player player2 = playerController.getPlayerFromUserId("234");
        playerController.setSelectedPokemonForPlayer(pokemon2.getId(), "234");
        assertThat(command.createEmbedBuilder("123", player2.getId()).getFields().size())
                .isEqualTo(4);
    }
}
