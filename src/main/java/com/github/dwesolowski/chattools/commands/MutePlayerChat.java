package com.github.dwesolowski.chattools.commands;

import com.github.dwesolowski.chattools.ChatTools;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class MutePlayerChat implements CommandExecutor {

    private final ChatTools plugin;

    public MutePlayerChat(ChatTools plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Command not allowed in console, must be used in-game only!");
            return true;
        }
        Player player = (Player) sender;
        if ((cmd.getName().equalsIgnoreCase("muteplayerchat")) && (args.length == 0)) {
            if (player.hasPermission("chattools.muteplayerchat")) {
                if (this.plugin.mutedPlayers.contains(player.getUniqueId())) {
                    this.plugin.mutedPlayers.remove(player.getUniqueId());
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.MutePlayerChatEnabled")));
                    return true;
                }
                this.plugin.mutedPlayers.add(player.getUniqueId());
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.MutePlayerChatDisabled")));
                return true;
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.NoPermissions")));
                return true;
            }
        }
        return false;
    }
}
