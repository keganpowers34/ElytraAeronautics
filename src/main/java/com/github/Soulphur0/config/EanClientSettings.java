package com.github.Soulphur0.config;

import com.github.Soulphur0.networking.payload.EanClientSettingsPayload;
import net.minecraft.network.FriendlyByteBuf;

/**
 * Class for EanClientSettings objects.<br><br>
 * This class, unlike the rest of config classes, is not a singleton, but rather an object that is instantiated whenever
 * client's settings are updated via command (server-sided) and need to be packed to be sent from the server to the client.<br><br>
 * @see EanClientSettingsPayload
 * */
public class EanClientSettings {
    String[] settingValues;

    // ? Constructor with arbitrary number of parameters.
    // ¿ The first String determines the setting type, the rest of string depend on the setting in particular.
    // ¿ The client-side payload handler redirects to the setting processing method for each client setting in its corresponding class.
    public EanClientSettings (String ...values){
        settingValues = values;
    }

    public String[] getSettingValues() {
        return settingValues;
    }

    // . NETWORKING

    // ? Write all the values of the setting to the packet buff, length-prefixed so the count can be recovered on read.
    public void writeToBuffer(FriendlyByteBuf buf){
        buf.writeVarInt(settingValues.length);
        for (String settingValue : settingValues) {
            buf.writeUtf(settingValue);
        }
    }

    // ? Read from the packet buf all the values of the setting sequentially.
    // ¿ Used to rebuild this class object from a received packet.
    public static EanClientSettings createFromBuffer(FriendlyByteBuf buf){
        int count = buf.readVarInt();
        String[] settingValues = new String[count];

        for (int i = 0; i < count; i++) {
            settingValues[i] = buf.readUtf();
        }

        return new EanClientSettings(settingValues);
    }
}
