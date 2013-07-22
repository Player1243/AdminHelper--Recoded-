/*
* Copyright 2011-2013 Jan Krüger. All rights reserved.
*
* Redistribution and use in source and binary forms, with or without modification, are
* permitted provided that the following conditions are met:
*
* 1. Redistributions of source code must retain the above copyright notice, this list of
* conditions and the following disclaimer.
*
* 2. Redistributions in binary form must reproduce the above copyright notice, this list
* of conditions and the following disclaimer in the documentation and/or other materials
* provided with the distribution.
*
* THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
* WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
* FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
* CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
* SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
* ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
* NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
* ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
* The views and conclusions contained in the software and documentation are those of the
* authors and contributors and should not be interpreted as representing official policies,
* either expressed or implied, of anybody else.
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