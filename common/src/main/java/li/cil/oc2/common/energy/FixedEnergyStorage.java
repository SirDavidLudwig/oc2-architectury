package li.cil.oc2.common.energy;

import li.cil.oc2.api.util.INBTSerializable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

public class FixedEnergyStorage implements ICustomEnergyStorage, INBTSerializable {
    public static final String STORED_TAG_NAME = "stored";
    public static final String CAPACITY_TAG_NAME = "capacity";

    public int amount = 0;
    public int capacity;
    public int maxInsert;
    public int maxExtract;

    public FixedEnergyStorage(int capacity) {
        this(capacity, capacity, capacity);
    }

    public FixedEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        this.capacity = capacity;
        this.maxInsert = maxReceive;
        this.maxExtract = maxExtract;
    }

    @Override
    public int insert(int maxAmount) {
        int toInsert = Math.min(capacity - amount, Math.min(maxInsert, Math.max(0, maxAmount)));
        amount += toInsert;
        return toInsert;
    }

    @Override
    public int extract(int maxAmount) {
        int toExtract = Math.min(amount, Math.min(maxExtract, Math.max(0, maxAmount)));
        amount -= toExtract;
        return toExtract;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean canExtract() {
        return maxExtract > 0;
    }

    @Override
    public boolean canInsert() {
        return maxInsert > 0;
    }

    @Override
    public int getMaxInsert() {
        return maxInsert;
    }

    @Override
    public int getMaxExtract() {
        return maxExtract;
    }

    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();
        tag.putInt(STORED_TAG_NAME, amount);
        tag.putInt(CAPACITY_TAG_NAME, capacity);
        return tag;
    }

    @Override
    public void deserializeNBT(final Tag tag) {
        if (tag instanceof final CompoundTag compoundTag) {
            amount = compoundTag.getInt(STORED_TAG_NAME);
        }
    }
}
