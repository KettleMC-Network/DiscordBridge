package net.kettlemc.discordbridge.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.kettlemc.discordbridge.DiscordBridge;
import net.kettlemc.discordbridge.utils.LuckPermsUtils;
import org.bukkit.ChatColor;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import javax.security.auth.login.LoginException;

public class DiscordBot {


    private JDA jda;
    private DiscordBridge plugin;

    public DiscordBot(DiscordBridge plugin) {
        try {
            JDABuilder builder = JDABuilder.createDefault(plugin.getConfiguration().token);
            builder.setActivity(Activity.playing(plugin.getConfiguration().status));
            builder.setStatus(OnlineStatus.ONLINE);
            this.jda = builder.build();
            this.jda.getSelfUser().getManager().setName(plugin.getConfiguration().botName).queue();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public TextChannel getTextChannelByID(String id) {
        return jda.getTextChannelById(id);
    }

    public void sendMessage(TextChannel channel, String message) {
        channel.sendMessage(message).queue();
    }

    public void sendMessage(String channelId, String message) {
        getTextChannelByID(channelId).sendMessage(message).queue();
    }

    public void sendEmbed() {
        // TODO
    }


}
