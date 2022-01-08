package no.spillere.stacking;

import no.spillere.stacking.handlers.Handler;
import no.spillere.stacking.listeners.Listener;
import org.bstats.bukkit.Metrics;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class StackingPlugin extends JavaPlugin {

    public FileConfiguration config;
    public int configVersion = 1;

    public static StackingPlugin instance;
    public static int mc_version;
    public static String mc_protocol;
    public static Metrics metrics;

    public final no.spillere.stacking.listeners.Listener Listener = new Listener(this);
    public final no.spillere.stacking.handlers.Handler Handler = new Handler(this);

    public void onEnable() {
        String pck = getServer().getClass().getPackage().getName();
        mc_protocol = pck.substring(pck.lastIndexOf('.') + 1);
        String[] split = mc_protocol.replaceFirst("v", "").split("_");
        mc_version = Integer.parseInt(split[0] + split[1]);
        getLogger().info("Running on server with " + mc_version + " / " + mc_protocol + ".");

        // Load plugin
        instance = this;
        loadListeners();
        getDataFolder().mkdir();
        loadConfig();

        // Enable bStats
        int pluginId = 13884;
        metrics = new Metrics(this, pluginId);
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