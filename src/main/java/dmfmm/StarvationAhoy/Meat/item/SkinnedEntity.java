package dmfmm.StarvationAhoy.Meat.item;

import dmfmm.StarvationAhoy.Core.SATabs;
import net.minecraft.item.Item;

public class SkinnedEntity extends Item{
	
	public SkinnedEntity(String name, String texture){
		super();
		this.setUnlocalizedName(name);
		this.setTextureName(texture);
		this.setCreativeTab(SATabs.INSTANCE);
	}
}
