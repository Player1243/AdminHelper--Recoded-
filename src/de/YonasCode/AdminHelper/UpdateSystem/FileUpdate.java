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
