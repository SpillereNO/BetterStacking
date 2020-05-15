package net.hydrotekz.BetterStacking.support;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface VersionSupport {

	public ItemStack getItemInHand(Player player);
	
	public Material getEndStone();
	
	public Material getRedSand();
	
	public Material getFallingBlockType(FallingBlock falling);
	
	public void setBlockData(Block block, FallingBlock falling);

}