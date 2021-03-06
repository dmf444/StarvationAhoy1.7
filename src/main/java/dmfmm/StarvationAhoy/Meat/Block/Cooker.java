package dmfmm.StarvationAhoy.Meat.Block;

import dmfmm.StarvationAhoy.Core.SATabs;
import dmfmm.StarvationAhoy.Meat.Block.multiblock.CookerTileEntity;
import dmfmm.StarvationAhoy.Meat.Block.multiblock.MultiBlockChecking;
import dmfmm.StarvationAhoy.Meat.Block.multiblock.TileEntityMultiBlock;
import dmfmm.StarvationAhoy.Meat.MeatRegistry;
import dmfmm.StarvationAhoy.Meat.MeatType;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Matthew on 2/28/2015.
 */
public class Cooker extends BlockContainer {
    protected Cooker() {
        super(Material.anvil);
        //this.setCreativeTab(SATabs.INSTANCE);
        this.setBlockTextureName("starvationahoy:clearBlock");
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new CookerTileEntity(null);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float hitX, float hitY, float hitZ) {
        if (player.inventory.mainInventory[player.inventory.currentItem] == null){
            TileEntityMultiBlock te = (TileEntityMultiBlock) world.getTileEntity(x, y, z);
            if (te.multiBlockStructure.sharedData.hasKey("RoastingItem")){
                ItemStack toSpawnInWorld = ItemStack.loadItemStackFromNBT(te.multiBlockStructure.sharedData.getCompoundTag("RoastingItem"));
                te.multiBlockStructure.sharedData = new NBTTagCompound();
                EntityItem e = new EntityItem(world, x, y+2, z, toSpawnInWorld);
                if (!world.isRemote){world.spawnEntityInWorld(e);}
                te.multiBlockStructure.syncData(te.multiBlockStructure, te.multiBlockStructure.bPos, te.multiBlockStructure.x, te.multiBlockStructure.y, te.multiBlockStructure.z, world);
            }
        }
        if (ModuleMeat.registry.isSkinnedItem(player.inventory.mainInventory[player.inventory.currentItem]).value){
            MeatType t = ModuleMeat.registry.isSkinnedItem(player.inventory.mainInventory[player.inventory.currentItem]).meat;
            TileEntityMultiBlock te = (TileEntityMultiBlock) world.getTileEntity(x, y, z);
            if (te.multiBlockStructure.sharedData.hasKey("RoastingItem")){ return false;}
            te.multiBlockStructure.sharedData.setTag("RoastingItem", player.inventory.mainInventory[player.inventory.currentItem].writeToNBT(new NBTTagCompound()));
            te.multiBlockStructure.sharedData.setTag("CookedItem", new ItemStack(t.items.meat, 1).writeToNBT(new NBTTagCompound()));
            te.multiBlockStructure.syncData(te.multiBlockStructure, te.multiBlockStructure.bPos, te.multiBlockStructure.x, te.multiBlockStructure.y, te.multiBlockStructure.z, world);
            player.inventory.mainInventory[player.inventory.currentItem].stackSize--;
            if (player.inventory.mainInventory[player.inventory.currentItem].stackSize < 1){
                player.inventory.mainInventory[player.inventory.currentItem] = null;
            }
        }


        return false;
    }



    public boolean isOpaqueCube(){
        return false;
    }

    public boolean renderAsNormalBlock(){
        return false;
    }
}
