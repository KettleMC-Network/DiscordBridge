package net.kettlemc.discordbridge.discord.listener;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kettlemc.discordbridge.discord.DiscordBot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MessageListener extends ListenerAdapter {

    private DiscordBot bot;

    public MessageListener(DiscordBot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.isFromType(ChannelType.PRIVATE) || event.getAuthor().isBot())
            return;
        String message = bot.getPlugin().getConfiguration().mcChatFormat.replace("%name%", event.getAuthor().getName()).replace("%user%", event.getAuthor().getAsTag()).replace("%msg%", event.getMessage().getContentDisplay());
        Bukkit.getOnlinePlayers() .forEach( player -> {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        });
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));


    }
}
