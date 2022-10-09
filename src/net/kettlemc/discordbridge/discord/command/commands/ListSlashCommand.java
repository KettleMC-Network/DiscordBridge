package net.kettlemc.discordbridge.discord.command.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.kettlemc.discordbridge.discord.command.SlashCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ListSlashCommand extends SlashCommand {

    public ListSlashCommand() {
        super("list");
    }

    @Override
    public void run(SlashCommandEvent event, Member member, TextChannel channel) {
        StringBuilder sb = new StringBuilder("**Online:** ");
        for (Player player : Bukkit.getOnlinePlayers()) {
            sb.append(player.getName());
            sb.append(" ");
        }
        event.reply(sb.toString());
    }

    @Override
    public void register(CommandListUpdateAction commands) {
        commands.addCommands(
                new CommandData("list", "Lists all players on the server"));
    }

}
