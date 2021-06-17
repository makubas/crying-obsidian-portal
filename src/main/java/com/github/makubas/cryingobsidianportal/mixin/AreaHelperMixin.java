package com.github.makubas.cryingobsidianportal.mixin;


import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.world.dimension.AreaHelper;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AreaHelper.class)
public class AreaHelperMixin {
    private static final AbstractBlock.ContextPredicate IS_VALID_FRAME_BLOCK = (state, world, pos) -> {
        return (state.isOf(Blocks.OBSIDIAN) || state.isOf(Blocks.CRYING_OBSIDIAN));
    };
}
