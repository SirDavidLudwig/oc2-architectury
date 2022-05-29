package li.cil.oc2.common.forge;

import dev.architectury.platform.forge.EventBuses;
import li.cil.oc2.api.API;
import li.cil.oc2.common.Config;
import li.cil.oc2.common.Main;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(API.MOD_ID)
public class MainForge extends Main {

    public MainForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(API.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        init();
    }

    @Override
    protected void initConfig() {
        ConfigManagerForge.add(Config::new);
        ConfigManagerForge.initialize();
    }
}
