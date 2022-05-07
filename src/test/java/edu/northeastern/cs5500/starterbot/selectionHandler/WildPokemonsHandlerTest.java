package edu.northeastern.cs5500.starterbot.selectionHandler;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.collect.Iterables;
import edu.northeastern.cs5500.starterbot.controller.*;
import edu.northeastern.cs5500.starterbot.model.*;
import edu.northeastern.cs5500.starterbot.repository.*;
import java.util.Collection;
import java.util.HashMap;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.components.selections.*;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WildPokemonsHandlerTest {
    private PlayerController playerController;
    private WildPokemonsHandler wildPokemonsHandler;
    private Player player1;
    private Pokemon pokemon;
    private PokemonController pokemonController;
    private PokemonInfoController pokemonInfoController;
    private PokemonInfo pokemonInfo;

    @BeforeEach
    void setUp() {
        pokemonInfoController = new PokemonInfoController(new InMemoryRepository<>());
        pokemonController =
                new PokemonController(pokemonInfoController, new InMemoryRepository<>());
        pokemonInfoController.addInitPokeInfo();
        pokemonInfoController.addInitPokeInfo();
        pokemonInfoController.addInitPokeInfo();
        Collection<PokemonInfo> tmp = pokemonInfoController.getAll();
        pokemonInfo = Iterables.get(tmp, 0);
        pokemon = new Pokemon();
        pokemon.setPokemonInfo(pokemonInfo.getId());
        pokemonController.addPokemon(pokemon);
        pokemon.setGender("Male");
        pokemon.setHp(200);
        pokemon.setCp(59);
        pokemon.setPokemonInfo(pokemonInfo.getId());
        wildPokemonsHandler = new WildPokemonsHandler();
    }

    @Test
    void testCreateEmbedBuilder() {
        EmbedBuilder testInfo =
                wildPokemonsHandler.createEmbedBuilder(
                        pokemon, "test Pokemon", pokemonInfoController);
        assertThat(testInfo.getFields().size()).isEqualTo(3);
    }

    @Test
    void testCreateMenu() {
        String wildPoke = "wilePoke";

        HashMap<String, Integer> items = new HashMap<>(10);
        items.put("poke ball", 10);
        items.put("great ball", 20);
        Builder menu = wildPokemonsHandler.createMenu(items, wildPoke);
        assertThat(menu.getOptions().size()).isEqualTo(2);

        HashMap<String, Integer> items1 = new HashMap<>(10);
        items1.put("poke ball", 10);
        Builder menu1 = wildPokemonsHandler.createMenu(items1, wildPoke);
        assertThat(menu1.getOptions().size()).isEqualTo(1);

        HashMap<String, Integer> items2 = new HashMap<>(10);
        items2.put("great ball", 20);
        Builder menu2 = wildPokemonsHandler.createMenu(items2, wildPoke);
        SelectionMenu.create("menu:wildpokemons").setPlaceholder("Choose a ball");
        assertThat(menu2.getOptions().size()).isEqualTo(1);

        HashMap<String, Integer> items3 = new HashMap<>(10);
        items3.put("no ball", 0);
        Builder menu3 = wildPokemonsHandler.createMenu(items3, wildPoke);
        SelectionMenu.create("menu:wildpokemons").setPlaceholder("Choose a ball");
        assertThat(menu3.getOptions().size()).isEqualTo(0);
    }

    @Test
    void testGetName() {
        assertThat(wildPokemonsHandler.getName()).isEqualTo("menu:wildpokemons");
    }
}
