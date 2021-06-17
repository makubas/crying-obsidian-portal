package com.github.makubas.cryingobsidianportal.mixin;


import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.world.dimension.AreaHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AreaHelper.class)
public interface AreaHelperInvoker {
    //@Accessor("IS_VALID_FRAME_BLOCK")
    //public static AbstractBlock.ContextPredicate invokeIS_VALID_FRAME_BLOCK = (state, world, pos) -> {
    //    return (state.isOf(Blocks.OBSIDIAN) || state.isOf(Blocks.CRYING_OBSIDIAN));
    //};
}
