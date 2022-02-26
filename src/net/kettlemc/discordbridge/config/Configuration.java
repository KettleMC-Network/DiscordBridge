package net.kettlemc.discordbridge.config;

import net.kettlemc.cfw.configuration.BasicConfigurationHandler;

public class Configuration {

    private BasicConfigurationHandler config;

    public String token, status, defaultRank;
    public String mcChatFormat, chatFormat, joinFormat, quitFormat, startupMessage, shutdownMessage;
    public long channel;
    public boolean embed, showSkin, disableFormatting;

    public BasicConfigurationHandler getConfig() {
        return this.config;
    }

    public Configuration() {
        this.config = new BasicConfigurationHandler("plugins/DiscordBridge/config.yml");

        this.token = config.getString("bot.token", "ENTER TOKEN HERE");
        this.channel = config.getLong("bot.channel-id", -1);
        this.status = config.getString("bot.status", " auf KettleMC.net");

        this.embed = config.getBool("bot.messages.embeds.enabled", false);
        this.showSkin = config.getBool("bot.messages.embeds.show-skin", true);

        this.disableFormatting = config.getBool("bot.messages.disable-format", true);

        this.mcChatFormat = config.getString("bot.messages.mc-chat-format", "&9DC &8| &7%user% &8» &7%msg%");
        this.chatFormat = config.getString("bot.messages.chat-format", ":speech_left: **[%rank%] %player% »** %msg%");
        this.joinFormat = config.getString("bot.messages.join-format", ":arrow_right: %msg%");
        this.quitFormat = config.getString("bot.messages.quit-format", ":arrow_left: %msg%");
        this.startupMessage = config.getString("bot.messages.startup", ":white_check_mark: Server started!");
        this.shutdownMessage = config.getString("bot.messages.shutdown", ":negative_squared_cross_mark: Server stopped!");

        this.defaultRank = config.getString("bot.messages.default-rank", "Player");
    }
}
