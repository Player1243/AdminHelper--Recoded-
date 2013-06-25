/*
* AdminHelper for Bukkit.
* Copyright (C) 2012-2013 AdminHelper Team
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/
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
