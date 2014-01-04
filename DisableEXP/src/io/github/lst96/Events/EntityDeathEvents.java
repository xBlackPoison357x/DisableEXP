package io.github.lst96.Events;


import io.github.lst96.DisableEXP.DisableEXP;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathEvents implements Listener {
private DisableEXP plugin;
	
	public  EntityDeathEvents(DisableEXP instance){
		this.plugin = instance;
}

	@EventHandler
	public void onDeath(EntityDeathEvent e){
		if(plugin.getConfig().getBoolean("EXP.Mob")){
		e.setDroppedExp(0);
	}
  }
}
