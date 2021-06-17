package com.github.makubas.cryingobsidianportal.mixin;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PortalForcer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PortalForcer.class)
public interface PortalForcerAccessor {
    @Accessor("world")
    public abstract ServerWorld getWorld();
}
