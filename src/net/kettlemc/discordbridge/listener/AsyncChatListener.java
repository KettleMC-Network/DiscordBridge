package net.kettlemc.discordbridge.listener;

import net.kettlemc.discordbridge.DiscordBridge;
import net.kettlemc.discordbridge.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncChatListener implements Listener {

    private DiscordBridge plugin;

    public AsyncChatListener(DiscordBridge plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.isCancelled())
            return;

        String prefix = Utils.getLuckPermsPrefix(event.getPlayer());
        String name = event.getPlayer().getDisplayName();
        String msg = Utils.stripColor(event.getMessage());

        if (prefix == null)
            prefix = plugin.getConfiguration().defaultRank;

        String message = plugin.getConfiguration().chatFormat.replace("%rank%", prefix).replace("%player%", name).replace("%msg%", msg);

        plugin.getBot().sendMessage(message);
    }

}
