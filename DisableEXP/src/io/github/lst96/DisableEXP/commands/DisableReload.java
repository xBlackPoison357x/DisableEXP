package io.github.lst96.DisableEXP.commands;

import io.github.lst96.DisableEXP.DisableEXP;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DisableReload implements CommandExecutor {
	private DisableEXP plugin;
	
	public DisableReload(DisableEXP instance){
		this.plugin = instance;
	}
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	  {
	    if (commandLabel.equalsIgnoreCase("disablereload")) {
	      if ((sender.isOp()) || (sender.hasPermission("disable.reload"))) {
	        plugin.reloadConfig();
	        sender.sendMessage(ChatColor.DARK_RED + "[DisableEXP]" + " " + ChatColor.RED + "Configuration Reloaded!");
	        return true;
	      }
	      sender.sendMessage(ChatColor.DARK_RED + "[DisableEXP]" + ChatColor.RED + " I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
	      return true;
	    }
	    return false;
	  }
}