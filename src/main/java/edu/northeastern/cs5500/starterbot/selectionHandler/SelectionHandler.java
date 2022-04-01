package edu.northeastern.cs5500.starterbot.selectionHandler;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;

public interface SelectionHandler {
    @Nonnull
    public String getName();

    public void onEvent(@Nonnull SelectionMenuEvent event);
}
