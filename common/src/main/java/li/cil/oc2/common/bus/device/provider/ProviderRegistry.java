package li.cil.oc2.common.bus.device.provider;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import li.cil.oc2.api.bus.device.provider.BlockDeviceProvider;
import li.cil.oc2.api.bus.device.provider.ItemDeviceProvider;
import li.cil.oc2.api.util.Registries;
import li.cil.oc2.common.util.RegistryUtils;

import java.util.function.Supplier;

public final class ProviderRegistry {
    private static final DeferredRegister<BlockDeviceProvider> BLOCK_DEVICE_PROVIDERS = RegistryUtils.getInitializerFor(Registries.BLOCK_DEVICE_PROVIDER);
//    public static final Supplier<IForgeRegistry<BlockDeviceProvider>> BLOCK_DEVICE_PROVIDER_REGISTRY = BLOCK_DEVICE_PROVIDERS.makeRegistry(BlockDeviceProvider.class, RegistryBuilder::new);
    public static final Registrar<BlockDeviceProvider> BLOCK_DEVICE_PROVIDER_REGISTRY = BLOCK_DEVICE_PROVIDERS.getRegistrar();

    ///////////////////////////////////////////////////////////////////

    private static final DeferredRegister<ItemDeviceProvider> ITEM_DEVICE_PROVIDERS = RegistryUtils.getInitializerFor(Registries.ITEM_DEVICE_PROVIDER);
//    public static final Supplier<IForgeRegistry<ItemDeviceProvider>> ITEM_DEVICE_PROVIDER_REGISTRY = ITEM_DEVICE_PROVIDERS.makeRegistry(ItemDeviceProvider.class, RegistryBuilder::new);
    public static final Registrar<ItemDeviceProvider> ITEM_DEVICE_PROVIDER_REGISTRY = ITEM_DEVICE_PROVIDERS.getRegistrar();

    ///////////////////////////////////////////////////////////////////

    public static void initialize() {
        Providers.registerBlockDeviceProviders(BLOCK_DEVICE_PROVIDERS::register);
        Providers.registerItemDeviceProviders(ITEM_DEVICE_PROVIDERS::register);
    }
}
