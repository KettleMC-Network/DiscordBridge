package net.kettlemc.discordbridge.listener;

import net.kettlemc.discordbridge.DiscordBridge;
import net.kettlemc.discordbridge.utils.LuckPermsUtils;
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

        String prefix = LuckPermsUtils.getLuckPermsPrefix(event.getPlayer());
        String name = event.getPlayer().getDisplayName();
        String message = ChatColor.stripColor(event.getMessage());

        plugin.getBot().sendMessage(plugin.getConfiguration().channel, prefix + " **|** " + name + " **»** " + message);
    }

}
