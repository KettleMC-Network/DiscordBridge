package net.kettlemc.discordbridge.utils;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;

import java.util.regex.Pattern;

public class Utils {

    private static final LuckPerms luckperms = LuckPermsProvider.get();

    public static String getLuckPermsPrefix(Player player) {
        User user = luckperms.getUserManager().getUser(player.getUniqueId());
        return stripColor(user.getCachedData().getMetaData().getPrefix());
    }

    // Inspired by Spigot's Chatcolor
    private static final Pattern COLOR_PATTERN = Pattern.compile("(?i)" + "(&|§)" + "[0-9A-FK-OR]");

    public static String stripColor(final String input) {
        if (input == null) {
            return null;
        }

        return COLOR_PATTERN.matcher(input).replaceAll("");
    }
}
