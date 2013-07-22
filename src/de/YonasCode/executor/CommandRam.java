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

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.YonasCode.AdminHelper.Main;
import de.YonasCode.AdminHelper.Message;
import de.YonasCode.AdminHelper.Permission;

public class CommandRam implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player pl = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("ram")) {
				if(pl.hasPermission(Permission.RAM_USE)) {
					pl.sendMessage(ChatColor.AQUA + "----" + ChatColor.GOLD + "Server" + ChatColor.AQUA + "----");
					pl.sendMessage(ChatColor.AQUA + "Free-RAM: " + ChatColor.GOLD + (Main.RUNTIME.maxMemory() - Main.RUNTIME.totalMemory()) / 1048576L + "MB");
					pl.sendMessage(ChatColor.AQUA + "Used-RAM: " + ChatColor.GOLD + Main.RUNTIME.totalMemory() / 1048576L + "MB");
					pl.sendMessage(ChatColor.AQUA + "Maxi-RAM: " + ChatColor.GOLD + Main.RUNTIME.maxMemory() / 1048576L + "MB");
					pl.sendMessage(ChatColor.AQUA + "Utilization: " + ramCheck() + usedMemory() + "%");
				} else {
					pl.sendMessage(Message.NO_PERMISSIONS);
				}
			}
			return true;
		} else {
			if(cmd.getName().equalsIgnoreCase("ram")) {
				Main.LOG.info(ChatColor.AQUA + "----" + ChatColor.GOLD + "Server" + ChatColor.AQUA + "----");
				Main.LOG.info(ChatColor.AQUA + "Free-RAM: " + ChatColor.GOLD + (Main.RUNTIME.maxMemory() - Main.RUNTIME.totalMemory()) / 1048576L + "MB");
				Main.LOG.info(ChatColor.AQUA + "Used-RAM: " + ChatColor.GOLD + Main.RUNTIME.totalMemory() / 1048576L + "MB");
				Main.LOG.info(ChatColor.AQUA + "Maxi-RAM: " + ChatColor.GOLD + Main.RUNTIME.maxMemory() / 1048576L + "MB");
				Main.LOG.info(ChatColor.AQUA + "Utilization: " + ramCheck() + usedMemory() + "%");
			}
			return true;
		}
	
	}
	
	private ChatColor ramCheck() {
		double raminpro = usedMemory();
		if(raminpro <= 50.0D)
			return ChatColor.GREEN;
		else if((raminpro >= 50.1D) && (raminpro <= 80.0D)) 
			return ChatColor.YELLOW;
		else if((raminpro >= 80.1D) && (raminpro <= 100.0D))
			return ChatColor.DARK_RED;
		else
			return ChatColor.WHITE;
	}
	
	private double usedMemory() {
		double maxmemory = Main.RUNTIME.maxMemory();
	    double usedmemory = Main.RUNTIME.totalMemory();
	    double maxm = maxmemory / 1048576.0D;
	    maxm /= 100.0D;
	    double usedm = usedmemory / 1048576.0D;
	    double auslastung = usedm / maxm;
	    auslastung *= 100.0D;
	    auslastung = Math.round(auslastung);
	    auslastung /= 100.0D;
	    return auslastung;
	}

}