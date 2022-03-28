package edu.northeastern.cs5500.starterbot.buttonHandler;

import javax.inject.Inject;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;

public class ExampleButtonHandler implements ButtonHandler {

    @Inject
    ExampleButtonHandler() {}

    @Override
    public String getName() {
        return "example";
    }

    @Override
    public void onEvent(ButtonClickEvent event) {}
}
