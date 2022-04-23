package edu.northeastern.cs5500.starterbot.command;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module
public class CommandModule {

    @Provides
    @IntoSet
    public Command provideSayCommand(RegisterCommand registerCommand) {
        return registerCommand;
    }

    @Provides
    @IntoSet
    public Command provideSetNameCommand(SetNameCommand setNameCommand) {
        return setNameCommand;
    }

    @Provides
    @IntoSet
    public Command provideAddFriendCommand(AddFriendCommand addFriendCommand) {
        return addFriendCommand;
    }

    @Provides
    @IntoSet
    public Command provideClearFriendCommand(ClearFriendCommand clearFriendCommand) {
        return clearFriendCommand;
    }

    @Provides
    @IntoSet
    public Command provideLookUpPokemonCommand(LookUpPokemonCommand lookUpPokemonCommand) {
        return lookUpPokemonCommand;
    }

    @Provides
    @IntoSet
    public Command provideSeeWildPokemonsCommand(SeeWildPokemonsCommand seeWildPokemonsCommand) {
        return seeWildPokemonsCommand;
    }

    @Provides
    @IntoSet
    public Command provideChallengeCommand(ChallengeCommand challengeCommand) {
        return challengeCommand;
    }

    @Provides
    @IntoSet
    public Command provideFightCommand(FightCommand fightCommand) {
        return fightCommand;
    }

    @Provides
    @IntoSet
    public Command provideSelectPokemonCommand(SelectPokemonCommand selectPokemonCommand) {
        return selectPokemonCommand;
    }

    @Provides
    @IntoSet
    public Command provideMyPokemonCommand(MyPokemonCommand myPokemonCommand) {
        return myPokemonCommand;
    }
}
