package net.hydrotekz.BetterStacking.handlers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;

import net.hydrotekz.BetterStacking.StackingPlugin;
import net.hydrotekz.BetterStacking.util.Util;

public class Handler {

	StackingPlugin plugin;

	public Handler(StackingPlugin blowablePlugin){
		plugin = blowablePlugin;
	}

	public boolean landUpon(Block b) {
		Material m = b.getType();
		List<String> blocks = plugin.getConfig().getStringList("Falling Blocks Land.Upon Blocks");
		blocks = lowerStringList(blocks);
		return blocks.contains(m.toString().toLowerCase());
	}

	public void exportConfig() {
		try {
			if (plugin.getConfig().getInt("Config Version") < plugin.configVersion){
				URL inputUrl = plugin.getClass().getResource("/config.yml");
				File dest = new File(plugin.getDataFolder() + File.separator + "config.yml");

				if (dest.exists()){
					File renameTo = new File(dest.getParent() + File.separator + "old_config.yml");
					if (renameTo.exists()) renameTo.delete();
					dest.renameTo(renameTo);
					StackingPlugin.instance.getLogger().info("Previous configuration file was renamed to old_config.yml.");
				}

				Util.copyUrlToFile(inputUrl, dest);
				StackingPlugin.instance.getLogger().info("Configuration file was successfully exported to plugin folder.");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private List<String> lowerStringList(List<String> list){
		List<String> output = new ArrayList<String>();
		for (String item : list){
			output.add(item.toLowerCase());
		}
		return output;
	}
}