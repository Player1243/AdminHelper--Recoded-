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
import org.bukkit.inventory.ItemStack;

import de.YonasCode.AdminHelper.Main;
import de.YonasCode.AdminHelper.Message;
import de.YonasCode.AdminHelper.Permission;

public class CommandGiveall implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player pl = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("giveall")) {
				if(pl.hasPermission(Permission.GIVEALL_USE)) {
					try {
						if(args.length == 0)
							pl.sendMessage(Message.MOREARGUMENTS);
						else if(args.length == 1) 
							pl.sendMessage(Message.MOREARGUMENTS);
						else if(args.length >= 2) {
							if(args[0].contains(":")) {
								String[] splited = args[0].split(":");
								String id = splited[0];
								String meta = splited[1];
								ItemStack itemstack = Main.ITEMDB.getItem(Integer.valueOf(id), Short.valueOf(meta), Integer.valueOf(args[1]));
								String name = Main.ITEMDB.getName(Integer.valueOf(id), Short.valueOf(meta));
								for(Player p : Bukkit.getOnlinePlayers()) {
									p.getInventory().addItem(itemstack);
									p.sendMessage(Message.GIVEALL.replaceAll("%player%", p.getName()).replaceAll("%amount%", args[1]).replaceAll("%item%", name)); 
								}
							} else {
								if(args[0].matches("\\d+")) {
									ItemStack itemstack = Main.ITEMDB.getItem(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
									String name = Main.ITEMDB.getName(Integer.valueOf(args[0]), Short.valueOf((short) 0));
									for(Player p : Bukkit.getOnlinePlayers()) {
										p.getInventory().addItem(itemstack);
										p.sendMessage(Message.GIVEALL.replaceAll("%player%", p.getName()).replaceAll("%amount%", args[1]).replaceAll("%item%", name)); 
									}
								} else {
									try {
										ItemStack itemstack = Main.ITEMDB.getItem(args[0], Integer.valueOf(args[1]));
										String name = args[0].toLowerCase();
										for(Player p : Bukkit.getOnlinePlayers()) {
											p.getInventory().addItem(itemstack);
											p.sendMessage(Message.GIVEALL.replaceAll("%player%", p.getName()).replaceAll("%amount%", args[1]).replaceAll("%item%", name)); 
										}
									} catch(IllegalArgumentException e) {
										pl.sendMessage(ChatColor.RED + "Unknown Item.");
										e.printStackTrace();
									}
								}
							}
						}
					
					} catch(NumberFormatException e) {
						pl.sendMessage(ChatColor.RED + "Plese use only numbers.");
					} catch(ArrayIndexOutOfBoundsException | NullPointerException e) {
						pl.sendMessage(ChatColor.RED + "Unknown Item-ID");
						e.printStackTrace();
					}
				} else {
					pl.sendMessage(Message.NO_PERMISSIONS);
				}
			}
			return true;
		} else {
			if(cmd.getName().equalsIgnoreCase("giveall")) {
				try {
					if(args.length == 0)
						Main.LOG.info(Message.MOREARGUMENTS);
					else if(args.length == 1) 
						Main.LOG.info(Message.MOREARGUMENTS);
					else if(args.length >= 2) {
						if(args[0].contains(":")) {
							String[] splited = args[0].split(":");
							String id = splited[0];
							String meta = splited[1];
							ItemStack itemstack = Main.ITEMDB.getItem(Integer.valueOf(id), Short.valueOf(meta), Integer.valueOf(args[1]));
							String name = Main.ITEMDB.getName(Integer.valueOf(id), Short.valueOf(meta));
							for(Player p : Bukkit.getOnlinePlayers()) {
								p.getInventory().addItem(itemstack);
								p.sendMessage(Message.GIVEALL.replaceAll("%player%", p.getName()).replaceAll("%amount%", args[1]).replaceAll("%item%", name)); 
							}
						} else {
							if(args[0].matches("\\d+")) {
								ItemStack itemstack = Main.ITEMDB.getItem(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
								String name = Main.ITEMDB.getName(Integer.valueOf(args[0]), (short)0);
								for(Player p : Bukkit.getOnlinePlayers()) {
									p.getInventory().addItem(itemstack);
									p.sendMessage(Message.GIVEALL.replaceAll("%player%", p.getName()).replaceAll("%amount%", args[1]).replaceAll("%item%", name)); 
								}
							} else {
								try {
									ItemStack itemstack = Main.ITEMDB.getItem(args[0], Integer.valueOf(args[1]));
									String name = args[0].toLowerCase();
									for(Player p : Bukkit.getOnlinePlayers()) {
										p.getInventory().addItem(itemstack);
										p.sendMessage(Message.GIVEALL.replaceAll("%player%", p.getName()).replaceAll("%amount%", args[1]).replaceAll("%item%", name)); 
									}
								} catch(IllegalArgumentException e) {
									Main.LOG.info(ChatColor.RED + "Unknown Item.");
								}
							}
						}
						Main.LOG.info("Command successfully used.");
					}
				
				} catch(NumberFormatException e) {
					Main.LOG.info(ChatColor.RED + "Plese use only numbers.");
				} catch(ArrayIndexOutOfBoundsException | NullPointerException e) {
					Main.LOG.info(ChatColor.RED + "Unknown Item-ID");
				}
			}
			return true;
		}
		
	}

}
