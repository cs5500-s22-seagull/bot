package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.Pokedex;
import edu.northeastern.cs5500.starterbot.model.PokemonInfo;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import lombok.Data;

@Data
public class CatchController {

    @Inject
    CatchController() {}

    @Inject GenericRepository<Pokedex> pokedexRepository;
    @Inject GenericRepository<PokemonInfo> pokemonInfoRepository;

    public ArrayList<String> seeWildPokemons() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentTime = dtf.format(now).toString();
        System.out.println(
                "currentTime------------------------------------------------- " + currentTime);

        Collection<PokemonInfo> pokemonInfos = pokemonInfoRepository.getAll();
        for (PokemonInfo pokemonInfo : pokemonInfos) {
            // ArrayList<String> res = new ArrayList<String>();
            // res.add(pokemonInfo.getPokemonName());
            // res.add(String.valueOf(pokemonInfo.getPokemonNumber()));
            // res.add(pokemonInfo.getIntroduction());
            // res.add(pokemonInfo.getPictureAddress());
        }

        return null;
    }

    public void catchPokemon(String pokeName) {}
}
