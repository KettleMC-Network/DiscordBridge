package net.kettlemc.discordbridge.discord.listener;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kettlemc.discordbridge.config.DiscordConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.isFromType(ChannelType.PRIVATE) || event.getAuthor().isBot() || event.getChannel().getIdLong() != DiscordConfig.DISCORD_CHANNEL_ID.getValue())
            return;
        String message = DiscordConfig.MINECRAFT_MESSAGE_CHAT.getValue().replace("%name%", event.getAuthor().getName()).replace("%user%", event.getAuthor().getAsTag()).replace("%msg%", event.getMessage().getContentDisplay());
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        });
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));


    }
}
