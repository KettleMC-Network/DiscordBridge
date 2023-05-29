package net.kettlemc.discordbridge.listener;

import me.minebuilders.clearlag.events.EntityRemoveEvent;
import net.kettlemc.discordbridge.DiscordBridge;
import net.kettlemc.discordbridge.config.DiscordConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class ClearLaggListener implements Listener {

    private final DiscordBridge plugin;

    private static int lastCleared = 0;

    public ClearLaggListener(DiscordBridge plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClear(EntityRemoveEvent event) {

        lastCleared += event.getEntityList().size();

        // Event gets fired for every world, only send it once
        if (event.getWorld() == Bukkit.getWorlds().get(0) && lastCleared >= DiscordConfig.MIN_CLEARLAGG_ITEMS.getValue()) {

            new BukkitRunnable() {

                @Override
                public void run() {
                    String message = DiscordConfig.DISCORD_MESSAGE_CLEARLAGG.getValue().replace("%items%", String.valueOf(lastCleared));
                    plugin.getBot().sendMessage(message);
                    lastCleared = 0;
                }

            }.runTaskLater(DiscordBridge.getInstance(), 40L);


        }

    }

}
