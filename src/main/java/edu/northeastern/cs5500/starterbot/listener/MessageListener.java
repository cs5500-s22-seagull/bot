package edu.northeastern.cs5500.starterbot.listener;

import edu.northeastern.cs5500.starterbot.command.Command;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Inject;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class MessageListener extends ListenerAdapter {

    @Inject Set<Command> commands;

    @Inject
    public MessageListener() {
        super();
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        for (Command command : commands) {
            if (event.getName().equals(event.getName())) {
                command.onEvent(event);
                return;
            }
        }
    }

    public Collection<CommandData> allCommandData() {
        return commands.stream().map(c -> c.getCommandData()).collect(Collectors.toList());
    }
}

// public class MessageListener extends ListenerAdapter {
//     @Override
//     public void onSlashCommand(SlashCommandEvent event) {
//         switch (event.getName()) {
//             case "say":
//                 event.reply(event.getOption("content").getAsString()).queue();
//         }
//     }
// }
