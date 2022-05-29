package li.cil.oc2.common.blockapi;

import net.fabricmc.fabric.api.lookup.v1.block.BlockApiLookup;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import team.reborn.energy.api.EnergyStorage;

import java.util.ArrayList;

public class BlockApiRegistry {

    private static ArrayList<BlockApiLookup<?, Direction>> BLOCK_APIS = new ArrayList<>();

    ///////////////////////////////////////////////////////////////////

    public static BlockApiLookup<EnergyStorage, Direction> ENERGY_STORAGE = blockApi(EnergyStorage.SIDED);

    ///////////////////////////////////////////////////////////////////

    private static <A> BlockApiLookup<A, Direction> blockApi(BlockApiLookup<A, Direction> api) {
        BLOCK_APIS.add(api);
        return api;
    }

    ///////////////////////////////////////////////////////////////////

    public static void register(BlockEntityType<? extends BlockEntity> blockEntityType) {
        for (BlockApiLookup<?, Direction> api : BLOCK_APIS) {
            registerForApi(api, blockEntityType);
        }
    }

    private static <A> void registerForApi(BlockApiLookup<A, Direction> api, BlockEntityType<? extends BlockEntity> blockEntityType) {
        api.registerForBlockEntity((blockEntity, side) -> {
            IBlockApiProvider provider = (IBlockApiProvider) blockEntity;
            return (A) provider.getBlockApi(api.getId(), side);
        }, blockEntityType);
    }
}
