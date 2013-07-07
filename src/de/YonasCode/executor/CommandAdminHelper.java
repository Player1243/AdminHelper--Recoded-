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
package de.YonasCode.executor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.YonasCode.AdminHelper.Booleans;
import de.YonasCode.AdminHelper.Main;
import de.YonasCode.AdminHelper.Message;
import de.YonasCode.AdminHelper.Permission;

public class CommandAdminHelper implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player pl = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("adminhelper")) {
				if(args.length == 0) {
					ChatColor version = ChatColor.GREEN;
					
					if(Booleans.OPT_OUT)
						Main.UPDATEALERT.updateInformations();
					
					if(Booleans.OPT_OUT) 
						if(Main.UPDATEALERT.updateNeeded())
							version = ChatColor.RED;
					
					pl.sendMessage(ChatColor.GOLD + "---" + Message.TAG + "---");
					pl.sendMessage(ChatColor.AQUA + "Developer: " + ChatColor.GOLD + Main.INSTANCE.getDescription().getAuthors());
					pl.sendMessage(ChatColor.AQUA + "Installed Version: " + version + Main.INSTANCE.getDescription().getVersion());
					pl.sendMessage(ChatColor.AQUA + "Download: " + ChatColor.STRIKETHROUGH + ChatColor.GOLD + "http://krueger-jan.de/plugins/adminhelper/");
				
					if(Booleans.OPT_OUT) {
						pl.sendMessage(ChatColor.AQUA + "Change-Log: " + ChatColor.GOLD + Main.UPDATEALERT.getVersion() + ChatColor.GREEN + " (latest version)");
						for(String s : Main.UPDATEALERT.getChangelog()) {
							if(s != null)
								Bukkit.broadcastMessage(s.replaceAll("\\+", ChatColor.GREEN + "+" + ChatColor.GOLD).replaceAll("\\-", ChatColor.RED + "-" + ChatColor.GOLD) + ChatColor.GOLD);
						}
					}
					
					if((Booleans.OPT_OUT) && (pl.hasPermission(Permission.ADMINHELPER_UPDATE))) {
						if(Main.UPDATEALERT.updateNeeded()) {
							pl.sendMessage(ChatColor.AQUA + "Update: " + ChatColor.RED + "The new version " + ChatColor.GOLD + Main.UPDATEALERT.getVersion() + ChatColor.RED + " is now available on " + ChatColor.GOLD + Main.UPDATEALERT.getLink() + ChatColor.RED + ".");
							pl.sendMessage(ChatColor.GOLD + "If you want to update the plugin follow the instructions.");
							pl.sendMessage(ChatColor.GOLD + "1)" + ChatColor.GREEN + "Type:  /adminhelper update");
							pl.sendMessage(ChatColor.GOLD + "2)" + ChatColor.GREEN + "Ready to use it. I hope it was very easy ;) .");
						} else {
							pl.sendMessage(ChatColor.AQUA + "Update: " + ChatColor.GREEN + "All is good, no update available.");
						}
					}
					
				} else if(args.length == 1) {
					
					if(args[0].equalsIgnoreCase("update")) {
						if(pl.hasPermission(Permission.ADMINHELPER_UPDATE)){
							if(Booleans.OPT_OUT) {
								Main.UPDATEALERT.updateInformations();
								if(Main.UPDATEALERT.updateNeeded()) {
									pl.sendMessage(Message.TAG + ChatColor.GREEN + "Starting the update...");
									Main.FILEUPDATE.updateDownload(pl);
									pl.sendMessage(Message.TAG + ChatColor.GREEN + "Ending the update...");
								} else {
									pl.sendMessage(Message.TAG + ChatColor.GREEN + "No update available.");
								}
							} else {
								 pl.sendMessage(Message.TAG + ChatColor.RED + "Please set " + ChatColor.GOLD + "\"opt-out\"" + ChatColor.RED + " to " + ChatColor.GOLD + "true" + ChatColor.RED + ".");
							}
						} else {
							pl.sendMessage(Message.NO_PERMISSIONS);
						}
					}
					
				} else if(args.length >= 2) {
					if(args[0].equalsIgnoreCase("help")) {
						if(pl.hasPermission(Permission.ADMINHELPER_HELP)) {
							int i;
							try {
								i = Integer.parseInt(args[1]);
							} catch(ArrayIndexOutOfBoundsException e) {
								i = 1;
							}
							if(i == 1) {
								pl.sendMessage(ChatColor.GOLD + "-------------" + Message.TAG + ChatColor.GOLD + "-------------");
				                pl.sendMessage(ChatColor.AQUA + "/globalmute OR //gm " + ChatColor.GOLD + " Muted all Online Players.");
				                pl.sendMessage(ChatColor.AQUA + "/clearchat OR /cc" + ChatColor.GOLD + " Cleared the Chat");
				                pl.sendMessage(ChatColor.AQUA + "/pcc " + ChatColor.GOLD + " Cleared your private Chat.");
				                pl.sendMessage(ChatColor.AQUA + "/ga <item> <amount> " + ChatColor.GOLD + " Give all online players Items.");
				                pl.sendMessage(ChatColor.AQUA + "/ram " + ChatColor.GOLD + " Displayed RAM ussage.");
							} else if(i == 2) {
								pl.sendMessage(ChatColor.GOLD + "-------------" + Message.TAG + ChatColor.GOLD + "-------------");
				                pl.sendMessage(ChatColor.AQUA + "/gamemodeall <gamemode>" + ChatColor.GOLD + " Changed the gamemode of all online players.");
							} else {
								pl.sendMessage(ChatColor.GOLD + "-------------" + Message.TAG + ChatColor.GOLD + "-------------");
								pl.sendMessage(ChatColor.DARK_RED + "Page not found.");
							}
						} else {
							pl.sendMessage(Message.NO_PERMISSIONS);
						}
					}
				}
				
			}
			return true;
			
		} else {
			if(cmd.getName().equalsIgnoreCase("adminhelper")) {
				if(args.length == 0) {
					ChatColor version = ChatColor.GREEN;
					
					if(Booleans.OPT_OUT)
						Main.UPDATEALERT.updateInformations();
					
					if(Booleans.OPT_OUT) 
						if(Main.UPDATEALERT.updateNeeded())
							version = ChatColor.RED;
					
					Main.LOG.info(ChatColor.GOLD + "---" + Message.TAG + "---");
					Main.LOG.info(ChatColor.AQUA + "Developer: " + ChatColor.GOLD + Main.INSTANCE.getDescription().getAuthors());
					Main.LOG.info(ChatColor.AQUA + "Installed Version: " + version + Main.INSTANCE.getDescription().getVersion());
					Main.LOG.info(ChatColor.AQUA + "Download: " + ChatColor.STRIKETHROUGH + ChatColor.GOLD + "http://krueger-jan.de/plugins/adminhelper/");
				
					if(Booleans.OPT_OUT) {
						Main.LOG.info(ChatColor.AQUA + "Change-Log: " + ChatColor.GOLD + Main.UPDATEALERT.getVersion() + ChatColor.GREEN + " (latest version)");
						for(String s : Main.UPDATEALERT.getChangelog()) {
							if(s != null)
								Bukkit.broadcastMessage(s.replaceAll("\\+", ChatColor.GREEN + "+" + ChatColor.GOLD).replaceAll("\\-", ChatColor.RED + "-" + ChatColor.GOLD) + ChatColor.GOLD);
						}
					}
					
					if(Main.UPDATEALERT.updateNeeded()) {
						Main.LOG.info(ChatColor.AQUA + "Update: " + ChatColor.RED + "The new version " + ChatColor.GOLD + Main.UPDATEALERT.getVersion() + ChatColor.RED + " is now available on " + ChatColor.GOLD + Main.UPDATEALERT.getLink() + ChatColor.RED + ".");
						Main.LOG.info(ChatColor.GOLD + "If you want to update the plugin follow the instructions.");
						Main.LOG.info(ChatColor.GOLD + "1)" + ChatColor.GREEN + "Type:  /adminhelper update");
						Main.LOG.info(ChatColor.GOLD + "2)" + ChatColor.GREEN + "Ready to use it. I hope it was very easy ;) .");
					} else {
						Main.LOG.info(ChatColor.AQUA + "Update: " + ChatColor.GREEN + "All is good, no update available.");
					}
					
				} else if(args.length == 1) {
					
					if(args[0].equalsIgnoreCase("update")) {
						if(Booleans.OPT_OUT) {
							Main.UPDATEALERT.updateInformations();
							if(Main.UPDATEALERT.updateNeeded()) {
								Main.LOG.info(Message.TAG + ChatColor.RED + "Sorry, but this command is only for players.");
							} else {
								Main.LOG.info(Message.TAG + ChatColor.GREEN + "No update available.");
							}
						} else {
							Main.LOG.info(Message.TAG + ChatColor.RED + "Please set " + ChatColor.GOLD + "\"opt-out\"" + ChatColor.RED + " to " + ChatColor.GOLD + "true" + ChatColor.RED + ".");
						}
					}
					
				} else if(args.length >= 2) {
					if(args[0].equalsIgnoreCase("help")) {
						int i;
						try {
							i = Integer.parseInt(args[1]);
						} catch(ArrayIndexOutOfBoundsException e) {
							i = 1;
						}
						if(i == 1) {
							Main.LOG.info(ChatColor.GOLD + "-------------" + Message.TAG + ChatColor.GOLD + "-------------");
							Main.LOG.info(ChatColor.AQUA + "/globalmute OR //gm " + ChatColor.GOLD + " Muted all Online Players.");
							Main.LOG.info(ChatColor.AQUA + "/clearchat OR /cc" + ChatColor.GOLD + " Cleared the Chat");
							Main.LOG.info(ChatColor.AQUA + "/pcc " + ChatColor.GOLD + " Cleared your private Chat.");
							Main.LOG.info(ChatColor.AQUA + "/ga <item> <amount> " + ChatColor.GOLD + " Give all online players Items.");
							Main.LOG.info(ChatColor.AQUA + "/ram " + ChatColor.GOLD + " Displayed RAM ussage.");
						} else if(i == 2) {
							Main.LOG.info(ChatColor.GOLD + "-------------" + Message.TAG + ChatColor.GOLD + "-------------");
							Main.LOG.info(ChatColor.AQUA + "/gamemodeall <gamemode>" + ChatColor.GOLD + " Changed the gamemode of all online players.");
						} else {
							Main.LOG.info(ChatColor.GOLD + "-------------" + Message.TAG + ChatColor.GOLD + "-------------");
							Main.LOG.info(ChatColor.DARK_RED + "Page not found.");
						}
					}
				}
				
			}
			return true;
		}
		
	}
	
}
