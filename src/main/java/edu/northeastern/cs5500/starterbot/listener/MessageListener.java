package edu.northeastern.cs5500.starterbot.listener;

import edu.northeastern.cs5500.starterbot.buttonHandler.ButtonHandler;
import edu.northeastern.cs5500.starterbot.command.Command;
import edu.northeastern.cs5500.starterbot.selectionHandler.SelectionHandler;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Inject;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class MessageListener extends ListenerAdapter {

    @Inject Set<Command> commands;

    @Inject Set<ButtonHandler> buttonHandlers;

    @Inject Set<SelectionHandler> selectionHandlers;

    @Inject
    public MessageListener() {
        super();
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        for (Command command : commands) {
            if (command.getName().equals(event.getName())) {
                command.onEvent(event);
                return;
            }
        }
    }

    public Collection<CommandData> allCommandData() {
        return commands.stream().map(Command::getCommandData).collect(Collectors.toList());
    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        for (ButtonHandler buttonHandler : buttonHandlers) {
            if (buttonHandler.getName().equals(event.getComponentId())) {
                buttonHandler.onEvent(event);
                return;
            }
        }
    }

    @Override
    public void onSelectionMenu(SelectionMenuEvent event) {
        for (SelectionHandler handler : selectionHandlers) {
            if (handler.getName().equals(event.getComponentId())) {
                handler.onEvent(event);
                return;
            }
        }
    }
}
