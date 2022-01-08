package no.spillere.stacking.support;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class mc_legacy extends MultiVersion implements VersionSupport {

	public mc_legacy() {
		super();
	}

	/*
	 * 1.7.* - 1.8.*
	 */

	@Override
	public ItemStack getItemInHand(Player player) {
		return player.getInventory().getItemInHand();
	}

	@Override
	public Material getEndStone() {
		return Material.ENDER_STONE;
	}

	@Override
	public Material getRedSand() {
		return Material.SAND;
	}

	@Override
	public Material getFallingBlockType(FallingBlock falling) {
		return falling.getMaterial();
	}

	@Override
	public void setBlockData(Block block, FallingBlock falling) {
		block.setData(falling.getBlockData());
	}
}