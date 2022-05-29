package li.cil.oc2.common.energy.forge;

import li.cil.oc2.common.energy.ICustomEnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

public class ForgeEnergyStorageWrapper implements IEnergyStorage {
    public ICustomEnergyStorage storage;

    public ForgeEnergyStorageWrapper(ICustomEnergyStorage storage) {
        this.storage = storage;
    }

    @Override
    public int receiveEnergy(int maxAmount, boolean simulate) {
        if (simulate) {
            return Math.min(storage.getCapacity() - storage.getAmount(), Math.min(storage.getMaxInsert(), maxAmount));
        }
        return storage.insert(maxAmount);
    }

    @Override
    public int extractEnergy(int maxAmount, boolean simulate) {
        if (simulate) {
            return Math.min(storage.getAmount(), Math.min(storage.getMaxExtract(), maxAmount));
        }
        return storage.extract(maxAmount);
    }

    @Override
    public int getEnergyStored() {
        return storage.getAmount();
    }

    @Override
    public int getMaxEnergyStored() {
        return storage.getCapacity();
    }

    @Override
    public boolean canExtract() {
        return storage.canExtract();
    }

    @Override
    public boolean canReceive() {
        return storage.canInsert();
    }

//    @Override
//    public Capability<IEnergyStorage> getKey() {
//        return Capabilities.energyStorage();
//    }
}
