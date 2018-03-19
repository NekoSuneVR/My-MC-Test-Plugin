package me.chisdealhd.AutoUpdater;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import ocspigot.updatechecker.UpdateChecker;

public class Updater extends JavaPlugin implements Listener{
	public void update(CommandSender sender) {
	    if (UpdateChecker.checkUpdate(1155, 1.2)) {
	    	Bukkit.getServer().getLogger().info(ChatColor.GREEN +"There is a UPDATE! Please Install Newest Version");
    		sender.sendMessage(ChatColor.GREEN +"There is a UPDATE! Please Install Newest Version");
    	} else {
        	Bukkit.getServer().getLogger().info(ChatColor.RED +"There is no Update, Update UP TO DATE!");
        	sender.sendMessage(ChatColor.RED +"There is no Update, Update UP TO DATE!");
		}
	}
}
