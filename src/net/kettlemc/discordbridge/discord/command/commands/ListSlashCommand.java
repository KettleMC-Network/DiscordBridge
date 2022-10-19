package net.kettlemc.discordbridge.discord.command.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.kettlemc.discordbridge.config.DiscordConfig;
import net.kettlemc.discordbridge.discord.command.SlashCommand;
import net.kettlemc.discordbridge.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ListSlashCommand extends SlashCommand {

    public ListSlashCommand() {
        super("list");
    }

    @Override
    public void run(SlashCommandEvent event, Member member, TextChannel channel) {
        int count = 0;
        StringBuilder players = new StringBuilder();

        for (Player player : Bukkit.getOnlinePlayers()) {
            players.append(player.getName());
            players.append(" ");
            count++;
        }

        String playerList = DiscordConfig.DISCORD_DISABLE_FORMATTING.getValue() ? Utils.replaceFormats(players.toString()) : players.toString();

        if (count != 0) {
            event.reply(DiscordConfig.DISCORD_MESSAGE_ONLINE_LIST.getValue().replace("%players%", playerList).replace("%online%", String.valueOf(count))).queue();
        } else {
            event.reply(DiscordConfig.DISCORD_MESSAGE_NO_PLAYERS.getValue()).queue();
        }
    }

    @Override
    public void register(CommandListUpdateAction commands) {
        commands.addCommands(
                new CommandData("list", "Lists all players on the server"));
    }

}
