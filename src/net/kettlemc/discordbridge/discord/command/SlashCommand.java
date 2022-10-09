package net.kettlemc.discordbridge.discord.command;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.kettlemc.discordbridge.DiscordBridge;

import java.util.HashMap;

public abstract class SlashCommand {

    public static HashMap<String, SlashCommand> commandList = new HashMap();

    private String name;

    public SlashCommand(String name) {
        this.name = name;
    }

    public abstract void run(SlashCommandEvent event, Member member, TextChannel channel);
    public abstract void register(CommandListUpdateAction commands);

    public void addToList() {
        if (!SlashCommand.commandList.containsKey(this.getName()))
            DiscordBridge.getInstance().getLogger().info("Adding command " + this.getName() + " to the list.");
            SlashCommand.commandList.put(this.getName(), this);
    }

    public String getName() {
        return this.name;
    }

}
