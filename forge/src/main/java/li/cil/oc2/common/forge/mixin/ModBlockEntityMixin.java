package li.cil.oc2.common.forge.mixin;

import li.cil.oc2.common.component.IComponentProvider;
import li.cil.oc2.common.blockentity.ModBlockEntity;
import li.cil.oc2.common.utils.forge.LazyOptionalUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.HashMap;

@Mixin(ModBlockEntity.class)
public abstract class ModBlockEntityMixin extends BlockEntity implements IComponentProvider {

    private final HashMap<CapabilityCacheKey, LazyOptional<?>> capabilityCache = new HashMap<>();

    public ModBlockEntityMixin(BlockEntityType<?> arg, BlockPos arg2, BlockState arg3) {
        super(arg, arg2, arg3);
    }

    @Shadow
    public abstract boolean isValid();

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
        if (!isValid()) {
            return LazyOptional.empty();
        }

        final CapabilityCacheKey key = new CapabilityCacheKey(capability, side);
        LazyOptional<?> value;
        if (capabilityCache.containsKey(key)) {
            value = capabilityCache.get(key);
        } else {
            value = LazyOptional.empty();
        }

        if (!value.isPresent()) {
            final T instance = (T) getWrappedComponent(capability, side);

            if (instance != null) {
                value = LazyOptional.of(() -> instance);
            } else {
                value = super.getCapability(capability, side);
            }

            if (value.isPresent()) {
                capabilityCache.put(key, value);
                LazyOptionalUtils.addWeakListener(value, capabilityCache, (map, optional) -> map.remove(key, optional));
            }
        }

        return value.cast();
    }

    ///////////////////////////////////////////////////////////////////

    private record CapabilityCacheKey(Capability<?> capability, @Nullable Direction direction) { }
}
