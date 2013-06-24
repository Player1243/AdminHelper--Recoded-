package de.YonasCode.AdminHelper.API;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AdminHelperBugFixing {
	
	public void fixGoastBug(Player player) {
		player.teleport(player.getLocation());
	
	}
	
	public void fixUnvisible() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			for(Player pl : Bukkit.getOnlinePlayers()) {
				p.showPlayer(pl);
			}
		}
	}
	
	public void fixAllBugs() {
		fixUnvisible();
		for(Player p : Bukkit.getOnlinePlayers()) {
			fixGoastBug(p);
		}
	}
	
}
