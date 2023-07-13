package games.startrace.registry;

import games.startrace.MainMod;
import games.startrace.registry.obsidian.Pickaxe;
import games.startrace.registry.tools._Pickaxe;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class modItem {

    public static final Item obsidian_pickaxe = createItem("obsidian_pickaxe",
            new _Pickaxe(
                    Pickaxe.ObsidianPickaxeINSTANCE, 79, 0, new Item.Settings())
            , modItemGroup.OBSIDIAN_GROUP);



    //物品实例
//    public static final Item OBSIDIAN_PICKAXE = createItem("obsidian_pickaxe", new Item(new FabricItemSettings()), ItemGroups.TOOLS, modItemGroup.TOOLS_GROUP);

    /**
     * 创建一个物品
     * 并将其加入到已存在的物品组(如有)
     *
     * @param name   物品的名字
     * @param item   物品实例
     * @param groups 物品组
     * @return 物品实例
     */
    public static Item createItem(String name, Item item, RegistryKey<ItemGroup>... groups) {
        var CUSTOM_ITEM = Registry.register(Registries.ITEM, new Identifier(MainMod.MOD_ID, name), item);
        if (groups.length > 0) {
            addToItemGroup(CUSTOM_ITEM, groups);
        }
        return CUSTOM_ITEM;
    }

    /**
     * 将物品加入到物品组
     *
     * @param item   物品实例
     * @param groups 物品组
     */
    public static void addToItemGroup(Item item, RegistryKey<ItemGroup>... groups) {
        for (RegistryKey<ItemGroup> group : groups) {
            ItemGroupEvents.modifyEntriesEvent(group).register(e -> e.add(item));
        }
    }

    public static void init() {

    }
}
