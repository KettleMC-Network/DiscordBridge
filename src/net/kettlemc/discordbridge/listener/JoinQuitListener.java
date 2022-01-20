package net.kettlemc.discordbridge.listener;

import net.kettlemc.discordbridge.DiscordBridge;
import net.kettlemc.discordbridge.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    private DiscordBridge plugin;

    public JoinQuitListener(DiscordBridge plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String message = Utils.stripColor(event.getJoinMessage());
        plugin.getBot().sendMessage(plugin.getConfiguration().channel, plugin.getConfiguration().joinFormat.replace("%msg%", message));
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        String message = Utils.stripColor(event.getQuitMessage());
        plugin.getBot().sendMessage(plugin.getConfiguration().channel, plugin.getConfiguration().quitFormat.replace("%msg%", message));
    }

}
