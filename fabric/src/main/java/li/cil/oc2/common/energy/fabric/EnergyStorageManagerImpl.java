package li.cil.oc2.common.energy.fabric;

import li.cil.oc2.common.blockapi.BlockApis;
import li.cil.oc2.common.energy.ICommonEnergyStorage;
import li.cil.oc2.common.energy.ICustomEnergyStorage;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import team.reborn.energy.api.EnergyStorage;

import javax.annotation.Nullable;
import java.util.function.BiFunction;

public class EnergyStorageManagerImpl {
    @Nullable
    public static ICommonEnergyStorage get(BlockEntity blockEntity, Direction side) {
        EnergyStorage storage = EnergyStorage.SIDED.find(blockEntity.getLevel(), blockEntity.getBlockPos(), side);
        if (storage == null) {
            return null;
        }
        return new EnergyStorageWrapper(storage);
    }

    public static <T extends BlockEntity> void register(BlockEntityType<T> type, BiFunction<T, Direction, ICustomEnergyStorage> factory) {
        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, side) -> ((EnergyStorageWrapper) factory.apply(blockEntity, side)).energy, type);
    }

    ///////////////////////////////////////////////////////////////////

    public static Object getKey() {
        return BlockApis.energyStorage().getId();
    }

    public static Object wrap(ICustomEnergyStorage energy) {
        return new FabricEnergyStorageWrapper(energy);
    }
}
