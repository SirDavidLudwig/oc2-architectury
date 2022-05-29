package li.cil.oc2.common.item;

import dev.architectury.registry.CreativeTabRegistry;
import li.cil.oc2.api.API;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ItemGroup {
    public static final CreativeModeTab COMMON = CreativeTabRegistry.create(
            new ResourceLocation(API.MOD_ID, "common"), () -> new ItemStack(Items.COMPUTER.get()));
}
