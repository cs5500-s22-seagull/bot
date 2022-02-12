package edu.northeastern.cs5500.starterbot.command;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public interface Command {
    @Nonnull
    public String getName();

    @Nonnull
    public CommandData getCommandData();

    public void onEvent(@Nonnull CommandInteraction event);
}
