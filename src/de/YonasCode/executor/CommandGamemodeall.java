/*
* Copyright 2011-2013 Jan Kr�ger. All rights reserved.
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
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.YonasCode.AdminHelper.Main;
import de.YonasCode.AdminHelper.Message;
import de.YonasCode.AdminHelper.Permission;

public class CommandGamemodeall implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player pl = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("gamemodeall")) {
				if(pl.hasPermission(Permission.GAMEMODEALL_USE)) {
					if(args.length >= 1) {
						GameMode mode = null;
			            if ((args[0].equalsIgnoreCase("survival")) || (args[0].equalsIgnoreCase("0")))
			            	mode = GameMode.SURVIVAL;
			            else if ((args[0].equalsIgnoreCase("creative")) || (args[0].equalsIgnoreCase("1")))
			            	mode = GameMode.CREATIVE;
			            else if ((args[0].equalsIgnoreCase("adventure")) || (args[0].equalsIgnoreCase("2")))
			            	mode = GameMode.ADVENTURE;
			            else
			            	mode = GameMode.SURVIVAL;
			            for(Player p : Bukkit.getOnlinePlayers()) {
			            	p.setGameMode(mode);
			            	p.sendMessage(ChatColor.GREEN + "Your gamemode has changed to " + ChatColor.GOLD + mode.toString() + ChatColor.GREEN + ".");
			            }
					} else {
						pl.sendMessage(Message.MOREARGUMENTS);
					}
				} else {
					pl.sendMessage(Message.NO_PERMISSIONS);
				}
			}
			
			return true;
		} else {
			if(cmd.getName().equalsIgnoreCase("gamemodeall")) {
				if(args.length >= 1) {
					GameMode mode = null;
					if ((args[0].equalsIgnoreCase("survival")) || (args[0].equalsIgnoreCase("0")))
						mode = GameMode.SURVIVAL;
			        else if ((args[0].equalsIgnoreCase("creative")) || (args[0].equalsIgnoreCase("1")))
			        	mode = GameMode.CREATIVE;
			        else if ((args[0].equalsIgnoreCase("adventure")) || (args[0].equalsIgnoreCase("2")))
			        	mode = GameMode.ADVENTURE;
			        else
			        	mode = GameMode.SURVIVAL;
			        for(Player p : Bukkit.getOnlinePlayers()) {
			        	p.setGameMode(mode);
			        	p.sendMessage(ChatColor.GREEN + "Your gamemode has changed to " + ChatColor.GOLD + mode.toString() + ChatColor.GREEN + ".");
			        }
			        Main.LOG.info("Command was successfully used.");
				} else {
					Main.LOG.info(Message.MOREARGUMENTS);
				}
			}
			return true;
		}
		
	}

}