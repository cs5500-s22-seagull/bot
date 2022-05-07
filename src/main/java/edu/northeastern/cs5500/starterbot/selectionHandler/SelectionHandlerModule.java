package edu.northeastern.cs5500.starterbot.selectionHandler;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import edu.northeastern.cs5500.starterbot.annotation.ExcludeClassFromJacocoGeneratedReport;

@Module
@ExcludeClassFromJacocoGeneratedReport
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

    @Provides
    @IntoSet
    public SelectionHandler provideChallengeFriendHandler(ChallengeFriendSelectionHandler handler) {
        return handler;
    }

    @Provides
    @IntoSet
    public SelectionHandler provideSelectPokemonHandler(SelectPokemonSelectionHandler handler) {
        return handler;
    }

    @Provides
    @IntoSet
    public SelectionHandler provideSelectAbilitiesHandler(SelectAbilitiesHandler handler) {
        return handler;
    }

    @Provides
    @IntoSet
    public SelectionHandler provideMyPokemonHandler(MyPokemonHandler handler) {
        return handler;
    }

    @Provides
    @IntoSet
    public SelectionHandler provideNextNodeHandler(NextNodeHandler handler) {
        return handler;
    }
}
