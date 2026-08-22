package com.github.Soulphur0.networking.payload;

import com.github.Soulphur0.config.EanClientSettings;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

/**
 * S2C payload sent when an operator changes a single client-side setting via command.
 * The first value is always the setting category ("generalCloudConfig"/"cloudLayerConfig"),
 * the rest depend on which category it is - see {@code EanClientNetworkingRegistry}'s receiver for this type.
 */
public record EanClientSettingsPayload(EanClientSettings clientSettings) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<EanClientSettingsPayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath("ean", "client_config"));

    public static final StreamCodec<RegistryFriendlyByteBuf, EanClientSettingsPayload> CODEC =
            CustomPacketPayload.codec(EanClientSettingsPayload::write, EanClientSettingsPayload::read);

    private void write(RegistryFriendlyByteBuf buf) {
        clientSettings.writeToBuffer(buf);
    }

    private static EanClientSettingsPayload read(RegistryFriendlyByteBuf buf) {
        return new EanClientSettingsPayload(EanClientSettings.createFromBuffer(buf));
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
