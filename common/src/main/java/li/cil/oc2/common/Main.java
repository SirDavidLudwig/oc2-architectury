package li.cil.oc2.common;

import com.google.common.base.Suppliers;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registries;
import dev.architectury.registry.registries.RegistrySupplier;
import li.cil.ceres.Ceres;
import li.cil.oc2.common.block.Blocks;
import li.cil.oc2.common.blockentity.BlockEntities;
import li.cil.oc2.common.blockentity.CreativeEnergyBlockEntity;
import li.cil.oc2.common.bus.device.DeviceTypes;
import li.cil.oc2.common.item.Items;
import li.cil.oc2.common.tags.BlockTags;
import li.cil.oc2.common.tags.ItemTags;
import li.cil.oc2.common.util.RegistryUtils;
import li.cil.sedna.Sedna;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public abstract class Main {

    public void init() {
        Ceres.initialize();
        Sedna.initialize();

        RegistryUtils.begin();

        ItemTags.initialize();
        BlockTags.initialize();
        Blocks.initialize();
        Items.initialize();
        BlockEntities.initialize();

//        DeviceTypes.initialize();

        RegistryUtils.finish();

        CreativeEnergyBlockEntity.installComponents();
    }

    protected abstract void initConfig();
}
