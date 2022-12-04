package io.github.ivymc.compassplus.config;

import com.google.gson.JsonObject;
import io.github.ivymc.ivycore.config.ConfigData;

public class CommonConfig extends ConfigData {
    public boolean enabled = true;
    public boolean reduceDebug = false;

    @Override
    public void onRead(JsonObject jsonObject) throws Exception {

    }
}
