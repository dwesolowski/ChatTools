package com.github.dwesolowski.chattools.listeners;

import com.github.dwesolowski.chattools.ChatTools;
import com.github.dwesolowski.chattools.commands.MuteGlobalChat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class GlobalChat implements Listener {

    private final ChatTools plugin;
    public GlobalChat(ChatTools plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();

        if (MuteGlobalChat.toggle) {
            if (!(player.hasPermission("chattools.bypass"))) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.ChatMuted")));
            }
        }
    }
}