package li.cil.oc2.common.energy;

public interface ICommonEnergyStorage {
    public int insert(int maxAmount);
    public int extract(int maxAmount);

    public int getAmount();
    public int getCapacity();

    public boolean canExtract();
    public boolean canInsert();
}
