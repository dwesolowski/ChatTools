package com.github.dwesolowski.chattools.listeners;

import com.github.dwesolowski.chattools.ChatTools;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    private final ChatTools plugin;

    public PlayerChat(ChatTools plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();
        String msg = event.getMessage();

        if (this.plugin.mutedPlayers.contains(player.getUniqueId())) {
            event.setCancelled(true);

            event.getRecipients().remove(player);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.ChatMuted")));
        }
        for (Player pl : event.getRecipients()) {
            if (this.plugin.mutedPlayers.contains(pl.getUniqueId())) {
                event.getRecipients().remove(pl);
            }
        }
    }
}
