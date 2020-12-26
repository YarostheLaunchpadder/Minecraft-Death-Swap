package me.yaros.deathswap.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.yaros.deathswap.Main;

public class EndCommand implements CommandExecutor {
	private Main plugin;
	
	public EndCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("endswap").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	
		Player p = (Player) sender;
		p.getServer().broadcastMessage("Stopping Death Swap!");
		p.getServer().getScheduler().cancelTasks(this.plugin);
		return false;
	}
}