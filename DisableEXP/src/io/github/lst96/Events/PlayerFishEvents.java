package io.github.lst96.Events;

import io.github.lst96.DisableEXP.DisableEXP;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class PlayerFishEvents implements Listener {
private DisableEXP plugin;
	
	public  PlayerFishEvents(DisableEXP instance){
		this.plugin = instance;
	}
	@EventHandler
	public void onFish(PlayerFishEvent e) {
		if(plugin.getConfig().getBoolean("EXP.Fish")){
			e.setExpToDrop(0);
		}
	}
}