package io.github.ivymc.compassplus;

import io.github.ivymc.compassplus.config.CommonConfig;
import io.github.ivymc.compassplus.config.CompassConfig;
import io.github.ivymc.ivycore.Global;
import io.github.ivymc.ivycore.config.ConfigBuilder;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

import java.io.IOException;
import java.nio.file.Path;

public class PreMain implements PreLaunchEntrypoint {
    public static Global g = new Global("compassplus");
    public static ConfigBuilder configBuilder = new ConfigBuilder(g.MOD_ID);

    @Override
    public void onPreLaunch() {
        try {
            configBuilder.loadConfig();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Configs.commonConfig = configBuilder.createConfigKey(Path.of("commonconfig.json"), CommonConfig.class);
        Configs.compassConfig = configBuilder.createConfigKey(Path.of("itemconfig.json"), CompassConfig.class);
        try {
            Configs.commonConfig.readConfig();
            Configs.compassConfig.readConfig();
        } catch (IOException e) {
            try {
                Configs.commonConfig.writeConfig();
                Configs.compassConfig.writeConfig();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
