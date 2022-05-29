package li.cil.oc2.common.blockapi;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public interface IBlockApiProvider {
    public Object getBlockApi(ResourceLocation identifier, @Nullable Direction side);
}
