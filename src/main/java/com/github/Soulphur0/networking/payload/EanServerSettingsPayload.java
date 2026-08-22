package com.github.Soulphur0.networking.payload;

import com.github.Soulphur0.config.EanServerSettings;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

/**
 * S2C payload carrying all server-dependant config settings (flight + world rendering).
 * Reuses {@link EanServerSettings}' own buffer read/write logic unchanged.
 */
public record EanServerSettingsPayload(EanServerSettings serverSettings) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<EanServerSettingsPayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath("ean", "sync_config"));

    public static final StreamCodec<RegistryFriendlyByteBuf, EanServerSettingsPayload> CODEC =
            CustomPacketPayload.codec(EanServerSettingsPayload::write, EanServerSettingsPayload::read);

    private void write(RegistryFriendlyByteBuf buf) {
        serverSettings.writeToBuffer(buf);
    }

    private static EanServerSettingsPayload read(RegistryFriendlyByteBuf buf) {
        return new EanServerSettingsPayload(EanServerSettings.createFromBuffer(buf));
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
