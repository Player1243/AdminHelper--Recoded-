package de.YonasCode.listener;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.YonasCode.AdminHelper.Booleans;
import de.YonasCode.AdminHelper.Integers;
import de.YonasCode.AdminHelper.Main;
import de.YonasCode.AdminHelper.Message;
import de.YonasCode.AdminHelper.Permission;

public class ChatListener implements Listener {
	
	private HashMap<String, String> antispam = new HashMap<String, String>();
	
	@EventHandler
	public void onGlobalMute(AsyncPlayerChatEvent event) {
		if(Booleans.GLOBALMUTE) {
			if(!(event.getPlayer().hasPermission(Permission.GLOBALMUTE_IGNORE))) {
				event.getPlayer().sendMessage(Message.replacePlayer(Message.GLOBALMUTE_ACTIVE, "%sender%", event.getPlayer().getName()));
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onAntiSeven(AsyncPlayerChatEvent event) {
		if(Booleans.ANTISEVEN) {
			if(event.getMessage().startsWith("7")) {
				if(!(event.getPlayer().hasPermission(Permission.ANTIADVERTISING_IGNORE))) {
					event.getPlayer().sendMessage(Message.ANTISEVEN);
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onAntiCaps(AsyncPlayerChatEvent event) {
		if(Booleans.ANTICAPS) {
			if(!(event.getPlayer().hasPermission(Permission.ANTICAPS_IGNORE))) {
				char[] message = event.getMessage().toCharArray();
				double totalCaps = 0.0D;
				int maxcaps = Integers.ANTICAPS_TOTAL;
				if(maxcaps >= 100)
					maxcaps = 100;
				if(maxcaps <= 0)
					maxcaps = 0;
				
				for(int i= 0; i < message.length; i++) {
					if(Character.isUpperCase(message[i]))
						totalCaps += 1.0D;
				}
				
				double percent = 100.0D * (totalCaps / message.length);
				if(percent >= maxcaps) {
					event.getPlayer().sendMessage(Message.ANTICAPS);
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onAntiSpam(AsyncPlayerChatEvent event) {
		if(Booleans.ANTISPAM) {
			if(!(event.getPlayer().hasPermission(Permission.ANTISPAM_IGNORE))) {
				if(this.antispam.containsKey(event.getPlayer().getName())) {
					if(this.antispam.get(event.getPlayer().getName()).equals(event.getMessage().toLowerCase())) {
						this.antispam.remove(event.getPlayer().getName());
						event.getPlayer().sendMessage(Message.ANTISPAM);
						event.setCancelled(true);
					} else {
						this.antispam.remove(event.getPlayer().getName());
					}
				} else {
					this.antispam.put(event.getPlayer().getName(), event.getMessage().toLowerCase());
				}
			}
		}
	}
	
	@EventHandler
	public void onAdvertising(AsyncPlayerChatEvent event) {
		if(Booleans.ANTIADVERTISING) {
			if(!(event.getPlayer().hasPermission(Permission.ANTIADVERTISING_IGNORE))) {
				if(Booleans.ANTIADVERTISINGEXTREME) {
					if(Main.ANTISPAM.check(event.getMessage().replaceAll(" ", ""))) {
						event.getPlayer().sendMessage(Message.ANTIADVERTISING);
						if(Booleans.ANTIADVERTISING) {
							for(Player p : Bukkit.getOnlinePlayers()) {
								if(p.hasPermission(Permission.ANTIADVERTISING_MESSAGE))
									p.sendMessage(Message.TAG + ChatColor.DARK_RED + "The player " + ChatColor.GOLD + event.getPlayer().getName() + ChatColor.DARK_RED + " tried to send an link or server-ip. Sended-Message: " + ChatColor.GOLD + event.getMessage());
							}
						}
						event.setCancelled(true);
					}
				} else {
					boolean success = false;
					ArrayList<String> detectedwords = new ArrayList<String>();
					for(String w : event.getMessage().split(" ")) {
						if(Main.ANTISPAM.check(w)) {
							detectedwords.add(w);
							success = true;
							StringBuilder newword = new StringBuilder();
							char[] charedword = w.toCharArray();
							for(int i = 0; i < charedword.length; i++) {
								newword.append(ChatColor.BOLD + "" + ChatColor.DARK_RED + "*");
							}
							event.setMessage(event.getMessage().replaceAll(w, newword.toString() + ChatColor.RESET));
						}
					}
					if((success) && (Booleans.ANTIADVERTISINGMESSAGE)) {
						for(Player p : Bukkit.getOnlinePlayers()) {
							if(p.hasPermission(Permission.ANTIADVERTISING_MESSAGE))
								p.sendMessage(Message.TAG + ChatColor.DARK_RED + "The player " + ChatColor.GOLD + event.getPlayer().getName() + ChatColor.DARK_RED + " tried to send an link or server-ip. Detected-List: " + ChatColor.GOLD + detectedwords.toString());
						}
					}
				}
			}
		}
	}

}
