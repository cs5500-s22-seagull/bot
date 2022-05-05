package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.PokemonInfo;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.bson.types.ObjectId;

@Singleton
public class PokemonInfoController {

    @Inject
    PokemonInfoController() {}

    @Inject GenericRepository<PokemonInfo> pokemonInfoRepository;

    /**
     * Get max cp by pokemon name
     *
     * @param pokeName
     * @return int
     */
    public int getMaxCpbyName(String pokeName) {
        Collection<PokemonInfo> pokemonInfos = pokemonInfoRepository.getAll();
        for (PokemonInfo pokemonInfo : pokemonInfos) {
            if (pokemonInfo.getPokemonName().equals(pokeName)) {
                return pokemonInfo.getMaxCP();
            }
        }
        return -1;
    }

    /**
     * Get picture address by id
     *
     * @param id
     * @return String
     */
    public String getPictureAddress(ObjectId id) {
        return pokemonInfoRepository.get(id).getPictureAddress();
    }

    /**
     * Get name by id
     *
     * @param id
     * @return String
     */
    public String getName(ObjectId id) {
        if (id != null) {
            return pokemonInfoRepository.get(id).getPokemonName();
        }
        return null;
    }

    /**
     * Get max hp by id
     *
     * @param id
     * @return int
     */
    public int getMaxHp(ObjectId id) {
        return pokemonInfoRepository.get(id).getMaxHP();
    }

    /**
     * Get image hp by id
     *
     * @param id
     * @return String
     */
    public String getImage(ObjectId id) {
        return pokemonInfoRepository.get(id).getPictureAddress();
    }

    /**
     * Get all PokemonInfo in repository
     *
     * @return Collection<PokemonInfo>
     */
    public Collection<PokemonInfo> getAll() {
        return pokemonInfoRepository.getAll();
    }

    /**
     * Update PokemonInfo in repository
     *
     * @param pokemonInfo
     */
    public void updateRepo(PokemonInfo pokemonInfo) {
        pokemonInfoRepository.update(pokemonInfo);
    }

    /**
     * Add new PokemonInfo to repository
     *
     * @param pokemonInfoNew
     */
    public void addToRepo(PokemonInfo pokemonInfoNew) {
        pokemonInfoRepository.add(pokemonInfoNew);
    }
}
