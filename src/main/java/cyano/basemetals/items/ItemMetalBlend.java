package cyano.basemetals.items;

import cyano.basemetals.init.Achievements;
import cyano.basemetals.material.IMetalObject;
import cyano.basemetals.material.MetalMaterial;
import cyano.basemetals.registry.IOreDictionaryEntry;
import cyano.basemetals.util.Config.Options;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Blends
 * 
 * @author DrCyano
 *
 */
public class ItemMetalBlend extends net.minecraft.item.Item implements IOreDictionaryEntry, IMetalObject {

	private final MetalMaterial material;
	private final String oreDict;

	/**
	 *
	 * @param material The material to make the blend from
	 */
	public ItemMetalBlend(MetalMaterial material) {
		this.material = material;
		this.setCreativeTab(CreativeTabs.MATERIALS);
		this.oreDict = "dust" + this.material.getCapitalizedName(); // same oreDict entry as powder
	}

	@Override
	public void onCreated(final ItemStack item, final World world, final EntityPlayer crafter) {
		super.onCreated(item, world, crafter);
		// achievement
    	if (Options.ENABLE_ACHIEVEMENTS) {
    		crafter.addStat(Achievements.metallurgy, 1);
    	}
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
