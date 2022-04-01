package edu.northeastern.cs5500.starterbot.buttonHandler;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;

public interface ButtonHandler {

    @Nonnull
    public String getName();

    public void onEvent(@Nonnull ButtonClickEvent event);
}
