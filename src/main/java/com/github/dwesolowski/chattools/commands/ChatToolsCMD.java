package com.github.dwesolowski.chattools.commands;

import com.github.dwesolowski.chattools.ChatTools;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatToolsCMD implements CommandExecutor {

    private final ChatTools plugin;

    public ChatToolsCMD(ChatTools plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Command not allowed in console, must be used in-game only!");
            return true;
        }
        Player player = (Player) sender;
        if ((cmd.getName().equalsIgnoreCase("chattools"))) {
            if (player.hasPermission("chattools.admin")) {
                if (args.length == 1) {

                    if (args[0].equalsIgnoreCase("list")) {
                        this.plugin.wordList.addAll(this.plugin.list);
                        sender.sendMessage(ChatColor.GREEN + "Blacklist " + ChatColor.DARK_GRAY + "» " + ChatColor.RED + this.plugin.wordList.toString().replace("[", "").replace("]", ""));
                        this.plugin.wordList.clear();
                        return true;
                    }
                } else {
                    if (args.length == 2) {
                        if (args[0].equalsIgnoreCase("add")) {
                            String word = getWord(args);
                            this.plugin.list.add(word);
                            this.plugin.getConfig().set("Blacklist", this.plugin.list);
                            this.plugin.saveConfig();
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.AddedWord").replace("%word%", word)));
                            return true;
                        }
                        if (args[0].equalsIgnoreCase("remove")) {
                            String word = getWord(args);
                            if (this.plugin.list.contains(word)) {
                                this.plugin.list.remove(word);
                                plugin.getConfig().set("Blacklist", this.plugin.list);
                                plugin.saveConfig();
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.RemovedWord").replace("%word%", word)));
                                return true;
                            }
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.WordNotFound").replace("%word%", word)));
                            return true;
                        }
                    }
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.NoPermissions")));
                return true;
            }
        }
        return false;
    }

    private static String getWord(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            sb.append(args[i]);
        }
        return sb.toString();
    }
}