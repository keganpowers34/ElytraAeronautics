package com.github.Soulphur0.networking.server;

import com.github.Soulphur0.config.EanClientSettings;
import com.github.Soulphur0.config.EanServerSettings;
import com.github.Soulphur0.networking.payload.EanClientSettingsPayload;
import com.github.Soulphur0.networking.payload.EanServerSettingsPayload;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

/**
 * Class that holds utility method for networking.<br><br>
 * Its main purpose is to hold static methods with all necessary sends to the client.<br><br>
 * */
public class EanServerPacketSender {

    // : SERVER CONFIG ---------------------------------------------------------------------------------------------------

    // ? Sync all clients' config with the server.
    // ¿ Used when the server config is changed by an operator.
    public static void syncAllClientsConfigWithServer(MinecraftServer server){
        EanServerSettingsPayload payload = new EanServerSettingsPayload(new EanServerSettings());

        // + Send sync order to all connected clients.
        for (ServerPlayer serverPlayer : PlayerLookup.all(server)){
            ServerPlayNetworking.send(serverPlayer, payload);
        }
    }

    // ? Sync a single client's config with the server.
    // ¿ Used when a player joins the server.
    public static void syncClientConfigWithServer(Player user){
        if(user.level().isClientSide()) return;

        // + Send sync order to the joined player.
        ServerPlayNetworking.send((ServerPlayer) user, new EanServerSettingsPayload(new EanServerSettings()));
    }

    // : CLIENT CONFIG ---------------------------------------------------------------------------------------------------

    // ? Send config written in command to the client.
    // ¿ Since the config command is server side, it is needed to send a packet to the client in order to update client-related configuration.
    // ! A whole custom packet is not needed because client config will always only need to get one value read at the same time, while server config needs all values to be read at once when joining a server.
    public static void sendUpdatedClientConfig(EanClientSettings setting, CommandContext<CommandSourceStack> context){
        if (context.getSource().getLevel().isClientSide() || context.getSource().getPlayer() == null) return;

        // + Send packet to the client.
        ServerPlayNetworking.send(context.getSource().getPlayer(), new EanClientSettingsPayload(setting));
    }
}
