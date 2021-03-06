package dmfmm.StarvationAhoy.proxy;

import dmfmm.StarvationAhoy.Meat.MeatType;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import dmfmm.StarvationAhoy.Meat.item.MItemLoader;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Items;

public class ServerProxy extends CommonProxy{

	@Override
	public void registerKeyBindings() {
		// NOOP
		
	}

	@Override
	public void registerRenderers() {
		// TODO Auto-generated method stub
		MeatType meatType = new MeatType(1);
		meatType.doDeadEntity(EntityCow.class, MItemLoader.deadCow, Items.leather, Items.beef, MItemLoader.skinlessCow);
		meatType.doMeatType(null, "starvationahoy:textures/null.png", "starvationahoy:textures/null.png", "starvationahoy:textures/null.png");
		ModuleMeat.registry.addMeatType(meatType);


		meatType = new MeatType(2);
		meatType.doDeadEntity(EntityPig.class, MItemLoader.deadPig, null, Items.porkchop, MItemLoader.skinlessPig);
		meatType.doMeatType(null, "starvationahoy:textures/null.png", "starvationahoy:textures/null.png", "starvationahoy:textures/null.png");
		ModuleMeat.registry.addMeatType(meatType);

		meatType = new MeatType(3);
		meatType.doDeadEntity(EntityChicken.class, MItemLoader.deadChicken, Items.feather, Items.chicken,  MItemLoader.skinlessChicken);
		meatType.doMeatType(null, "starvationahoy:textures/null.png", "starvationahoy:textures/null.png", "starvationahoy:textures/null.png");
		ModuleMeat.registry.addMeatType(meatType);
	}



}
