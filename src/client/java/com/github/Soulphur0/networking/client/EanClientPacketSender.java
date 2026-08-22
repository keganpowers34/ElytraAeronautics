package com.github.Soulphur0.networking.client;

import com.github.Soulphur0.networking.payload.EanChunkLoadingPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import java.util.UUID;

public class EanClientPacketSender {

    // ? Sends whether the player's should generate chunks to the server.
    // ¿ Used in the mixin entries.
    public static void sendPlayerChunkLoadingAbility(UUID playerUuid, boolean canLoadChunks){
        ClientPlayNetworking.send(new EanChunkLoadingPayload(playerUuid, canLoadChunks));
    }
}
