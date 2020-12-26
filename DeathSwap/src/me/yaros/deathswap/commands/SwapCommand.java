package me.yaros.deathswap.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import me.yaros.deathswap.Main;

public class SwapCommand implements CommandExecutor {
	
	private Main plugin;
	public int task1;
	public int task2;
	
	public SwapCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("deathswap").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p1 = (Player) sender;
		Player p2 = p1.getServer().getPlayer(args[0]);
		long delay = Long.parseLong(args[1], 10)*20*60;
		BukkitScheduler sched = p1.getServer().getScheduler();
		
		p1.getServer().broadcastMessage("Game Starting! You will be swapping in " + args[1] + " minutes");
		task1 = sched.scheduleSyncRepeatingTask(this.plugin, new Runnable() {
			
			int num = 10;
			
			@Override
			public void run() {
				if(num == 0) {
					p1.getServer().broadcastMessage("Game Started! Good luck!");
					Bukkit.getScheduler().cancelTask(task1);
				}
				else {
					p1.getServer().broadcastMessage(Integer.toString(num--));
				}
			}
			
		}, 20L, 20L);
		
		sched.scheduleSyncRepeatingTask(this.plugin, new Runnable() {
			
			@Override
			public void run() {
				task2 = sched.scheduleSyncRepeatingTask(plugin, new Runnable() {
					
					int num = 15;
					
					@Override
					public void run() {
						if(num == 0) {
							
							Location l1 = p1.getLocation();
							p1.teleport(p2.getLocation());
							p2.teleport(l1);
							Bukkit.getScheduler().cancelTask(task2);
							p1.getServer().broadcastMessage("Teleporting Players!");
						}
						else {
							p1.getServer().broadcastMessage(Integer.toString(num--));
						}
					}
					
				}, 0L, 20L);
				
			}
			
		}, delay+200, delay+300);
		
		
		return true;
	}
}
