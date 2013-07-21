package de.YonasCode.AdminHelper.API;

import java.io.IOException;

import org.bukkit.inventory.ItemStack;

import de.YonasCode.AdminHelper.Main;

/**
 * 
 * @author Yonas
 *
 */

public class AdminHelperItemAPI {
	
	/**
	 * With this function you can load the Database into the cache
	 * @throws IOException if they couldn't open the Item Database
	 */
	public void loadItemDatabase() throws IOException {
		Main.ITEMDB.loadItemDatabase();
	}
	
	/**
	 * @param name The name of the item that you search, example: apple
	 * @param amount stack size
	 * @return An item stack with no extra data
	 */
	public ItemStack getItem(String name, int amount) {
		return Main.ITEMDB.getItem(name, amount);
	}
	
	/**
	 * @param id item material id
	 * @param amount stack size
	 * @return An item stack with no extra data 
	 */
	public ItemStack getItem(int id, int amount) {
		return Main.ITEMDB.getItem(id, amount);
	}
	
	/**
	 * @param id item material id
	 * @param metadata the specified metadata 
	 * @param amount stack size
	 * @return An item stack with the specified metadata like 5:3
	 */
	public ItemStack getItem(int id, short metadata, int amount) {
		return Main.ITEMDB.getItem(id, metadata, amount);
	}
	
	/**
	 * @param item material id
	 * @param the specified metadata
	 * @return The item name
	 */
	public String getName(int id, short metadata) {
		return Main.ITEMDB.getName(id, metadata);
	}
	
	
}
