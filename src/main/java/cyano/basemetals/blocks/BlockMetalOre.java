package cyano.basemetals.blocks;

import cyano.basemetals.init.Materials;
import cyano.basemetals.material.IMetalObject;
import cyano.basemetals.material.MetalMaterial;
import cyano.basemetals.registry.IOreDictionaryEntry;
import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Ore Block
 */
public class BlockMetalOre extends BlockOre implements IOreDictionaryEntry, IMetalObject {

	private final MetalMaterial material;
	private final String oreDict;

	/**
	 *
	 * @param material The material the ore is made from
	 */
	public BlockMetalOre(MetalMaterial material) {
		super();
		this.setSoundType(SoundType.STONE);
		this.material = material;
		this.blockHardness = Math.max(5f, material.getOreBlockHardness());
		this.blockResistance = Math.max(1.5f, material.getBlastResistance() * 0.75f);
		this.setHarvestLevel("pickaxe", material.getRequiredHarvestLevel());
		this.oreDict = "ore" + material.getCapitalizedName();
		// FMLLog.info(material.getName() + " ore harvest level set to " + material.getRequiredHarvestLevel());
	}

	@Override
	public int getExpDrop(final IBlockState bs, IBlockAccess w, final BlockPos coord, final int i) {
		return 0; // XP comes from smelting
	}

	@Override
	public boolean canEntityDestroy(IBlockState bs, IBlockAccess w, BlockPos coord, Entity entity) {
		if ((this == Materials.starsteel.ore) && (entity instanceof net.minecraft.entity.boss.EntityDragon))
			return false;
		return super.canEntityDestroy(bs, w, coord, entity);
	}

	public MetalMaterial getMetal() {
		return this.material;
	}

	@Override
	public String getOreDictionaryName() {
		return this.oreDict;
	}

	@Override
	public MetalMaterial getMaterial() {
		return this.material;
	}

	@Override
	@Deprecated
	public MetalMaterial getMetalMaterial() {
		return this.material;
	}
}
