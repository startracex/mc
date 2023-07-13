package games.startrace.registry.obsidian;

import games.startrace.registry.modBlock;
import games.startrace.registry.modItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;

public class Brick {
    public static Block ObsidianBrickINSTANCE = modBlock.createBlock("obsidian_brick",
            new Block(FabricBlockSettings.create().hardness(200f).requiresTool()),
            modItemGroup.OBSIDIAN_GROUP
    );
}
