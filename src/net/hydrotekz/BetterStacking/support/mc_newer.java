package net.hydrotekz.BetterStacking.support;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class mc_newer extends MultiVersion implements VersionSupport {

	public mc_newer() {
		super();
	}
	
	/*
	 * 1.9.* - 1.15.*
	 */

	@Override
	public ItemStack getItemInHand(Player player) {
		return player.getInventory().getItemInMainHand();
	}

	@Override
	public Material getEndStone() {
		return Material.END_STONE;
	}

	@Override
	public Material getRedSand() {
		return Material.RED_SAND;
	}

	@Override
	public Material getFallingBlockType(FallingBlock falling) {
		return falling.getBlockData().getMaterial();
	}

	@Override
	public void setBlockData(Block block, FallingBlock falling) {
		block.setBlockData(falling.getBlockData());
	}
	
}