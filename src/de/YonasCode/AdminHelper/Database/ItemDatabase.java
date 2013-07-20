package de.YonasCode.AdminHelper.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

import de.YonasCode.AdminHelper.Main;


public class ItemDatabase {
	
	private ArrayList<String> cache = new ArrayList<String>();
	
	public void loadItemDatabase() throws IOException {
		InputStream in = null;
		BufferedReader reader = null;
		
		try {
			in = Main.INSTANCE.getResource("ItemDatabase.csv");
			reader = new BufferedReader(new InputStreamReader(in));
			
			String line;
			
			while(((line = reader.readLine()) != null)) {
				if(!(line.startsWith("#")))
					cache.add(line);
			}
			Main.LOG.info("The cache contains now " + cache.size() + " items.");
		} finally {
			if(reader != null) reader.close();
			if(in != null) in.close();
		}
	}
	
	public ItemStack getItem(String name, int amount) throws NullPointerException {
		ItemStack itemstack = null;
		for(String c : cache) {
			String[] i = c.split(",");
			if(i[0].equalsIgnoreCase(name.toLowerCase())) {
				itemstack = new ItemStack(Integer.valueOf(i[1]), amount, Short.valueOf(i[2]));
			}
		}
		return itemstack;
	}
	
	public ItemStack getItem(int id, int amount) throws NullPointerException {
		ItemStack itemstack = null;
		for(String c : cache) {
			String[] i = c.split(",");
			if(Integer.valueOf(i[1]) == id)
				itemstack = new ItemStack(Integer.valueOf(i[1]), amount, Short.valueOf(i[2]));
		}
		return itemstack;
	}
	
	public ItemStack getItem(int id, short metadata, int amount) throws NullPointerException {
		ItemStack itemstack = null;
		for(String c : cache) {
			String[] i = c.split(",");
			if((Integer.valueOf(i[1]) == id) && Short.valueOf(i[2]) == metadata)
				itemstack = new ItemStack(Integer.valueOf(i[1]), amount, Short.valueOf(i[2]));
		}
		return itemstack;
	}
	
	public String getName(int id, short metadata) {
		String name = null;
			for(String c : cache) {
				String[] i = c.split(",");
				if((Integer.valueOf(i[1]) == id) && (Short.valueOf(i[2]) == metadata))
					name = i[0];
			}
		return name.toLowerCase();
	}
}
