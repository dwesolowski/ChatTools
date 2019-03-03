package com.github.dwesolowski.chattools;

import com.github.dwesolowski.chattools.commands.*;
import com.github.dwesolowski.chattools.listeners.*;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChatTools extends JavaPlugin {

    public final ArrayList<UUID> mutedPlayers = new ArrayList<UUID>();

    public final List<String> list = getConfig().getStringList("Blacklist");
    public final ArrayList<String> wordList = new ArrayList<String>();

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        registerCommands();
        registerListeners();
        resetBooleans();
        registerMetrics();
    }

    @Override
    public void onDisable() {
        Bukkit.getPluginManager().disablePlugin(this);
    }

    private void registerCommands() {
        this.getCommand("chattools").setExecutor(new ChatToolsCMD(this));
        this.getCommand("clearplayerchat").setExecutor(new ClearPlayerChat(this));
        this.getCommand("muteplayerchat").setExecutor(new MutePlayerChat(this));
        this.getCommand("clearglobalchat").setExecutor(new ClearGlobalChat(this));
        this.getCommand("muteglobalchat").setExecutor(new MuteGlobalChat(this));
        this.getCommand("reloadchattools").setExecutor(new ReloadChatTools(this));
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerChat(this), this);
        getServer().getPluginManager().registerEvents(new GlobalChat(this), this);
        getServer().getPluginManager().registerEvents(new FilterChat(this), this);
    }

    private void resetBooleans() {
        MuteGlobalChat.toggle = false;
    }

    private void registerMetrics() {
        final MetricsLite metrics = new MetricsLite(this);
    }
}