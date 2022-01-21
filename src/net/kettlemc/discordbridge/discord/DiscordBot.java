package net.kettlemc.discordbridge.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.kettlemc.discordbridge.DiscordBridge;
import net.kettlemc.discordbridge.discord.listener.MessageListener;

import javax.security.auth.login.LoginException;

public class DiscordBot {


    private JDA jda;
    private DiscordBridge plugin;

    public DiscordBot(DiscordBridge plugin) {
        this.plugin = plugin;
        try {
            JDABuilder builder = JDABuilder.createDefault(plugin.getConfiguration().token);
            builder.setActivity(Activity.playing(plugin.getConfiguration().status));
            builder.setStatus(OnlineStatus.ONLINE);
            this.jda = builder.build();
            jda.addEventListener(new MessageListener(this));

        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public DiscordBridge getPlugin() {
        return this.plugin;
    }

    public void shutdown() {
        jda.shutdown();
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

    public void sendMessage(String message) {
        sendMessage(plugin.getConfiguration().channel, message);
    }

    public void sendEmbed() {
        // TODO
    }




}
