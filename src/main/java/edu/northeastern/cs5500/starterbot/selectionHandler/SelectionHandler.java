package edu.northeastern.cs5500.starterbot.selectionHandler;

import edu.northeastern.cs5500.starterbot.annotation.ExcludeClassFromJacocoGeneratedReport;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;

@ExcludeClassFromJacocoGeneratedReport
public interface SelectionHandler {
    /**
     * Returns the name of the object.
     *
     * @return A string
     */
    @Nonnull
    public String getName();

    /**
     * > This function is called when a selection menu is opened
     *
     * @param event The event that was fired.
     */
    public void onEvent(@Nonnull SelectionMenuEvent event);
}
