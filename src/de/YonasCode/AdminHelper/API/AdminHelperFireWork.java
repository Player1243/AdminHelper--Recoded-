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
package de.YonasCode.AdminHelper.API;

/**
 * 
 * @author Jan Krüger
 * 
 */

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import de.YonasCode.AdminHelper.Main;

public class AdminHelperFireWork {
	
	private int taskid;
	
	public void createFirework() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			
			org.bukkit.entity.Firework fw = (org.bukkit.entity.Firework) p.getWorld().spawn(p.getLocation(), org.bukkit.entity.Firework.class);
			
			FireworkMeta fm = fw.getFireworkMeta();
			
			Random random = new Random();
			Type type = getType();
			FireworkEffect effect = FireworkEffect.builder().flicker(random.nextBoolean()).withColor(getColour(random.nextInt(16)+1)).withFade(getColour(random.nextInt(16)+1)).with(type).trail(random.nextBoolean()).build();
			fm.addEffect(effect);
			fm.setPower(random.nextInt(2)+1);
			fw.setFireworkMeta(fm);
		}
	}
	
	public void moreFireworks(int amount) {
		for(int i = 0; i < amount; i++) {
			createFirework();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void withDelay(int delay, final int amount) {
		int d = delay;
		taskid = Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(Main.INSTANCE, new Runnable() {
			int a = amount;
			@Override
			public void run() {
				if(a != 0) {
					createFirework();
					a--;
				} else {
					Bukkit.getServer().getScheduler().cancelTask(taskid);
				}
			}

		}, 0L, d);
	}
	
	public void onPlayer(Player player) {
		org.bukkit.entity.Firework fw = (org.bukkit.entity.Firework) player.getWorld().spawn(player.getLocation(), org.bukkit.entity.Firework.class);
		
		FireworkMeta fm = fw.getFireworkMeta();
		
		Random random = new Random();
		Type type = getType();
		FireworkEffect effect = FireworkEffect.builder().flicker(random.nextBoolean()).withColor(getColour(random.nextInt(16)+1)).withFade(getColour(random.nextInt(16)+1)).with(type).trail(random.nextBoolean()).build();
		fm.addEffect(effect);
		fm.setPower(random.nextInt(2)+1);
		fw.setFireworkMeta(fm);
	}
	
	public void atLocation(Location location) {
		org.bukkit.entity.Firework fw = (org.bukkit.entity.Firework) location.getWorld().spawn(location, org.bukkit.entity.Firework.class);
		
		FireworkMeta fm = fw.getFireworkMeta();
		
		Random random = new Random();
		Type type = getType();
		FireworkEffect effect = FireworkEffect.builder().flicker(random.nextBoolean()).withColor(getColour(random.nextInt(16)+1)).withFade(getColour(random.nextInt(16)+1)).with(type).trail(random.nextBoolean()).build();
		fm.addEffect(effect);
		fm.setPower(random.nextInt(2)+1);
		fw.setFireworkMeta(fm);
	}
	
	private Color getColour(int c) {
		switch(c) {
			default:
			case 1: return Color.AQUA;
			case 2: return Color.BLACK;
			case 3: return Color.BLUE;
			case 4: return Color.FUCHSIA;
			case 5: return Color.GRAY;
			case 6: return Color.GREEN;
			case 7: return Color.LIME;
			case 8: return Color.MAROON;
			case 9: return Color.NAVY;
			case 10: return Color.OLIVE;
			case 11: return Color.PURPLE;
			case 12: return Color.RED;
			case 13: return Color.SILVER;
			case 14: return Color.TEAL;
			case 15: return Color.WHITE;
			case 16: return Color.YELLOW;
		}
	}
	
	
	private Type getType() {
		Random random = new Random();
		switch(random.nextInt(5) + 1) {
		default:
			case 1: return Type.BALL;
			case 2:	return Type.BALL_LARGE;
			case 3:	return Type.BURST;
			case 4: return Type.CREEPER;
			case 5: return Type.STAR;
		}
	}

}