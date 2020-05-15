package net.hydrotekz.BetterStacking.listeners;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.ItemSpawnEvent;

import net.hydrotekz.BetterStacking.StackingPlugin;
import net.hydrotekz.BetterStacking.support.MultiVersion;

public class Listener implements org.bukkit.event.Listener {

	StackingPlugin plugin;

	public Listener(StackingPlugin blowablePlugin){
		plugin = blowablePlugin;
	}

	@EventHandler (priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onItemSpawn(ItemSpawnEvent e){
		if (plugin.getConfig().getBoolean("Better Stacking") ||
				plugin.getConfig().getBoolean("Void Stacking")){
			List<Entity> entities = e.getEntity().getNearbyEntities(2, 2, 2);
			if (entities == null || entities.isEmpty()) return;
			int fallingBlocks = 0;
			for(Entity entity : entities){
				if(entity.getType() == EntityType.FALLING_BLOCK){
					Location loc = entity.getLocation();
					boolean voidStack = false;
					FallingBlock fallingBlock = (FallingBlock) entity;
					Material mat = MultiVersion.get().getFallingBlockType(fallingBlock);
					if (plugin.getConfig().getBoolean("Void Stacking") && loc.getBlockY() < 0){
						loc.setY(0);
						voidStack = true;
						Block bedrock = loc.getBlock();
						if (!bedrock.getType().isSolid() || bedrock.isLiquid()){

							if (mat == Material.SAND) {
								bedrock.setType(Material.SANDSTONE);
							}
							else if (mat == MultiVersion.get().getRedSand()) {
								bedrock.setType(Material.RED_SANDSTONE);
							}
							else {
								bedrock.setType(MultiVersion.get().getEndStone());
							}

							continue;
						}
					}
					Block below = loc.getBlock();
					if (plugin.Handler.landUpon(below) || voidStack){
						Block block = below.getRelative(BlockFace.UP);

						if (plugin.getConfig().getBoolean("Better Stacking")){
							fallingBlocks++;
							if (fallingBlocks >= 2){
								fallingBlocks = 0;
								continue;
							}

							int attempts = 0;
							while(block.getType() != Material.AIR && !block.isLiquid()){
								attempts++;
								String attempt = String.valueOf(attempts);
								if (attempt.endsWith("0") || attempt.endsWith("2") || attempt.endsWith("4") || attempt.endsWith("6") || attempt.endsWith("8")) continue;
								if (!plugin.Handler.landUpon(block)) return;
								block = block.getRelative(BlockFace.UP);
								if (block.getY() > 256) return;
							}
						}

						e.getEntity().remove();
						entity.remove();

						block.setType(mat);
						MultiVersion.get().setBlockData(block, fallingBlock);
					}
				}
			}
		}
	}
}