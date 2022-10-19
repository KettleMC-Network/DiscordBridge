package net.kettlemc.discordbridge.discord.command;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kettlemc.discordbridge.config.DiscordConfig;

public class SlashCommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        String commandName = event.getName();

        SlashCommand command;

        if ((command = SlashCommand.commandMap.get(commandName)) != null) {
            if (DiscordConfig.DISCORD_ALLOW_EVERY_CHANNEL_FOR_COMMANDS.getValue() || event.getChannel().getIdLong() == DiscordConfig.DISCORD_CHANNEL_ID.getValue())
            command.run(event, event.getMember(), event.getTextChannel());
        }
    }
}
