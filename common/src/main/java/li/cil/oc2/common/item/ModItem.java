package li.cil.oc2.common.item;

import net.minecraft.world.item.Item;

public class ModItem extends Item {
    public ModItem(final Properties properties) {
        super(properties.tab(ItemGroup.COMMON));
    }

    public ModItem() {
        this(createProperties());
    }

    ///////////////////////////////////////////////////////////////////

//    @OnlyIn(Dist.CLIENT)
//    @Override
//    public void appendHoverText(final ItemStack stack, @Nullable final Level level, final List<Component> tooltip, final TooltipFlag flag) {
//        super.appendHoverText(stack, level, tooltip, flag);
//        TooltipUtils.tryAddDescription(stack, tooltip);
//    }

    ///////////////////////////////////////////////////////////////////

    protected static Properties createProperties() {
        return new Properties();
    }
}
