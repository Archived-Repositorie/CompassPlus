package io.github.ivymc.compassplus.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ModConfigs {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public final boolean enabled = false;
    public final boolean reduceDebug = true;


    public static void writeConfig(ModConfigs config) throws IOException {
        var path = FabricLoader.getInstance().getConfigDir().resolve("compassplus.json");
        Files.writeString(path, GSON.toJson(config));
    }

    public static ModConfigs readConfig() throws IOException {
        var path = FabricLoader.getInstance().getConfigDir().resolve("compassplus.json");
        return GSON.fromJson(Files.readString(path), ModConfigs.class);
    }
}
