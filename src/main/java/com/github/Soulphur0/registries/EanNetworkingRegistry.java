package com.github.Soulphur0.registries;

import com.github.Soulphur0.networking.EanPlayerDataCache;
import com.github.Soulphur0.networking.payload.EanChunkLoadingPayload;
import com.github.Soulphur0.networking.payload.EanClientSettingsPayload;
import com.github.Soulphur0.networking.payload.EanServerSettingsPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class EanNetworkingRegistry {

    public static void registerEanServerReceivers(){
        // . S2C payload types
        PayloadTypeRegistry.clientboundPlay().register(EanServerSettingsPayload.TYPE, EanServerSettingsPayload.CODEC);
        PayloadTypeRegistry.clientboundPlay().register(EanClientSettingsPayload.TYPE, EanClientSettingsPayload.CODEC);

        // . C2S payload type + receiver
        PayloadTypeRegistry.serverboundPlay().register(EanChunkLoadingPayload.TYPE, EanChunkLoadingPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(EanChunkLoadingPayload.TYPE, (payload, context) ->
                EanPlayerDataCache.setOrUpdateCanPlayerLoadChunks(payload.playerUuid(), payload.canLoadChunks()));
    }
}
