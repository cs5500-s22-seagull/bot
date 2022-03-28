package edu.northeastern.cs5500.starterbot.repository;

import dagger.Module;
import dagger.Provides;
import edu.northeastern.cs5500.starterbot.model.Player;

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
}
