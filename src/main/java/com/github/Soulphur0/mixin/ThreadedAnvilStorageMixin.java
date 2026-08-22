package com.github.Soulphur0.mixin;

import com.github.Soulphur0.networking.EanPlayerDataCache;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = ChunkMap.class, priority = 1500)
public abstract class ThreadedAnvilStorageMixin {

    // ; handlePlayerAddedOrRemoved -> updatePlayerStatus, updatePosition -> move, and
    // ; doesNotGenerateChunks -> skipPlayer in 26.x (same semantics: spectator-mode chunk-gen skip).
    @WrapOperation(method = "updatePlayerStatus", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ChunkMap;skipPlayer(Lnet/minecraft/server/level/ServerPlayer;)Z"))
    private boolean ean_disableChunkGeneration_1(ChunkMap instance, ServerPlayer player, Operation<Boolean> original){
        boolean condition = EanPlayerDataCache.canPlayerLoadChunks(player.getUUID());
        return !condition;
    }

    @WrapOperation(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ChunkMap;skipPlayer(Lnet/minecraft/server/level/ServerPlayer;)Z"))
    private boolean ean_disableChunkGeneration_2(ChunkMap instance, ServerPlayer player, Operation<Boolean> original){
        boolean condition = EanPlayerDataCache.canPlayerLoadChunks(player.getUUID());
        return !condition;
    }
}