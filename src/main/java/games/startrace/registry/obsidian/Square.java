package games.startrace.registry.obsidian;


import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;

import static games.startrace.registry.modBlock.createBlock;
import static games.startrace.registry.modItemGroup.OBSIDIAN_GROUP;

public class Square {
    public static Block ObsidianSquareINSTANCE = createBlock("obsidian_square",
            new Block(FabricBlockSettings.create().hardness(200f).requiresTool()),
            OBSIDIAN_GROUP);
}
