package li.cil.oc2.common.blockapi;

import li.cil.oc2.common.blockentity.BlockEntities;
import net.fabricmc.fabric.api.lookup.v1.block.BlockApiLookup;
import net.minecraft.core.Direction;
import team.reborn.energy.api.EnergyStorage;

public final class BlockApis {
    public static BlockApiLookup<EnergyStorage, Direction> energyStorage() {
        return BlockApiRegistry.ENERGY_STORAGE;
    }

    public static void initialize() {
        BlockApiRegistry.register(BlockEntities.COMPUTER.get());
        BlockApiRegistry.register(BlockEntities.CREATIVE_ENERGY.get());
    }
}
