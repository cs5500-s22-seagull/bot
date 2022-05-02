package edu.northeastern.cs5500.starterbot.repository;

import dagger.Module;
import dagger.Provides;
import edu.northeastern.cs5500.starterbot.model.Combat;
import edu.northeastern.cs5500.starterbot.model.Player;
import edu.northeastern.cs5500.starterbot.model.PokeMap;
import edu.northeastern.cs5500.starterbot.model.Pokedex;
import edu.northeastern.cs5500.starterbot.model.Pokemon;
import edu.northeastern.cs5500.starterbot.model.PokemonInfo;

@Module
public class RepositoryModule {

    @Provides
    public GenericRepository<Player> providePlayerRepository(MongoDBRepository<Player> repository) {
        return repository;
    }

    @Provides
    public Class<Player> providePlayer() {
        return Player.class;
    }

    @Provides
    public GenericRepository<Pokedex> providePokedexRepository(
            MongoDBRepository<Pokedex> repository) {
        return repository;
    }

    @Provides
    public Class<Pokedex> providePokedex() {
        return Pokedex.class;
    }

    @Provides
    public GenericRepository<Pokemon> providePokemonRepository(
            MongoDBRepository<Pokemon> repository) {
        return repository;
    }

    @Provides
    public Class<Pokemon> providePokemon() {
        return Pokemon.class;
    }

    @Provides
    public GenericRepository<PokemonInfo> providePokemonInfoRepository(
            MongoDBRepository<PokemonInfo> repository) {
        return repository;
    }

    @Provides
    public Class<PokemonInfo> providePokemonInfo() {
        return PokemonInfo.class;
    }

    @Provides
    public GenericRepository<Combat> provideCombatRepository(MongoDBRepository<Combat> repository) {
        return repository;
    }

    @Provides
    public Class<Combat> provideCombat() {
        return Combat.class;
    }
    /*
    @Provides
    public GenericRepository<PokeMap> providePokeMapRepository(MongoDBRepository<PokeMap> repository) {
        return repository;
    }

    */

    @Provides
    public Class<PokeMap> providePokeMap() {
        return PokeMap.class;
    }
}
