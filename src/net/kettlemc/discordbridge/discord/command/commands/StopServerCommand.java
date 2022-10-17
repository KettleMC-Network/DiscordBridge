package net.kettlemc.discordbridge.discord.command.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.kettlemc.discordbridge.DiscordBridge;
import net.kettlemc.discordbridge.discord.command.SlashCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StopServerCommand extends SlashCommand {

    public StopServerCommand() {
        super("stop");
    }

    @Override
    public void run(SlashCommandEvent event, Member member, TextChannel channel) {
        if (!member.hasPermission(Permission.ADMINISTRATOR)) {
            event.reply("You're not allowed to use this command.");
            return;
        }

        OptionMapping secondsOptions = event.getOption("seconds");
        long seconds = 0;
        if (secondsOptions != null) {
            seconds = secondsOptions.getAsLong();
        }

        if (seconds == 0) {
            event.reply("The server has been stopped.");
            Bukkit.getServer().shutdown();
            return;
        }

        event.reply("The server will stop in " + seconds + "seconds.");
        Bukkit.getServer().broadcastMessage(DiscordBridge.getInstance().getConfiguration().plannedShutdownMessage.replace("%seconds%", String.valueOf(seconds)));
        Bukkit.getScheduler().runTaskLater(DiscordBridge.getInstance(), () -> Bukkit.getServer().shutdown(), seconds * 20L);

    }

    @Override
    public void register(CommandListUpdateAction commands) {
        commands.addCommands(
                new CommandData("stop", "Stops the server after a certain amount of seconds.")
                .addOptions(new OptionData(OptionType.INTEGER, "seconds", "How many seconds should pass before the server stops").setRequired(true))
        );
    }

}
