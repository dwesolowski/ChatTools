package com.github.dwesolowski.chattools.listeners;

import com.github.dwesolowski.chattools.ChatTools;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Iterator;

public class FilterChat implements Listener {

    private final ChatTools plugin;

    public FilterChat(ChatTools plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        Iterator localIterator = this.plugin.getConfig().getStringList("Blacklist").iterator();
        if (localIterator.hasNext()) {
            do {
                String s = (String) localIterator.next();
                if (event.getMessage().contains(s)) {
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.BlacklistMessage")));
                    return;
                }
            } while (localIterator.hasNext());
        }
    }
}
