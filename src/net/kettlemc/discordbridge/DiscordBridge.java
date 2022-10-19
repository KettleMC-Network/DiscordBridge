package net.kettlemc.discordbridge;

import net.kettlemc.discordbridge.config.DiscordConfig;
import net.kettlemc.discordbridge.discord.DiscordBot;
import net.kettlemc.discordbridge.listener.AsyncChatListener;
import net.kettlemc.discordbridge.listener.ClearLaggListener;
import net.kettlemc.discordbridge.listener.JoinQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class DiscordBridge extends JavaPlugin {

    private static final String CLEARLAGG_ID = "ClearLag";

    private boolean clearlaggEnabled = false;

    private static DiscordBridge instance;
    private DiscordBot bot;


    public void onEnable() {

        instance = this;
        this.getLogger().info("Loading plugin data...");

        if (DiscordConfig.DISCORD_TOKEN.getValue().equalsIgnoreCase(DiscordConfig.DISCORD_TOKEN.getDefaultValue())) {
            this.getLogger().warning("Please enter a token!");
            return;
        }

        this.bot = new DiscordBot();

        if (Bukkit.getPluginManager().getPlugin(CLEARLAGG_ID) == null) {
            this.getLogger().info("Couldn't find ClearLag, support disabled.");
        } else {
            this.clearlaggEnabled = true;
            this.getLogger().info("ClearLag was found, enabling support for it.");
        }

        this.registerBukkit();

        new BukkitRunnable() {

            @Override
            public void run() {
                bot.sendMessage(DiscordConfig.DISCORD_CHANNEL_ID.getValue(), DiscordConfig.DISCORD_MESSAGE_STARTUP.getValue());
            }

        }.runTaskLaterAsynchronously(this, 40L);
    }

    public void onDisable() {
        this.bot.sendMessage(DiscordConfig.DISCORD_CHANNEL_ID.getValue(), DiscordConfig.DISCORD_MESSAGE_SHUTDOWN.getValue());
        this.bot.shutdown();
    }

    private void registerBukkit() {
        Bukkit.getPluginManager().registerEvents(new AsyncChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);
        if (clearlaggEnabled && DiscordConfig.ENABLE_CLEARLAGG_SUPPORT.getValue())
            Bukkit.getPluginManager().registerEvents(new ClearLaggListener(this), this);
    }

    public static DiscordBridge getInstance() {
        return instance;
    }

    public DiscordBot getBot() {
        return this.bot;
    }

}