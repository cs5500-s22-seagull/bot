package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.PokemonInfo;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.Data;
import org.bson.types.ObjectId;

@Singleton
public class PokemonInfoController {

    @Inject
    PokemonInfoController() {}

    @Inject GenericRepository<PokemonInfo> pokemonInfoRepository;

    public int getMaxCpbyName(String pokeName) {
        Collection<PokemonInfo> pokemonInfos = pokemonInfoRepository.getAll();
        for (PokemonInfo pokemonInfo : pokemonInfos) {
            if (pokemonInfo.getPokemonName().equals(pokeName)) {
                return pokemonInfo.getMaxCP();
            }
        }
        return -1;
    }

    public String getNameByObjectId(ObjectId id) {
        return pokemonInfoRepository.get(id).getPokemonName();
    }

    public String getPictureAddress(ObjectId id) {
        return pokemonInfoRepository.get(id).getPictureAddress();
    }
}
