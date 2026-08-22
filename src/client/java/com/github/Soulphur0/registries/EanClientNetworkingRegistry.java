package com.github.Soulphur0.registries;

import com.github.Soulphur0.config.singletons.FlightConfig;
import com.github.Soulphur0.config.singletons.WorldRenderingConfig;
import com.github.Soulphur0.config.updaters.CloudConfigUpdater;
import com.github.Soulphur0.networking.payload.EanClientSettingsPayload;
import com.github.Soulphur0.networking.payload.EanServerSettingsPayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import java.util.Arrays;

@Environment(EnvType.CLIENT)
public class EanClientNetworkingRegistry {

    public static void registerEanClientReceivers(){
        ClientPlayNetworking.registerGlobalReceiver(EanServerSettingsPayload.TYPE, (payload, context) -> {
            FlightConfig.updateClientSettings(payload.serverSettings().getFlightConfigInstance());
            WorldRenderingConfig.updateClientSettings(payload.serverSettings().getWorldRenderingConfigInstance());
        });

        ClientPlayNetworking.registerGlobalReceiver(EanClientSettingsPayload.TYPE, (payload, context) -> {
            String[] settingValues = payload.clientSettings().getSettingValues();
            String settingCategory = settingValues[0];
            String[] rest = Arrays.copyOfRange(settingValues, 1, settingValues.length);

            switch (settingCategory){
                case "generalCloudConfig" -> CloudConfigUpdater.updateGeneralConfig(context.client(), rest);
                case "cloudLayerConfig" -> CloudConfigUpdater.updateCloudLayerConfig(context.client(), rest);
            }
        });
    }
}
