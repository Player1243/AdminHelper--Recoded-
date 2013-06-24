package de.YonasCode.AdminHelper;

import de.YonasCode.AdminHelper.Metrics.Metrics;
import de.YonasCode.executor.CommandsManager;
import de.YonasCode.listener.ChatListener;
import de.YonasCode.listener.JoinListener;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class AdminHelper {
	
	public void loadConfig() {
		FileConfiguration cfg = Main.INSTANCE.getConfig();
		cfg.options().copyDefaults(true);
		Main.INSTANCE.saveConfig();
	}
	
	public void loadMetrics() {
	    try {
	        Metrics metrics = new Metrics(Main.INSTANCE);
	        metrics.start();
	    } catch (IOException e) {
	        Main.LOG.warning("Can't connect to statistic server...");
	    }
	}
	
	public void unloadMetrics() {
		try {
			Metrics metrics = new Metrics(Main.INSTANCE);
			metrics.disable();
		}catch(IOException e) {
			Main.LOG.warning("Can't disable the service...");
		}
	}
	
	public void loadCommands() {
		CommandsManager.registerCommands();
	}
	
	public void loadListeners() {
		Bukkit.getServer().getPluginManager().registerEvents(new ChatListener(), Main.INSTANCE);
		Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(), Main.INSTANCE);
	}

}
