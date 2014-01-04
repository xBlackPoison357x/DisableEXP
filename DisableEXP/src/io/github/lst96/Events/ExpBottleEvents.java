package io.github.lst96.Events;

import io.github.lst96.DisableEXP.DisableEXP;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;

public class ExpBottleEvents implements Listener {
private DisableEXP plugin;
	
	public  ExpBottleEvents(DisableEXP instance){
		this.plugin = instance;
	}
	@EventHandler
	public void onBottle(ExpBottleEvent e){
		if(plugin.getConfig().getBoolean("EXP.Exp Bottle")){
			e.setExperience(0);
		}
	}
}