package edu.northeastern.cs5500.starterbot.selectionHandler;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module
public class SelectionHandlerModule {

    @Provides
    @IntoSet
    public SelectionHandler provideAddFriendHandler(AddFriendSelectionHandler handler) {
        return handler;
    }

    @Provides
    @IntoSet
    public SelectionHandler provideWildPokemonsHandler(WildPokemonsHandler handler) {
        return handler;
    }
}
