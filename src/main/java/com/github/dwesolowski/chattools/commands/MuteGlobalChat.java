package com.github.dwesolowski.chattools.commands;

import com.github.dwesolowski.chattools.ChatTools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteGlobalChat implements CommandExecutor {

    private final ChatTools plugin;

    public MuteGlobalChat(ChatTools plugin) {
        this.plugin = plugin;
    }

    public static boolean toggle = false;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Command not allowed in console, must be used in-game only!");
            return true;
        }
        Player player = (Player) sender;
        if ((cmd.getName().equalsIgnoreCase("muteglobalchat")) && (args.length == 0)) {
            if (player.hasPermission("chattools.muteglobalchat")) {
                if (toggle) {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.MuteGlobalChatDisabled").replace("%player%", player.getName())));
                    toggle = false;
                    return true;
                } else {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.MuteGlobalChatEnabled").replace("%player%", player.getName())));
                    toggle = true;
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.NoPermissions")));
                return true;
            }
        }
        return false;
    }
}

