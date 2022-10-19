# 🌉 Discord Bridge

A simple plugin providing a bridge between your discord and minecraft chats.

# Dependencies
- JDK >= 17
- Spigot o.ä. Server
- Minecraft Account

# Important Files
- Main files:
    - src/net/kettlemc/discordbridge/*DiscordBridge.java*
        - *Here is the Discord Bridge*
        - Function:
          - handle incoming Minecraft and Discord commands
          - Setup config by methods from Configuration.java
    - src/net/kettlemc/discordbridge/config/*DiscordConfig.java*
        - *Here are the important information loaded from the "config.json" in a java file.*
        - it's based on the configuration manager *Konfiguration*
        - Function:
          - defining methods to get the config values
    - src/net/kettlemc/discordbridge/discord/*DiscordBot.java*
        - *Here is the Bot for Discord.*
        - Function:
          - simplifying the Discord API package functions (JDA)
          - defining methods for the DiscordBridge.java
    - src/net/kettlemc/discordbridge/discord/*command/\**
        - *Here are the discord commands the bot can handle*