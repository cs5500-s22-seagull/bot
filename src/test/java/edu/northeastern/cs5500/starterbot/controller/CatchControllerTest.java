package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.model.PokemonInfo;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CatchControllerTest {

    private PokemonInfoController pokemonInfoController;
    private PlayerController playerController;
    private PokemonController pokemonController;
    private CatchController catchController;

    @BeforeEach
    void setUp() {
        pokemonInfoController = new PokemonInfoController(new InMemoryRepository<>());
        playerController = new PlayerController(new InMemoryRepository<>());
        pokemonController =
                new PokemonController(pokemonInfoController, new InMemoryRepository<>());
        catchController =
                new CatchController(pokemonInfoController, playerController, pokemonController);
    }

    @Test
    void testSeeWildPokemons() {
        pokemonInfoController.addInitPokeInfo();
        ArrayList<String> res = catchController.seeWildPokemons("Bungo");
        assertThat(res.size()).isAtLeast(1);
    }

    @Test
    void testGetCatchChange() {
        pokemonInfoController.addInitPokeInfo();
        Double chance = catchController.getCatchChance("Pikachu");
        assertThat(chance).isAtLeast(0.1);
    }

    @Test
    void testGenerateMoves() {
        PokemonInfo pokemonInfo = new PokemonInfo();
        HashMap<String, String> moves = new HashMap<String, String>();
        moves.put("Volt Switch", "70 100");
        moves.put("Thunderbolt", "90 100");
        moves.put("Thunder", "110 70");
        pokemonInfo.setMoves(moves);
        pokemonInfo.setPokemonName("Pikachu");
        assertThat(catchController.generateMoves("Pikachu", pokemonInfo).size()).isEqualTo(2);
    }

    @Test
    void testCatchPokemon() {
        pokemonInfoController.addInitPokeInfo();
        playerController.getPlayerFromUserId("123");
        Pokemon pokemon = catchController.catchPokemon("123", "Pikachu");
        assertThat(pokemonInfoController.getName(pokemon.getPokemonInfo())).isEqualTo("Pikachu");
    }
}
