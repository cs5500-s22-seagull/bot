package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.Player;
import edu.northeastern.cs5500.starterbot.model.Pokedex;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
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
    @Inject GenericRepository<Pokemon> pokemonRepository;
    @Inject GenericRepository<Player> playerRepository;

    @Inject PokemonInfoController pokemonInfoController;
    @Inject PlayerController playerController;

    public ArrayList<String> seeWildPokemons() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentTime = dtf.format(now).toString();
        System.out.println(
                "currentTime------------------------------------------------- " + currentTime);
        System.out.println(
                "currentTime------------------------------------- "
                        + currentTime.substring(11, 13).strip());

        ArrayList<String> res = new ArrayList<String>();
        Collection<PokemonInfo> pokemonInfos = pokemonInfoRepository.getAll();
        for (PokemonInfo pokemonInfo : pokemonInfos) {
            if (Integer.valueOf(currentTime.substring(11, 13).strip()) >= 10
                    && Integer.valueOf(currentTime.substring(11, 13).strip()) <= 15) {
                if (pokemonInfo.getMaxCP() < 1000) {
                    res.add(pokemonInfo.getPokemonName());
                }
            } else if (Integer.valueOf(currentTime.substring(11, 13).strip()) >= 16
                    && Integer.valueOf(currentTime.substring(11, 13).strip()) <= 20) {
                if (pokemonInfo.getPokemonNumber() < 100) {
                    res.add(pokemonInfo.getPokemonName());
                }
            } else {
                if (pokemonInfo.getMaxCP() < 1000 && pokemonInfo.getPokemonNumber() < 100) {
                    res.add(pokemonInfo.getPokemonName());
                }
            }
        }
        return res;
    }

    public double getCatchChange(String arr) {
        String pokeName = arr.substring(arr.lastIndexOf(" ") + 1).strip();
        int maxCp = pokemonInfoController.getMaxCpbyName(pokeName);
        double randomNum = Math.random();
        if (maxCp > 2000) {
            return randomNum * 0.45;
        } else if (maxCp > 1500) {
            return randomNum * 0.6;
        } else if (maxCp > 1000) {
            return randomNum * 0.8;
        } else if (maxCp > 600) {
            return randomNum * 0.9;
        }
        return randomNum;
    }

    public void catchPokemon(String discordMemberId, String arr) {
        String pokeName = arr.substring(arr.lastIndexOf(" ") + 1).strip();
        int cp =
                (int)
                        (pokemonInfoController.getMaxCpbyName(pokeName)
                                * (Math.random() * 0.45 + 0.45));
        int hp = (int) ((cp / 19) * (1 + Math.random() * 0.1));
        int level = (int) (cp / pokemonInfoController.getMaxCpbyName(pokeName));
        String gender = Math.random() > 0.5 ? "male" : "female";
        Collection<PokemonInfo> pokemonInfos = pokemonInfoRepository.getAll();
        for (PokemonInfo pokemonInfo : pokemonInfos) {
            if (pokemonInfo.getPokemonName().equals(pokeName)) {
                Pokemon pokemon = new Pokemon();
                pokemon.setPokemonInfo(pokemonInfo.getId());
                pokemon.setCp(cp);
                pokemon.setHp(hp);
                pokemon.setLevel(level);
                pokemon.setGender(gender);
                pokemonRepository.add(pokemon);
                // Player player = playerController.getPlayerFromMemberId(discordMemberId);
                // player.getPokemonList().add(pokemon.getId());
                // playerRepository.update(player);
                playerController.addNewPokemonInList(discordMemberId, pokemon);
                return;
            }
        }
    }
}
