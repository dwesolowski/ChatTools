package com.github.dwesolowski.chattools.commands;

import com.github.dwesolowski.chattools.ChatTools;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadChatTools implements CommandExecutor {

    private final ChatTools plugin;

    public ReloadChatTools(ChatTools plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Command not allowed in console, must be used in-game only!");
            return true;
        }
        Player player = (Player) sender;
        if ((cmd.getName().equalsIgnoreCase("reloadchattools")) && (args.length == 0)) {
            if (player.hasPermission("chattools.reloadchattools")) {
                this.plugin.reloadConfig();
                sender.sendMessage(ChatColor.RED + "ChatTools configuration has been reloaded!");
                return true;
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.NoPermissions")));
                return true;
            }
        }
        return false;
    }
}