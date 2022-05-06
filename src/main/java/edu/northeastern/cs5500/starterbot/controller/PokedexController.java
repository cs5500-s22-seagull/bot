package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.Pokedex;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.bson.types.ObjectId;

@Singleton
public class PokedexController {
    private GenericRepository<Pokedex> pokedexRepository;

    @Inject
    public PokedexController(GenericRepository<Pokedex> pokedexRepository) {
        this.pokedexRepository = pokedexRepository;
    }

    public int getSeen(ObjectId id) {
        Pokedex pokedex = pokedexRepository.get(id);
        return pokedex.getSeen();
    }

    public int getCaught(ObjectId id) {
        Pokedex pokedex = pokedexRepository.get(id);
        return pokedex.getCaught();
    }
}
