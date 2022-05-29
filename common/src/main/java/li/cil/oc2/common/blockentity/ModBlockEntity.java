package li.cil.oc2.common.blockentity;

import li.cil.oc2.common.component.IComponent;
import li.cil.oc2.common.component.IComponentProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ModBlockEntity extends BlockEntity implements IComponentProvider {
//    private final Runnable onWorldUnloaded = this::onWorldUnloaded;
//    private final HashMap<CapabilityCacheKey, LazyOptional<?>> capabilityCache = new HashMap<>();
    private boolean needsWorldUnloadEvent;
    private boolean isUnloaded;

    ///////////////////////////////////////////////////////////////////

    protected ModBlockEntity(final BlockEntityType<?> blockEntityType, final BlockPos pos, final BlockState state) {
        super(blockEntityType, pos, state);
    }

    ///////////////////////////////////////////////////////////////////

//    @Override
//    public void onLoad() {
//        super.onLoad();
//
//        if (level == null) {
//            return;
//        }
//
//        if (level.isClientSide()) {
//            loadClient();
//        } else {
//            loadServer();
//
//            if (needsWorldUnloadEvent) {
//                ServerScheduler.scheduleOnUnload(level, onWorldUnloaded);
//            }
//        }
//    }

//    @Override
//    public void onChunkUnloaded() {
//        super.onChunkUnloaded(); // -> invalidateCaps()
//        onUnload(false);
//        isUnloaded = true;
//    }
//
//    public void onWorldUnloaded() {
//        invalidateCaps();
//        onUnload(false);
//    }
//
    @Override
    public void setRemoved() {
        super.setRemoved(); // -> invalidateCaps()
        if (!isUnloaded) {
            onUnload(true);
        }
    }

    public boolean isValid() {
        return !isRemoved() && !isUnloaded;
    }

    ///////////////////////////////////////////////////////////////////

    @Nullable
    @Override
    public Object getWrappedComponent(Object identifier, Direction side) {
        System.out.println("Getting wrapped component...");
        final ArrayList<Object> list = new ArrayList<>();
        collectComponents((IComponent offeredComponent) -> {
            if (offeredComponent.getKey().equals(identifier)) {
                list.add(offeredComponent.wrap());
            }
        }, side);
        System.out.println("\tIs it null? " + list.isEmpty());
        if (list.isEmpty()) {
            return null;
        }
        System.out.println("Found component: " + list.get(0));
        return list.get(0);
    }

//    protected <T> void invalidateCapability(final Capability<T> capability, @Nullable final Direction direction) {
//        final CapabilityCacheKey key = new CapabilityCacheKey(capability, direction);
//        final LazyOptional<?> value = capabilityCache.get(key);
//        if (value != null) {
//            value.invalidate();
//        }
//    }
//
//    @Override
//    public void invalidateCaps() {
//        super.invalidateCaps();
//
//        // Copy values because invalidate callback will modify map (removes invalidated entry).
//        for (final LazyOptional<?> capability : new ArrayList<>(capabilityCache.values())) {
//            capability.invalidate();
//        }
//    }
    protected void onUnload(final boolean isRemove) {
        if (level != null && !level.isClientSide()) {
            unloadServer(isRemove);
//            ServerScheduler.cancelOnUnload(level, onWorldUnloaded);
        }
    }

    protected void setNeedsLevelUnloadEvent() {
        needsWorldUnloadEvent = true;
    }

    protected void loadClient() {
    }

    protected void loadServer() {
    }

    protected void unloadServer(final boolean isRemove) {
    }

    ///////////////////////////////////////////////////////////////////

    public static void installComponents() {

    }
}

