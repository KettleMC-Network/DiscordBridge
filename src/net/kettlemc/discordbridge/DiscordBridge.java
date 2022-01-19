package net.kettlemc.discordbridge;

import net.kettlemc.discordbridge.config.Configuration;
import net.kettlemc.discordbridge.discord.DiscordBot;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordBridge extends JavaPlugin {

    private Configuration configuration;
    private DiscordBot bot;

    public void onEnable() {
        this.configuration = new Configuration();
        this.bot = new DiscordBot(this);
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    public DiscordBot getBot() {
        return this.bot;
    }

}