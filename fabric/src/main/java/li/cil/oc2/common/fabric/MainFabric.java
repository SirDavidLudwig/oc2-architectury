package li.cil.oc2.common.fabric;

import li.cil.oc2.common.Main;
import net.fabricmc.api.ModInitializer;

public class MainFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Main.init();
    }
}
