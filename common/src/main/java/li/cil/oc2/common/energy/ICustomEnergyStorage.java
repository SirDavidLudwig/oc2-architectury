package li.cil.oc2.common.energy;

import li.cil.oc2.common.component.IComponent;

public interface ICustomEnergyStorage extends ICommonEnergyStorage, IComponent {
    public int getMaxInsert();
    public int getMaxExtract();

    @Override
    public default Object getKey() {
        return EnergyStorageManager.getKey();
    }

    @Override
    public default Object wrap() {
        return EnergyStorageManager.wrap(this);
    }
}
