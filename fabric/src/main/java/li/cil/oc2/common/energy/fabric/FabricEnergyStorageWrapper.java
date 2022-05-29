package li.cil.oc2.common.energy.fabric;

import li.cil.oc2.common.energy.ICustomEnergyStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import team.reborn.energy.api.EnergyStorage;

public class FabricEnergyStorageWrapper implements EnergyStorage {

    public ICustomEnergyStorage storage;


    public FabricEnergyStorageWrapper(ICustomEnergyStorage storage) {
        this.storage = storage;
    }

    @Override
    public boolean supportsInsertion() {
        return storage.canInsert();
    }

    @Override
    public boolean supportsExtraction() {
        return storage.canExtract();
    }

    @Override
    public long insert(long maxAmount, TransactionContext transaction) {
        return storage.insert((int) Math.min(maxAmount, Integer.MAX_VALUE));
    }

    @Override
    public long extract(long maxAmount, TransactionContext transaction) {
        return storage.extract((int) Math.min(maxAmount, Integer.MAX_VALUE));
    }

    @Override
    public long getAmount() {
        return storage.getAmount();
    }

    @Override
    public long getCapacity() {
        return storage.getCapacity();
    }
}
