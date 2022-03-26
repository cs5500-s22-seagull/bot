package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.Pokedex;
import edu.northeastern.cs5500.starterbot.model.PokemonInfo;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import javax.inject.Inject;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class PokedexController {

    @Inject GenericRepository<Pokedex> pokedexRepository;
    @Inject GenericRepository<PokemonInfo> pokemonInfoRepository;

    public int getSeen(ObjectId id) {
        Pokedex pokedex = pokedexRepository.get(id);
        return pokedex.getSeen();
    }

    public int getCaught(ObjectId id) {
        Pokedex pokedex = pokedexRepository.get(id);
        return pokedex.getCaught();
    }

    public ObjectId getPokemonInfo(ObjectId id) {
        Pokedex pokedex = pokedexRepository.get(id);
        PokemonInfo pokemonInfo = pokemonInfoRepository.get(pokedex.getPokemonInfo());
        return pokemonInfo.getId();
    }
}
