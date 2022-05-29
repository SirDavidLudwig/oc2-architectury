package li.cil.oc2.common.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import li.cil.oc2.common.util.RegistryUtils;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;

public final class Blocks {
    public static final DeferredRegister<Block> BLOCKS = RegistryUtils.getInitializerFor(Registry.BLOCK_REGISTRY);

    ///////////////////////////////////////////////////////////////////

    public static final RegistrySupplier<ComputerBlock> COMPUTER = BLOCKS.register("computer", ComputerBlock::new);
    public static final RegistrySupplier<CreativeEnergyBlock> CREATIVE_ENERGY = BLOCKS.register("creative_energy", CreativeEnergyBlock::new);

    ///////////////////////////////////////////////////////////////////

    public static void initialize() {
    }
}