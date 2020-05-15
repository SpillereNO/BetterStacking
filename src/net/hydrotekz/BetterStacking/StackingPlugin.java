package net.hydrotekz.BetterStacking;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.hydrotekz.BetterStacking.handlers.*;
import net.hydrotekz.BetterStacking.listeners.*;

public class StackingPlugin extends JavaPlugin {

	public static StackingPlugin instance;

	public FileConfiguration config;
	public int configVersion = 1;
	public static int mc_version;
	public static String mc_protocol;

	public final Listener Listener = new Listener(this);
	public final Handler Handler = new Handler(this);

	public void onEnable() {
		String pck = getServer().getClass().getPackage().getName();
		mc_protocol = pck.substring(pck.lastIndexOf('.') + 1);
		String[] split = mc_protocol.replaceFirst("v", "").split("_");
		mc_version = Integer.parseInt(split[0] + split[1]);
		getLogger().info("Running on server with " + mc_version + " / " + mc_protocol + ".");

		instance = this;
		loadListeners();
		getDataFolder().mkdir();
		loadConfig();
	}

	public void loadListeners() {
		getServer().getPluginManager().registerEvents(Listener, this);
	}

	private void loadConfig() {
		config = getConfig();
		config.options().copyDefaults(true);
		config.addDefault("Config Version", 0);
		Handler.exportConfig();
	}
}