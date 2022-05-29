package li.cil.oc2.api.util;

import dev.architectury.registry.registries.RegistrySupplier;

public class RegistryEntry<R> {
    private RegistrySupplier<R> supplier;

    public RegistrySupplier<R> getRegistrySupplier() {
        return this.supplier;
    }

    public void setRegistrySupplier(RegistrySupplier<R> supplier) {
        this.supplier = supplier;
    }
}
