package io.github.ivymc.compassplus;

import io.github.ivymc.compassplus.config.CommonConfig;
import io.github.ivymc.compassplus.config.CompassConfig;
import io.github.ivymc.ivycore.config.ConfigKey;
import net.fabricmc.tinyremapper.extension.mixin.common.data.CommonData;

public abstract class Configs {
    public static ConfigKey<CommonConfig> commonConfig;
    public static ConfigKey<CompassConfig> compassConfig;
}
