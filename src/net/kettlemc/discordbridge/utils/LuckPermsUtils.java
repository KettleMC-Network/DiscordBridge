package net.kettlemc.discordbridge.utils;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class LuckPermsUtils {

    private static LuckPerms luckperms = LuckPermsProvider.get();

    public static String getLuckPermsPrefix(Player player) {
        User user = luckperms.getUserManager().getUser(player.getUniqueId());
        return ChatColor.stripColor(user.getCachedData().getMetaData().getPrefix());
    }
}
