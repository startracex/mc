package games.startrace.registry;

import games.startrace.MainMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class modItemGroup {

    public static final RegistryKey<ItemGroup> TOOLS_GROUP = createItemGroup(Items.CRYING_OBSIDIAN, "obsidian_group");

    /**
     * 创建一个物品组
     * 其中的物品不能在创建之前加入此组
     *
     * @param ICON_ITEM 在物品组的图标
     * @param Name 物品组的名字
     * @return 物品组实例
     */
    public static RegistryKey<ItemGroup> createItemGroup(Item ICON_ITEM, String Name) {
        var CUSTOM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MainMod.MOD_ID, Name));
        Registry.register(Registries.ITEM_GROUP, CUSTOM_GROUP, FabricItemGroup.builder()
                .icon(() -> new ItemStack(ICON_ITEM))
                .displayName(Text.translatable("itemgroup." + MainMod.MOD_ID + "." + Name))
                .build());
        return CUSTOM_GROUP;
    }

    public static void init() {

    }
}