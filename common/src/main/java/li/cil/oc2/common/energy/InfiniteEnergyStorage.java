package li.cil.oc2.common.energy;

import li.cil.oc2.common.component.IComponent;

public class InfiniteEnergyStorage implements ICustomEnergyStorage {

    @Override
    public int insert(int maxAmount) {
        return 0;
    }

    @Override
    public int extract(int maxAmount) {
        return maxAmount;
    }

    @Override
    public int getMaxInsert() {
        return 0;
    }

    @Override
    public int getMaxExtract() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getAmount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getCapacity() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canInsert() {
        return false;
    }
}
