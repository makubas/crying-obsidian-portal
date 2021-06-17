package com.github.makubas.cryingobsidianportal.mixin;


import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.PortalForcer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PortalForcer.class)
public interface PortalForcerInvoker {
    @Invoker("isValidPortalPos")
    public boolean isValidPortalPos(BlockPos pos, BlockPos.Mutable temp, Direction portalDirection, int distanceOrthogonalToPortal);

}
