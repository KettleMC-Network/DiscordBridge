package net.kettlemc.discordbridge.listener;

import net.dv8tion.jda.api.utils.MarkdownSanitizer;
import net.kettlemc.discordbridge.DiscordBridge;
import net.kettlemc.discordbridge.config.DiscordConfig;
import net.kettlemc.discordbridge.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.isCancelled())
            return;

        String prefix = Utils.getLuckPermsPrefix(event.getPlayer());
        if (prefix == null) {
            prefix = DiscordConfig.DEFAULT_RANK.getValue();
        }

        String name = event.getPlayer().getDisplayName();
        name = (DiscordConfig.DISCORD_DISABLE_FORMATTING.getValue() ? MarkdownSanitizer.escape(name, true) : name);

        String msg = Utils.stripColor(event.getMessage());
        msg = (DiscordConfig.DISCORD_DISABLE_FORMATTING.getValue() ? MarkdownSanitizer.escape(msg, true) : msg);


        String message = DiscordConfig.DISCORD_MESSAGE_CHAT.getValue().replace("%rank%", prefix).replace("%player%", name).replace("%msg%", msg);

        DiscordBridge.getInstance().getBot().sendMessage(message);
    }

}
