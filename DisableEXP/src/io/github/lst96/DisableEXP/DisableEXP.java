package io.github.lst96.DisableEXP;

import io.github.lst96.Events.BlockBreakEvents;
import io.github.lst96.Events.EntityDeathEvents;
import io.github.lst96.Events.ExpBottleEvents;
import io.github.lst96.Events.FurnaceExtractEvents;
import io.github.lst96.Events.PlayerFishEvents;
import io.github.lst96.DisableEXP.commands.DisableReload;
import io.github.lst96.DisableEXP.metrics.Metrics;

import java.io.IOException;
import java.util.logging.Logger;

import net.gravitydevelopment.updater.Updater;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class DisableEXP extends JavaPlugin{
	public final Logger logger = Logger.getLogger("Minecraft");
	public PluginDescriptionFile pdfFile;
	public String PREFIX;
	public boolean autoUpdate = false;
	Updater updater;
	private boolean compatible;

	public void onEnable() {
		pdfFile = this.getDescription();
		PREFIX = "[" + pdfFile.getName() + "]";
		this.logger.info(PREFIX + " DisableEXP version " + pdfFile.getVersion() + " has been enabled.");
		this.logger.info(PREFIX + " Developed by: " + pdfFile.getAuthors());
		getConfig().options().copyDefaults(true);
	    saveConfig();
		getServer().getPluginManager().registerEvents(new EntityDeathEvents(this), this);
		getServer().getPluginManager().registerEvents(new BlockBreakEvents(this), this);
		getServer().getPluginManager().registerEvents(new FurnaceExtractEvents(this), this);
		getServer().getPluginManager().registerEvents(new PlayerFishEvents(this), this);
		getServer().getPluginManager().registerEvents(new ExpBottleEvents(this), this);
		getCommand("disablereload").setExecutor(new DisableReload(this));
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException localIOException) {
		}
		autoUpdate = this.getConfig().getBoolean("autoupdate");
		if (autoUpdate) {
			setupUpdater();
			String mcVersion = Bukkit.getBukkitVersion();
			this.compatible = mcVersion.startsWith("1.7.2");
			if ((this.getConfig().getBoolean("check_bukkit_compatibility"))
					&& (!this.compatible)) {
				this.logger.info("[DisableEXP] is not compatible with " + Bukkit.getVersion());
				getServer().getPluginManager().disablePlugin(this);
				return;
	}
		}
	}
	public void onDisable() {
		this.logger.info(PREFIX + " DisableEXP Disabled.");
	}
	private void setupUpdater() {

		Updater updater = new Updater(this, 71250, this.getFile(), Updater.UpdateType.DEFAULT, true);
		Updater.UpdateResult result = updater.getResult();
		switch (result) {
		case SUCCESS:
			// Success: The updater found an update, and has readied it to be
			// loaded the next time the server restarts/reloads
			break;
		case NO_UPDATE:
			// No Update: The updater did not find an update, and nothing was
			// downloaded.
			break;
		case DISABLED:
			// Won't Update: The updater was disabled in its configuration file.
			break;
		case FAIL_DOWNLOAD:
			// Download Failed: The updater found an update, but was unable to
			// download it.
			break;
		case FAIL_DBO:
			// dev.bukkit.org Failed: For some reason, the updater was unable to
			// contact DBO to download the file.
			break;
		case FAIL_NOVERSION:
			// No version found: When running the version check, the file on DBO
			// did not contain the a version in the format 'vVersion' such as
			// 'v1.0'.
			break;
		case FAIL_BADID:
			// Bad id: The id provided by the plugin running the updater was
			// invalid and doesn't exist on DBO.
			break;
		case FAIL_APIKEY:
			// Bad API key: The user provided an invalid API key for the updater
			// to use.
			break;
		case UPDATE_AVAILABLE:
			// There was an update found, but because you had the UpdateType set
			// to NO_DOWNLOAD, it was not downloaded.
		}
	}
}