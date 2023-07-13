package games.startrace.registry;

import games.startrace.MainMod;
import games.startrace.registry.obsidian.Brick;
import games.startrace.registry.obsidian.Square;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class modBlock {

    public static Block obsidian_square = Square.ObsidianSquareINSTANCE;
    public static Block obsidian_brick = Brick.ObsidianBrickINSTANCE;

    /**
     * 创建一个方块
     * X同时添加它的物品
     * 并将其加入到已存在的物品组(如有)
     *
     * @param name
     * @param block
     * @param groups
     * @return
     */
    public static Block createBlock(String name, Block block, RegistryKey<ItemGroup>... groups) {
        modItem.createItem(name, new BlockItem(block, new FabricItemSettings()), groups);
        Registry.register(Registries.BLOCK, new Identifier(MainMod.MOD_ID, name), block);
        return block;
    }

    public static void init() {

    }
}
