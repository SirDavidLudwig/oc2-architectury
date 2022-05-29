package li.cil.oc2.common.energy.forge;

import li.cil.oc2.common.energy.ICommonEnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

public class EnergyStorageWrapper implements ICommonEnergyStorage {

    public IEnergyStorage energy;

    public EnergyStorageWrapper(IEnergyStorage energy) {
        this.energy = energy;
    }

    @Override
    public int insert(int maxAmount) {
        return energy.receiveEnergy(maxAmount, false);
    }

    @Override
    public int extract(int maxAmount) {
        return energy.extractEnergy(maxAmount, false);
    }

    @Override
    public int getAmount() {
        return energy.getEnergyStored();
    }

    @Override
    public int getCapacity() {
        return energy.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return energy.canExtract();
    }

    @Override
    public boolean canInsert() {
        return energy.canReceive();
    }

//    @Override
//    public Capability<IEnergyStorage> getKey() {
//        return Capabilities.energyStorage();
//    }
//
//    @Override
//    public IEnergyStorage getCapability() {
//        return energy;
//    }
}
