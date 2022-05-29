package li.cil.oc2.common.energy;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import javax.annotation.Nullable;
import java.util.function.BiFunction;

public final class EnergyStorageManager {

    @Nullable
    @ExpectPlatform
    public static ICommonEnergyStorage get(BlockEntity blockEntity, Direction side) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends BlockEntity> void register(BlockEntityType<T> type, BiFunction<T, Direction, ICustomEnergyStorage> consumer) {
        throw new AssertionError();
    }

    ///////////////////////////////////////////////////////////////////

    @ExpectPlatform
    public static Object getKey() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Object wrap(ICustomEnergyStorage energy) {
        throw new AssertionError();
    }

    ///////////////////////////////////////////////////////////////////

    private EnergyStorageManager() { }

    public static void initialize() {

    }
}
