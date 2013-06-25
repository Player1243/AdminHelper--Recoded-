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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.UnknownDependencyException;

public class FileUpdate {
	
	private String getLink(String url) {
		String link = "";
		Scanner reader = null;
		try {
			reader = new Scanner(new URL(url).openStream());
			while(reader.hasNext()) {
				String line = reader.nextLine();
				if(line.trim().startsWith("<li class=\"user-action user-action-download\"><span><a href=\"")) {
					line = line.trim();
					line = line.replaceAll("<li class=\"user-action user-action-download\"><span><a href=\"", "");
					line = line.replaceFirst("\">Download</a></span></li>", "");
					link = line;
				}
			}
		} catch(IOException e) {
			link = null;
		}
		
		try {
			link = link.trim();
		} catch(CommandException | NullPointerException e) {} finally {
			if(reader != null)
				reader.close();
		}
		
	    return link;
	}
	
	public boolean updateDownload(Player sender) {
		String path = null;
		
		path = getLink(Main.UPDATEALERT.getLink());
		
		boolean success = false;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		String pluginPath = "plugins/" + Main.INSTANCE.getName() + ".jar";
		
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
		        Main.INSTANCE.getPluginLoader().disablePlugin(Main.INSTANCE);
		        Main.INSTANCE.getServer().getPluginManager().enablePlugin(Main.INSTANCE.getServer().getPluginManager().loadPlugin(new File(pluginPath)));
		        sender.sendMessage(ChatColor.GREEN + "Update successfully.");
		        success = true;
				
		    } catch (NullPointerException|CommandException|UnknownHostException|UnknownDependencyException|InvalidPluginException|InvalidDescriptionException e) {
		    	sender.sendMessage(ChatColor.RED + "Failed to update: " + ChatColor.GOLD + e.getMessage());
		    } catch (FileNotFoundException e) {
		    	sender.sendMessage(ChatColor.RED + "Failed to update: " + ChatColor.GOLD + e.getMessage());
		    } catch (MalformedURLException e) {
		    	sender.sendMessage(ChatColor.RED + "Failed to update: " + ChatColor.GOLD + e.getMessage());
		    } catch (IOException e) {
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
	
}
