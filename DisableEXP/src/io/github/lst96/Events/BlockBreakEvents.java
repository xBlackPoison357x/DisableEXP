package io.github.lst96.Events;

import io.github.lst96.DisableEXP.DisableEXP;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakEvents implements Listener {
private DisableEXP plugin;
	
	public  BlockBreakEvents(DisableEXP instance){
		this.plugin = instance;
}


@EventHandler
public void onBreak(BlockBreakEvent e) {
	if(plugin.getConfig().getBoolean("EXP.Ore")){
	e.setExpToDrop(0);
    }
  }
}