package li.cil.oc2.common.blockentity;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import li.cil.oc2.common.block.Blocks;
import li.cil.oc2.common.util.RegistryUtils;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BlockEntities {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = RegistryUtils.getInitializerFor(Registry.BLOCK_ENTITY_TYPE_REGISTRY);

    ///////////////////////////////////////////////////////////////////

    public static final RegistrySupplier<BlockEntityType<ComputerBlockEntity>> COMPUTER = register(Blocks.COMPUTER, ComputerBlockEntity::new);
    public static final RegistrySupplier<BlockEntityType<CreativeEnergyBlockEntity>> CREATIVE_ENERGY = register(Blocks.CREATIVE_ENERGY, CreativeEnergyBlockEntity::new);

    ///////////////////////////////////////////////////////////////////

    public static void initialize() {
    }

    ///////////////////////////////////////////////////////////////////

    @SuppressWarnings("ConstantConditions") // .build(null) is fine
    private static <B extends Block, T extends BlockEntity> RegistrySupplier<BlockEntityType<T>> register(final RegistrySupplier<B> block, final BlockEntityType.BlockEntitySupplier<T> factory) {
        return BLOCK_ENTITIES.register(block.getId().getPath(), () -> BlockEntityType.Builder.of(factory, block.get()).build(null));
    }
}
