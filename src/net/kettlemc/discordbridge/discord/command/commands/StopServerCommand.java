package net.kettlemc.discordbridge.discord.command.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.kettlemc.discordbridge.DiscordBridge;
import net.kettlemc.discordbridge.config.DiscordConfig;
import net.kettlemc.discordbridge.discord.command.SlashCommand;
import org.bukkit.Bukkit;

public class StopServerCommand extends SlashCommand {

    public StopServerCommand() {
        super("stop");
    }

    @Override
    public void run(SlashCommandInteractionEvent event, Member member, MessageChannel channel) {
        if (!member.hasPermission(Permission.ADMINISTRATOR)) {
            event.reply(DiscordConfig.DISCORD_MESSAGE_NO_PERMISSION.getValue()).queue();
            return;
        }

        OptionMapping secondsOptions = event.getOption("seconds");
        long seconds = 0;
        if (secondsOptions != null) {
            seconds = secondsOptions.getAsLong();
        }

        if (seconds == 0) {
            event.reply(DiscordConfig.DISCORD_MESSAGE_INSTANT_RESTART.getValue().replace("%seconds%", String.valueOf(seconds))).queue();
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "stop");
            return;
        }

        event.reply(DiscordConfig.DISCORD_MESSAGE_RESTART.getValue()).queue();
        Bukkit.getServer().broadcastMessage(DiscordConfig.MINECRAFT_MESSAGE_RESTART.getValue().replace("%seconds%", String.valueOf(seconds)));
        Bukkit.getScheduler().runTaskLater(DiscordBridge.getInstance(), () -> Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "stop"), seconds * 20L);

    }

    @Override
    public void register(CommandListUpdateAction commands) {
        commands.addCommands(
                Commands.slash("stop", "Stops the server after a certain amount of seconds.")
                .addOptions(new OptionData(OptionType.INTEGER, "seconds", "How many seconds should pass before the server stops").setRequired(false))
        );
    }

}
