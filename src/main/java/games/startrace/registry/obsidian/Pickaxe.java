package games.startrace.registry.obsidian;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class Pickaxe implements ToolMaterial {

    public static final Pickaxe ObsidianPickaxeINSTANCE = new Pickaxe();

    //    工具在使用此材料时的耐久
    @Override
    public int getDurability() {
        return Integer.MAX_VALUE;
    }

    //    定义了工具破坏方块的速度。木制工具的速度为2.0F，钻石工具的速度为8.0F
    @Override
    public float getMiningSpeedMultiplier() {
        return 80.0F;
    }

    @Override
    public float getAttackDamage() {
        return 4800.0F;
    }

    //    工具的挖掘等级。钻石的挖掘等级为3，黑曜石需要3+的挖掘等级来挖掘。
    @Override
    public int getMiningLevel() {
        return 4;
    }

    //    定义了工具可以如何附魔。金有22的附魔能力，钻石的附魔能力为10。更高的附魔能力意味着更好（更高等级）的附魔
    @Override
    public int getEnchantability() {
        return 22;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}
