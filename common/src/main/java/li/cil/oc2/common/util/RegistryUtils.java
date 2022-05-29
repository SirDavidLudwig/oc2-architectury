package li.cil.oc2.common.util;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import li.cil.oc2.api.API;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class RegistryUtils {
    private enum Phase {
        PRE_INIT,
        INIT,
        POST_INIT,
    }

    private static final List<DeferredRegister<?>> ENTRIES = new ArrayList<>();
    private static Phase phase = Phase.PRE_INIT;

    public static <T> DeferredRegister<T> getInitializerFor(final ResourceKey<Registry<T>> key) {
        if (phase != Phase.INIT) throw new IllegalStateException();

        final DeferredRegister<T> entry = DeferredRegister.create(API.MOD_ID, key);
        ENTRIES.add(entry);
        return entry;
    }

    public static void begin() {
        if (phase != Phase.PRE_INIT) throw new IllegalStateException();
        phase = Phase.INIT;
    }

    public static void finish() {
        if (phase != Phase.INIT) throw new IllegalStateException();
        phase = Phase.POST_INIT;

        for (final DeferredRegister<?> entry : ENTRIES) {
            entry.register();
        }

        ENTRIES.clear();
    }

    public static <T> String key(final RegistrySupplier<T> registryEntry) {
        return Objects.requireNonNull(registryEntry.getRegistryId()).toString();
    }

    public static <T> Optional<String> optionalKey(@Nullable final RegistrySupplier<T> registryEntry) {
        if (registryEntry == null) {
            return Optional.empty();
        }

        final ResourceLocation providerName = registryEntry.getRegistryId();
        if (providerName == null) {
            return Optional.empty();
        }

        return Optional.of(providerName.toString());
    }

    private RegistryUtils() {
    }
}
