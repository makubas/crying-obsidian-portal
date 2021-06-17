package com.github.makubas.cryingobsidianportal.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Heightmap;
import net.minecraft.world.PortalForcer;
import net.minecraft.world.PortalUtil;
import net.minecraft.world.border.WorldBorder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;
import java.util.Optional;


@Mixin(PortalForcer.class)
public class PortalForcerMixin {

    @Mutable
    @Shadow @Final private ServerWorld world;

    public Optional<PortalUtil.Rectangle> createPortal(BlockPos blockPos, Direction.Axis axis) {
        this.world = ((PortalForcerAccessor) this).getWorld();
        Direction direction = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        double d = -1.0D;
        BlockPos blockPos2 = null;
        double e = -1.0D;
        BlockPos blockPos3 = null;
        WorldBorder worldBorder = this.world.getWorldBorder();
        int i = Math.min(this.world.getTopY(), this.world.getBottomY() + this.world.getLogicalHeight()) - 1;
        BlockPos.Mutable mutable = blockPos.mutableCopy();
        Iterator<BlockPos.Mutable> var13 = BlockPos.iterateInSquare(blockPos, 16, Direction.EAST, Direction.SOUTH).iterator();

        while(true) {
            BlockPos.Mutable mutable2;
            int w;
            int l;
            int s;
            do {
                do {
                    if (!var13.hasNext()) {
                        if (d == -1.0D && e != -1.0D) {
                            blockPos2 = blockPos3;
                            d = e;
                        }

                        int t;
                        int v;
                        if (d == -1.0D) {
                            t = Math.max(this.world.getBottomY() - -1, 70);
                            v = i - 9;
                            if (v < t) {
                                return Optional.empty();
                            }

                            blockPos2 = (new BlockPos(blockPos.getX(), MathHelper.clamp(blockPos.getY(), t, v), blockPos.getZ())).toImmutable();
                            Direction direction2 = direction.rotateYClockwise();
                            if (!worldBorder.contains(blockPos2)) {
                                return Optional.empty();
                            }

                            for(int q = -1; q < 2; ++q) {
                                for(l = 0; l < 2; ++l) {
                                    for(s = -1; s < 3; ++s) {
                                        BlockState blockState = s < 0 ? Blocks.CRYING_OBSIDIAN.getDefaultState() : Blocks.AIR.getDefaultState();
                                        mutable.set(blockPos2, l * direction.getOffsetX() + q * direction2.getOffsetX(), s, l * direction.getOffsetZ() + q * direction2.getOffsetZ());
                                        this.world.setBlockState(mutable, blockState);
                                    }
                                }
                            }
                        }

                        for(t = -1; t < 3; ++t) {
                            for(v = -1; v < 4; ++v) {
                                if (t == -1 || t == 2 || v == -1 || v == 3) {
                                    mutable.set(blockPos2, t * direction.getOffsetX(), v, t * direction.getOffsetZ());
                                    this.world.setBlockState(mutable, Blocks.CRYING_OBSIDIAN.getDefaultState(), 3);
                                }
                            }
                        }

                        BlockState blockState2 = Blocks.NETHER_PORTAL.getDefaultState().with(NetherPortalBlock.AXIS, axis);

                        for(v = 0; v < 2; ++v) {
                            for(w = 0; w < 3; ++w) {
                                mutable.set(blockPos2, v * direction.getOffsetX(), w, v * direction.getOffsetZ());
                                this.world.setBlockState(mutable, blockState2, 18);
                            }
                        }

                        return Optional.of(new PortalUtil.Rectangle(blockPos2.toImmutable(), 2, 3));
                    }

                    mutable2 = var13.next();
                    w = Math.min(i, this.world.getTopY(Heightmap.Type.MOTION_BLOCKING, mutable2.getX(), mutable2.getZ()));
                } while(!worldBorder.contains(mutable2));
            } while(!worldBorder.contains(mutable2.move(direction, 1)));

            mutable2.move(direction.getOpposite(), 1);

            for(l = w; l >= this.world.getBottomY(); --l) {
                mutable2.setY(l);
                if (this.world.isAir(mutable2)) {
                    for(s = l; l > this.world.getBottomY() && this.world.isAir(mutable2.move(Direction.DOWN)); --l) {
                    }

                    if (l + 4 <= i) {
                        int n = s - l;
                        if (n <= 0 || n >= 3) {
                            mutable2.setY(l);
                            if (((PortalForcerInvoker) this).isValidPortalPos(mutable2, mutable, direction, 0)) {
                                double f = blockPos.getSquaredDistance(mutable2);
                                if (((PortalForcerInvoker) this).isValidPortalPos(mutable2, mutable, direction, -1) && ((PortalForcerInvoker) this).isValidPortalPos(mutable2, mutable, direction, 1)     && (d == -1.0D || d > f)) {
                                    d = f;
                                    blockPos2 = mutable2.toImmutable();
                                }

                                if (d == -1.0D && (e == -1.0D || e > f)) {
                                    e = f;
                                    blockPos3 = mutable2.toImmutable();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
