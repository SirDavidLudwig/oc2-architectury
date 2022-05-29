package li.cil.oc2.common.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import li.cil.oc2.common.block.Blocks;
import li.cil.oc2.common.util.RegistryUtils;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;
import java.util.function.Supplier;

public class Items {

    private static final DeferredRegister<Item> ITEMS = RegistryUtils.getInitializerFor(Registry.ITEM_REGISTRY);

    ///////////////////////////////////////////////////////////////////

    public static final RegistrySupplier<Item> COMPUTER = register(Blocks.COMPUTER);
    public static final RegistrySupplier<Item> CREATIVE_ENERGY = register(Blocks.CREATIVE_ENERGY);

    ///////////////////////////////////////////////////////////////////

    public static void initialize() {
    }

    ///////////////////////////////////////////////////////////////////

    private static RegistrySupplier<Item> register(final String name) {
        return register(name, ModItem::new);
    }

    private static <T extends Item> RegistrySupplier<T> register(final String name, final Supplier<T> factory) {
        return ITEMS.register(name, factory);
    }

    public static <T extends Block> RegistrySupplier<Item> register(final RegistrySupplier<T> block) {
        return register(block, ModBlockItem::new);
    }

    private static <TBlock extends Block, TItem extends Item> RegistrySupplier<TItem> register(final RegistrySupplier<TBlock> block, final Function<TBlock, TItem> factory) {
        return register(block.getId().getPath(), () -> factory.apply(block.get()));
    }
}
