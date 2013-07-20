package de.YonasCode.AdminHelper.API;

import org.bukkit.inventory.ItemStack;

import de.YonasCode.AdminHelper.Main;

public class AdminHelperItemAPI {

	public ItemStack getItem(String name, int amount) {
		return Main.ITEMDB.getItem(name, amount);
	}
	
	public ItemStack getItem(int id, int amount) {
		return Main.ITEMDB.getItem(id, amount);
	}
	
	public ItemStack getItem(int id, short metadata, int amount) {
		return Main.ITEMDB.getItem(id, metadata, amount);
	}
	
	public String getName(int id, short metadata) {
		return Main.ITEMDB.getName(id, metadata);
	}
	
}
