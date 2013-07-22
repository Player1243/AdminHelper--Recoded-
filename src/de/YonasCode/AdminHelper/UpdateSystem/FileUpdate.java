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
package de.YonasCode.AdminHelper.UpdateSystem;

import de.YonasCode.AdminHelper.Main;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Field;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.UnknownDependencyException;

public class FileUpdate {
	
	private String getLink(String url) {
	    return "http://krueger-jan.de/files/AdminHelper.jar";
	}
	
	public boolean updateDownload(Player sender) {
		String path = null;
		
		path = getLink(Main.UPDATEALERT.getLink());
		
		boolean success = false;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		String pluginPath = "plugins/" + Main.INSTANCE.getDescription().getName() + ".jar";
		
		if(path != null) {
			try {
				URL site = new URL(path);
				bis = new BufferedInputStream(site.openStream());
				fos = new FileOutputStream(pluginPath);
				sender.sendMessage(ChatColor.GREEN + "Downloading: " + ChatColor.GOLD + path);
				
				int count = -1;
				int read;
				while((read = bis.read()) != -1) {
					fos.write((byte)read);
					count++;
				}
				
		        sender.sendMessage(ChatColor.GREEN + "Plugin downloaded! (" + ChatColor.GOLD + count / 1024 + ChatColor.GREEN + "KB)");
		        sender.sendMessage(ChatColor.GREEN + "Reloading plugin");
		        
		        //Main.INSTANCE.getServer().getPluginManager().disablePlugin(Main.INSTANCE.getServer().getPluginManager().loadPlugin(new File(pluginPath)));
		        //Main.INSTANCE.getServer().getPluginManager().enablePlugin(Main.INSTANCE.getServer().getPluginManager().loadPlugin(new File(pluginPath)));
		        reload();
		        
		        sender.sendMessage(ChatColor.GREEN + "Update successfully.");
		        success = true;
				
			} catch (Exception e) {
		    	sender.sendMessage(ChatColor.RED + "Failed to update: " + ChatColor.GOLD + e.getMessage());
			}
			
		}
		
		if(fos != null) {
			try {
				fos.close();
			} catch (IOException e) {
				sender.sendMessage(ChatColor.RED + "Couldn't close output stream!");
		    }
		}
		
		if (bis != null) {
			try {
				bis.close();
			} catch (IOException e) {
				sender.sendMessage(ChatColor.RED + "Couldn't close input stream!");
			}
		}
		
		return success;
	}

	@SuppressWarnings("unchecked")
	private void reload() throws UnknownDependencyException, InvalidPluginException, InvalidDescriptionException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		PluginManager manager = Main.INSTANCE.getServer().getPluginManager();
		SimplePluginManager spmanager = (SimplePluginManager) manager;
		//unload
		Plugin pluginunload = manager.getPlugin(Main.INSTANCE.getDescription().getName());
		manager.disablePlugin(pluginunload);
		
        if (spmanager != null) {
            Field pluginsField = spmanager.getClass().getDeclaredField("plugins");
            pluginsField.setAccessible(true);
            List<Plugin> plugins = (List<Plugin>) pluginsField.get(spmanager);

            Field lookupNamesField = spmanager.getClass().getDeclaredField("lookupNames");
            lookupNamesField.setAccessible(true);
            Map<String, Plugin> lookupNames = (Map<String, Plugin>) lookupNamesField.get(spmanager);

            Field commandMapField = spmanager.getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            SimpleCommandMap commandMap = (SimpleCommandMap) commandMapField.get(spmanager);

            Field knownCommandsField = null;
            Map<String, Command> knownCommands = null;

            if (commandMap != null) {
                knownCommandsField = commandMap.getClass().getDeclaredField("knownCommands");
                knownCommandsField.setAccessible(true);
                knownCommands = (Map<String, Command>) knownCommandsField.get(commandMap);
            }

            for (Plugin plugin : manager.getPlugins()) {
                if (plugin.getDescription().getName().equalsIgnoreCase(Main.INSTANCE.getDescription().getName())) {
                    manager.disablePlugin(plugin);

                    if (plugins != null && plugins.contains(plugin)) {
                        plugins.remove(plugin);
                    }

                    if (lookupNames != null && lookupNames.containsKey(Main.INSTANCE.getDescription().getName())) {
                        lookupNames.remove(Main.INSTANCE.getDescription().getName());
                    }

                    if (commandMap != null) {
                        for (Iterator<Map.Entry<String, Command>> it = knownCommands.entrySet().iterator(); it.hasNext();) {
                            Map.Entry<String, Command> entry = it.next();

                            if (entry.getValue() instanceof PluginCommand) {
                                PluginCommand command = (PluginCommand) entry.getValue();

                                if (command.getPlugin() == plugin) {
                                    command.unregister(commandMap);
                                    it.remove();
                                }
                            }
                        }
                    }
                }
            }
        }
        
		//load
		Plugin pluginload = manager.loadPlugin(new File("plugins", Main.INSTANCE.getDescription().getName() + ".jar"));
		pluginload.onLoad();
		manager.enablePlugin(pluginload);
	}
	
	
}