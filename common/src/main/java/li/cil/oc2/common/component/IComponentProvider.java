package li.cil.oc2.common.component;

import net.minecraft.core.Direction;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public interface IComponentProvider {
    @Nullable
    public Object getWrappedComponent(Object identifier, Direction side);

    public default void collectComponents(final Consumer<IComponent> collector, @Nullable final Direction direction) {
    }
}
