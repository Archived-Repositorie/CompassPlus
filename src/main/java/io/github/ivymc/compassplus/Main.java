package io.github.ivymc.compassplus;

import io.github.ivymc.compassplus.config.ModConfigs;
import io.github.ivymc.ivycore.Global;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

import java.io.IOException;

public class Main implements PreLaunchEntrypoint {
    public static Global g = new Global("compassplus");
    public static ModConfigs CONFIG;

    @Override
    public void onPreLaunch() {
        try {
            CONFIG = ModConfigs.readConfig();
        } catch (IOException ignored) {
            CONFIG = new ModConfigs();
            try {
                ModConfigs.writeConfig(CONFIG);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
