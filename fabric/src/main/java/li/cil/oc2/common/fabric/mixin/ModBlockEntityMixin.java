package li.cil.oc2.common.fabric.mixin;

import li.cil.oc2.common.blockentity.ModBlockEntity;
import li.cil.oc2.common.blockapi.IBlockApiProvider;
import li.cil.oc2.common.component.IComponentProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.HashMap;

@Mixin(ModBlockEntity.class)
public abstract class ModBlockEntityMixin extends BlockEntity implements IComponentProvider, IBlockApiProvider {

    private final HashMap<BlockApiCacheKey, Object> blockApiCache = new HashMap<>();

    public ModBlockEntityMixin(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Shadow
    public abstract boolean isValid();

    @Nullable
    @Override
    public Object getBlockApi(ResourceLocation identifier, @Nullable Direction side) {
        if (!isValid()) {
            return null;
        }

        final BlockApiCacheKey key = new BlockApiCacheKey(identifier, side);
        if (blockApiCache.containsKey(key)) {
            return blockApiCache.get(key);
        }

        final Object instance = getWrappedComponent(identifier, side);
        blockApiCache.put(key, instance);
        return instance;
    }

    ///////////////////////////////////////////////////////////////////

    private record BlockApiCacheKey(ResourceLocation api, @Nullable Direction direction) { }
}
