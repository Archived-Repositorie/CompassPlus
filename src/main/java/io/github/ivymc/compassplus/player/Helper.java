package io.github.ivymc.compassplus.player;

import eu.pb4.playerdata.api.PlayerDataApi;
import eu.pb4.playerdata.api.storage.JsonDataStorage;
import eu.pb4.playerdata.api.storage.PlayerDataStorage;
import net.minecraft.server.network.ServerPlayerEntity;

public class Helper {
    private static final PlayerDataStorage<Data> storage = new JsonDataStorage<>("compassplus", Data.class);
    private final ServerPlayerEntity playerEntity;
    private Helper(ServerPlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }
    public static Helper of(ServerPlayerEntity playerEntity) {
        return new Helper(playerEntity) {};
    }
    public Data getData() {
        var data = PlayerDataApi.getCustomDataFor(playerEntity, storage);
        if (data == null) {
            data = new Data();
            PlayerDataApi.setCustomDataFor(playerEntity, storage, data);
        }
        return data;
    }
    public Data writeData(Data data) {
        PlayerDataApi.setCustomDataFor(playerEntity, storage, data);
        return data;
    }
    public static boolean register() {
        return PlayerDataApi.register(storage);
    }
}
