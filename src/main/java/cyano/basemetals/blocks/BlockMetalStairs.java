package cyano.basemetals.blocks;

import cyano.basemetals.material.IMetalObject;
import cyano.basemetals.material.MetalMaterial;
import cyano.basemetals.registry.IOreDictionaryEntry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;

/**
 *
 * @author Jasmine Iwanek
 *
 */
public class BlockMetalStairs extends BlockStairs implements IOreDictionaryEntry, IMetalObject {

	final MetalMaterial material;
	private final String oreDict;

	/**
	 *
	 * @param material The material the stairs are made from
	 */
	public BlockMetalStairs(MetalMaterial material) {
		super(material.block.getDefaultState());
		this.setSoundType(SoundType.METAL);
		this.material = material;
		this.oreDict = "stairs" + this.material.getCapitalizedName();
		this.blockHardness = material.getMetalBlockHardness();
		this.blockResistance = material.getBlastResistance();
		this.setHarvestLevel("pickaxe", material.getRequiredHarvestLevel());
	}

	/**
	 *
	 * @param material The material the stairs are made from
	 * @param modelBlock The block to use for the model
	 */
	public BlockMetalStairs(MetalMaterial material, Block modelBlock) {
		super(modelBlock.getDefaultState());
		this.setSoundType(SoundType.METAL);
		this.material = material;
		this.oreDict = "stairs" + this.material.getCapitalizedName();
		this.blockHardness = material.getMetalBlockHardness();
		this.blockResistance = material.getBlastResistance();
		this.setHarvestLevel("pickaxe", material.getRequiredHarvestLevel());
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

	@Override
	public String getOreDictionaryName() {
		return this.oreDict;
	}
}
