package li.cil.oc2.common.fabric;

import li.cil.oc2.common.Main;
import li.cil.oc2.common.blockapi.BlockApis;
import li.cil.oc2.common.blockentity.CreativeEnergyBlockEntity;
import li.cil.oc2.common.blockapi.IBlockApiProvider;
import net.fabricmc.api.ModInitializer;

public class MainFabric extends Main implements ModInitializer {

    @Override
    protected void initConfig() {

    }

    @Override
    public void onInitialize() {
        init();
        BlockApis.initialize();
    }
}
