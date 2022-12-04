package io.github.ivymc.compassplus;

import io.github.ivymc.compassplus.player.Helper;
import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {
    @Override
    public void onInitialize() {
        Helper.register();
    }
}
