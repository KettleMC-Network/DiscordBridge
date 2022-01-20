package net.kettlemc.discordbridge.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.kettlemc.discordbridge.DiscordBridge;

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
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        jda.shutdownNow();
    }

    public TextChannel getTextChannelByID(long id) {
        return jda.getTextChannelById(id);
    }

    public void sendMessage(TextChannel channel, String message) {
        channel.sendMessage(message).queue();
    }

    public void sendMessage(long channelId, String message) {
        sendMessage(getTextChannelByID(channelId), message);
    }

    public void sendEmbed() {
        // TODO
    }


}
