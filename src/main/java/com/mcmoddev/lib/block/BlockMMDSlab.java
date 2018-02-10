package com.mcmoddev.lib.block;

import java.util.Random;

import com.mcmoddev.lib.data.Names;
import com.mcmoddev.lib.material.IMMDObject;
import com.mcmoddev.lib.material.MMDMaterial;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 *
 * @author Jasmine Iwanek
 *
 */
public class BlockMMDSlab extends net.minecraft.block.BlockSlab implements IMMDObject {

	public static final PropertyEnum<BlockMMDSlab.Variant> VARIANT = PropertyEnum.<BlockMMDSlab.Variant>create("variant", BlockMMDSlab.Variant.class);

	final MMDMaterial material;

	/**
	 *
	 * @param material
	 *            The material the slab is made from
	 */
	public BlockMMDSlab(final MMDMaterial material) {
		super(material.getVanillaMaterial());
		this.material = material;
		this.setSoundType(this.material.getSoundType());
		this.blockHardness = this.material.getBlockHardness();
		this.blockResistance = this.material.getBlastResistance();
		this.setHarvestLevel(this.material.getHarvestTool(), this.material.getRequiredHarvestLevel());

		IBlockState iblockstate = this.blockState.getBaseState();

		if (!this.isDouble())
			iblockstate = iblockstate.withProperty(HALF, EnumBlockHalf.BOTTOM);

		this.setDefaultState(iblockstate.withProperty(VARIANT, BlockMMDSlab.Variant.DEFAULT));
	}

	/**
	 * @deprecated
	 */
	@Override
	@Deprecated
	public IBlockState getStateFromMeta(final int meta) {
		IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, BlockMMDSlab.Variant.DEFAULT);

		if (!this.isDouble())
			iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? EnumBlockHalf.BOTTOM : EnumBlockHalf.TOP);

		return iblockstate;
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(final IBlockState state) {
		int i = 0;

		if (!this.isDouble() && (state.getValue(HALF) == EnumBlockHalf.TOP))
			i |= 8;

		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return this.isDouble() ? new BlockStateContainer(this, (IProperty[]) new IProperty[] { VARIANT }) : new BlockStateContainer(this, (IProperty[]) new IProperty[] { HALF, VARIANT });
	}

	@Override
	public String getUnlocalizedName(final int meta) {
		return super.getUnlocalizedName();
	}

	@Override
	public boolean isDouble() {
		return false;
	}

	@Override
	public IProperty<?> getVariantProperty() {
		return VARIANT;
	}

	@Override
	public Comparable<?> getTypeForItem(final ItemStack stack) {
		return BlockMMDSlab.Variant.DEFAULT;
	}

	/**
	 * Get the Item that this Block should drop when harvested.
	 */
	@Override
	public Item getItemDropped(final IBlockState state, final Random rand, final int fortune) {
		return this.material.getItem(Names.SLAB);
	}

	/**
	 * @deprecated
	 */
	@Override
	@Deprecated
	public ItemStack getItem(final World worldIn, final BlockPos pos, final IBlockState state) {
		return this.material.getItemStack(Names.SLAB);
	}

	/**
	 *
	 * @author Jasmine Iwanek
	 *
	 */
	public enum Variant implements IStringSerializable {
		DEFAULT;

		@Override
		public String getName() {
			return "default";
		}
	}

	@Override
	public MMDMaterial getMMDMaterial() {
		return this.material;
	}
}
