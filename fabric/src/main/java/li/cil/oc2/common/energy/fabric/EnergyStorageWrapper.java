package li.cil.oc2.common.energy.fabric;

import li.cil.oc2.common.energy.ICommonEnergyStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import team.reborn.energy.api.EnergyStorage;

public class EnergyStorageWrapper implements ICommonEnergyStorage {

    public EnergyStorage energy;

    public EnergyStorageWrapper(EnergyStorage energy) {
        this.energy = energy;
    }

    @Override
    public int insert(int maxAmount) {
        try (Transaction context = Transaction.openOuter()) {
            int toInsert = (int) energy.insert(maxAmount, context);
            context.commit();
            return toInsert;
        }
    }

    @Override
    public int extract(int maxAmount) {
        try (Transaction context = Transaction.openOuter()) {
            int toExtract = (int) energy.extract(maxAmount, context);
            context.commit();
            return toExtract;
        }
    }

    @Override
    public int getAmount() {
        return (int) Math.min(energy.getAmount(), Integer.MAX_VALUE);
    }

    @Override
    public int getCapacity() {
        return (int) Math.min(energy.getCapacity(), Integer.MAX_VALUE);
    }

    @Override
    public boolean canExtract() {
        return energy.supportsExtraction();
    }

    @Override
    public boolean canInsert() {
        return energy.supportsInsertion();
    }
}
