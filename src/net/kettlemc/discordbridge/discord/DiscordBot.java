package net.kettlemc.discordbridge.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.kettlemc.discordbridge.DiscordBridge;
import net.kettlemc.discordbridge.discord.command.SlashCommand;
import net.kettlemc.discordbridge.discord.command.commands.ListSlashCommand;
import net.kettlemc.discordbridge.discord.command.commands.StopServerCommand;
import net.kettlemc.discordbridge.discord.listener.MessageListener;

import javax.security.auth.login.LoginException;
import java.util.List;

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

    public void registerCommands() {

        new ListSlashCommand().addToList();
        new StopServerCommand().addToList();

        // Delete any existing commands (prevents duplicates)
        List<Command> cmds = this.jda.retrieveCommands().complete();
        cmds.forEach(cmd -> cmd.delete());

        // Add commands to guilds
        this.jda.getGuilds().forEach(guild -> {
            CommandListUpdateAction commands = guild.updateCommands();
            SlashCommand.commandList.keySet().forEach(key -> {
                SlashCommand.commandList.get(key).register(commands);
            });
            commands.queue();
        });
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
