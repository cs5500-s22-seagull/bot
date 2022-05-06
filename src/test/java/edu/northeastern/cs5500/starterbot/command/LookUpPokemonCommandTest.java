package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.controller.PokedexController;
import edu.northeastern.cs5500.starterbot.controller.PokemonInfoController;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LookUpPokemonCommandTest {

    private PokedexController pokedexController;
    private PokemonInfoController pokemonInfoController;
    private LookUpPokemonCommand command;

    @BeforeEach
    void setUp() {
        pokemonInfoController = new PokemonInfoController(new InMemoryRepository<>());
        pokedexController =
                new PokedexController(pokemonInfoController, new InMemoryRepository<>());
        command = new LookUpPokemonCommand(pokedexController);
    }

    @Test
    void testGetCommandDataHasDescription() {
        assertThat(command.getCommandData().getDescription()).isNotNull();
    }

    @Test
    void testGetName() {
        assertThat(command.getName()).isEqualTo("lookuppokemon");
    }

    @Test
    void testCreateEmbedBuilder() {
        ArrayList<String> res = new ArrayList<String>();
        res.add("name");
        res.add("number");
        res.add("intro");
        res.add(
                "https://cdn.discordapp.com/attachments/955539523620700170/971671222788165692/Female_Raichu.webp");
        assertThat(command.createEmbedBuilder(res).getFields().size()).isEqualTo(3);
    }
}
