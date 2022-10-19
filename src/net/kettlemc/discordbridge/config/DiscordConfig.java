package net.kettlemc.discordbridge.config;

import net.kettlemc.discordbridge.DiscordBridge;
import net.kettlemc.konfiguration.Configuration;
import net.kettlemc.konfiguration.entry.BooleanConfigEntry;
import net.kettlemc.konfiguration.entry.IntegerConfigEntry;
import net.kettlemc.konfiguration.entry.LongConfigEntry;
import net.kettlemc.konfiguration.entry.StringConfigEntry;

public class DiscordConfig {

    private static Configuration CONFIGURATION = new Configuration(DiscordBridge.getInstance().getDataFolder().toPath().resolve("config.json"));

    public static StringConfigEntry DISCORD_TOKEN = new StringConfigEntry(CONFIGURATION, "YOUR TOKEN", "settings.discord.token");
    public static LongConfigEntry DISCORD_CHANNEL_ID = new LongConfigEntry(CONFIGURATION, -1l, "settings.discord.channel-id");
    public static StringConfigEntry DISCORD_BOT_STATUS = new StringConfigEntry(CONFIGURATION, " with %online% players", "settings.discord.status");
    public static BooleanConfigEntry DISCORD_DISABLE_FORMATTING = new BooleanConfigEntry(CONFIGURATION, true, "settings.discord.disable-formatting");

    public static BooleanConfigEntry DISCORD_EMBED_ENABLED = new BooleanConfigEntry(CONFIGURATION, false, "settings.discord.embed.enabled");
    public static BooleanConfigEntry DISCORD_EMBED_SHOW_SKIN = new BooleanConfigEntry(CONFIGURATION, true, "settings.discord.embed.show-skin");

    public static StringConfigEntry DISCORD_MESSAGE_CHAT = new StringConfigEntry(CONFIGURATION, ":speech_left: **[%rank%] %player% »** %msg%", "messages.discord.chat");
    public static StringConfigEntry DISCORD_MESSAGE_JOIN = new StringConfigEntry(CONFIGURATION, ":arrow_right: %msg%", "messages.discord.join");
    public static StringConfigEntry DISCORD_MESSAGE_QUIT = new StringConfigEntry(CONFIGURATION, ":arrow_left: %msg%", "messages.discord.quit");
    public static StringConfigEntry DISCORD_MESSAGE_STARTUP = new StringConfigEntry(CONFIGURATION, ":white_check_mark: Server started!", "messages.discord.startup");
    public static StringConfigEntry DISCORD_MESSAGE_SHUTDOWN = new StringConfigEntry(CONFIGURATION, ":negative_squared_cross_mark: Server stopped!", "messages.discord.shutdown");
    public static StringConfigEntry DISCORD_MESSAGE_CLEARLAGG = new StringConfigEntry(CONFIGURATION, ":broom: %items% have been cleared!", "messages.discord.clearlagg");
    public static StringConfigEntry DISCORD_MESSAGE_ONLINE_LIST = new StringConfigEntry(CONFIGURATION, ":family_mwgb: **Online (%online%):** %players%", "messages.discord.online");
    public static StringConfigEntry DISCORD_MESSAGE_NO_PLAYERS = new StringConfigEntry(CONFIGURATION, ":person_shrugging: No players online!", "messages.discord.no-players-online");
    public static StringConfigEntry DISCORD_MESSAGE_RESTART = new StringConfigEntry(CONFIGURATION, ":person_shrugging: No players online!", "messages.discord.no-players-online");

    public static StringConfigEntry MINECRAFT_MESSAGE_CHAT = new StringConfigEntry(CONFIGURATION, "&9DC &8| &7%user% &8» &7%msg%", "messages.minecraft.chat");
    public static StringConfigEntry MINECRAFT_MESSAGE_RESTART = new StringConfigEntry(CONFIGURATION, "&9DC &8| &7The server will restart in %seconds% seconds.", "messages.minecraft.restart");

    public static StringConfigEntry DEFAULT_RANK = new StringConfigEntry(CONFIGURATION, "Player", "settings.default-rank");
    public static BooleanConfigEntry ENABLE_CLEARLAGG_SUPPORT = new BooleanConfigEntry(CONFIGURATION, true, "settings.clearlagg.enabled");
    public static IntegerConfigEntry MIN_CLEARLAGG_ITEMS = new IntegerConfigEntry(CONFIGURATION, 100, "settings.clearlagg.min-items");

}
