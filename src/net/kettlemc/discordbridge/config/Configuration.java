package net.kettlemc.discordbridge.config;

import de.cuuky.cfw.configuration.BasicConfigurationHandler;

public class Configuration {

    private BasicConfigurationHandler config;

    public String token;
    public String channel;
    public String status;
    public String botName;


    public Configuration() {
        this.config = new BasicConfigurationHandler("plugins/DiscordBridge/config.yml");
        this.token = config.getString("bot.token", "ENTER TOKEN HERE");
        this.channel = config.getString("bot.channel-id", "ENTER CHANNEL ID HERE");
        this.status = config.getString("bot.status", " auf KettleMC.net");
        this.botName = config.getString("bot.name", "KettleMC");

    }
}
