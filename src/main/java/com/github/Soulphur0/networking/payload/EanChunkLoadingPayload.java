package com.github.Soulphur0.networking.payload;

import java.util.UUID;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

/**
 * C2S payload sent every client tick, telling the server whether this player should currently
 * be treated as chunk-loading (used by the "fly without loading chunks" bypass system).
 */
public record EanChunkLoadingPayload(UUID playerUuid, boolean canLoadChunks) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<EanChunkLoadingPayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath("ean", "client_chunk_loading"));

    public static final StreamCodec<RegistryFriendlyByteBuf, EanChunkLoadingPayload> CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, EanChunkLoadingPayload::playerUuid,
            ByteBufCodecs.BOOL, EanChunkLoadingPayload::canLoadChunks,
            EanChunkLoadingPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
