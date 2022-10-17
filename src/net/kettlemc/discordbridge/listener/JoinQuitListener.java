package net.kettlemc.discordbridge.listener;

import net.kettlemc.discordbridge.DiscordBridge;
import net.kettlemc.discordbridge.config.DiscordConfig;
import net.kettlemc.discordbridge.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String message = Utils.stripColor(event.getJoinMessage());
        message =  (DiscordConfig.DISCORD_DISABLE_FORMATTING.getValue() ? Utils.replaceFormats(message) : message);
        DiscordBridge.getInstance().getBot().sendMessage(DiscordConfig.DISCORD_MESSAGE_JOIN.getValue().replace("%msg%", message));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        String message = Utils.stripColor(event.getQuitMessage());
        message =  (DiscordConfig.DISCORD_DISABLE_FORMATTING.getValue() ? Utils.replaceFormats(message) : message);
        DiscordBridge.getInstance().getBot().sendMessage(DiscordConfig.DISCORD_MESSAGE_QUIT.getValue().replace("%msg%", message));
    }

}
