package me.yaros.deathswap;

import org.bukkit.plugin.java.JavaPlugin;

import me.yaros.deathswap.commands.EndCommand;
import me.yaros.deathswap.commands.SwapCommand;

public class Main extends JavaPlugin {
	
	public void onEnable() {
		new SwapCommand(this);
		new EndCommand(this);
	}
	
}
