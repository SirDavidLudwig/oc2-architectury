package li.cil.oc2.common.blockentity;

import li.cil.oc2.common.component.IComponent;
import li.cil.oc2.common.energy.ICommonEnergyStorage;
import li.cil.oc2.common.energy.EnergyStorageManager;
import li.cil.oc2.common.energy.InfiniteEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public final class CreativeEnergyBlockEntity extends ModBlockEntity implements TickableBlockEntity {
    private final Direction[] SIDES = Direction.values();

    private InfiniteEnergyStorage energy = new InfiniteEnergyStorage();

    ///////////////////////////////////////////////////////////////////

    public CreativeEnergyBlockEntity(final BlockPos pos, final BlockState state) {
        super(BlockEntities.CREATIVE_ENERGY.get(), pos, state);
    }

    ///////////////////////////////////////////////////////////////////

    @Override
    public void serverTick() {
        assert level != null;

        for (final Direction side : SIDES) {
            final BlockPos neighborPos = getBlockPos().relative(side);
            final ChunkPos neighborChunkPos = new ChunkPos(neighborPos);
            if (level.hasChunk(neighborChunkPos.x, neighborChunkPos.z)) {
                final BlockEntity blockEntity = level.getBlockEntity(neighborPos);
                if (blockEntity != null) {
                    ICommonEnergyStorage energy = EnergyStorageManager.get(blockEntity, side.getOpposite());
                    if (energy != null) {
                        energy.insert(Integer.MAX_VALUE);
                    }
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////////////

    @Override
    public void collectComponents(Consumer<IComponent> collector, @Nullable Direction direction) {
        collector.accept(energy);
    }
}
