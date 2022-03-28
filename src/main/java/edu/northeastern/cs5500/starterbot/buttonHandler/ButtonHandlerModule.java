package edu.northeastern.cs5500.starterbot.buttonHandler;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module
public class ButtonHandlerModule {

    @Provides
    @IntoSet
    public ButtonHandler provideExampleHandler(ExampleButtonHandler handler) {
        return handler;
    }
}
