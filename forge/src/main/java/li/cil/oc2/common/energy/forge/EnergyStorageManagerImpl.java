package li.cil.oc2.common.energy.forge;

import li.cil.oc2.common.energy.ICommonEnergyStorage;
import li.cil.oc2.common.energy.ICustomEnergyStorage;
import li.cil.oc2.common.capabilities.Capabilities;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;
import java.util.function.BiFunction;

public class EnergyStorageManagerImpl {

    @Nullable
    public static ICommonEnergyStorage get(BlockEntity blockEntity, Direction side) {
        LazyOptional<IEnergyStorage> energy = blockEntity.getCapability(Capabilities.energyStorage(), side);
        if (!energy.isPresent()) {
            return null;
        }
        return new EnergyStorageWrapper(energy.resolve().get());
    }

    public static <T extends BlockEntity> void register(BlockEntityType<T> type, BiFunction<T, Direction, ICustomEnergyStorage> consumer) { }

    ///////////////////////////////////////////////////////////////////

    public static Object getKey() {
        return Capabilities.energyStorage();
    }

    public static Object wrap(ICustomEnergyStorage energy) {
        return new ForgeEnergyStorageWrapper(energy);
    }
}
