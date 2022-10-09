package net.kettlemc.discordbridge.listener;

import me.minebuilders.clearlag.events.EntityRemoveEvent;
import net.kettlemc.discordbridge.DiscordBridge;
import net.kettlemc.discordbridge.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ClearLaggListener implements Listener {

    private DiscordBridge plugin;

    public ClearLaggListener(DiscordBridge plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClear(EntityRemoveEvent event) {

        String message = plugin.getConfiguration().clearLaggMessage.replace("%items%", String.valueOf(event.getEntityList().size()));

        plugin.getBot().sendMessage(message);
    }

}
