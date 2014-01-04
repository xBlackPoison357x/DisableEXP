package io.github.lst96.Events;

import io.github.lst96.DisableEXP.DisableEXP;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceExtractEvent;

public class FurnaceExtractEvents implements Listener {
private DisableEXP plugin;
	
	public  FurnaceExtractEvents(DisableEXP instance){
		this.plugin = instance;
}
	@EventHandler
	public void onSmelt(FurnaceExtractEvent e) {
		if(plugin.getConfig().getBoolean("EXP.Smelt")){
		e.setExpToDrop(0);
	}
  }
}