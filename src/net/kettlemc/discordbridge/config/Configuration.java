package net.kettlemc.discordbridge.config;

import de.cuuky.cfw.configuration.BasicConfigurationHandler;

public class Configuration {

    private BasicConfigurationHandler config;

    public String token, status, defaultRank;
    public String chatFormat, joinFormat, quitFormat;
    public long channel;
    public boolean embed;


    public Configuration() {
        this.config = new BasicConfigurationHandler("plugins/DiscordBridge/config.yml");

        this.token = config.getString("bot.token", "ENTER TOKEN HERE");
        this.channel = config.getLong("bot.channel-id", -1);
        this.status = config.getString("bot.status", " auf KettleMC.net");

        this.embed = config.getBool("bot.messages.embeds.enabled", false);
        this.embed = config.getBool("bot.messages.embeds.show-skin", true);

        this.chatFormat = config.getString("bot.messages.chat-format", ":speech_left: **[%rank%] %player% »** %msg%");
        this.joinFormat = config.getString("bot.messages.join-format", ":arrow_right: %msg%");
        this.quitFormat = config.getString("bot.messages.quit-format", ":arrow_left: %msg%");

        this.defaultRank = config.getString("bot.messages.default-rank", "Player");
    }
}
