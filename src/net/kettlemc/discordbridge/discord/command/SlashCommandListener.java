package net.kettlemc.discordbridge.discord.command;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashCommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        String commandName = event.getName();

        SlashCommand command;

        if ((command = SlashCommand.commandList.get(commandName)) != null) {
            command.run(event, event.getMember(), event.getTextChannel());
        }
    }
}
