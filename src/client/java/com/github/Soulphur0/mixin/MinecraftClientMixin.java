package com.github.Soulphur0.mixin;

import com.github.Soulphur0.networking.EanClientPlayerData;
import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * GameRenderer#updateTargetedEntity(F) was removed entirely in 26.2 - its logic moved to a
 * private Minecraft#pick(float) method. Since it moved onto Minecraft itself, this mixin now
 * covers both the old MinecraftClientMixin hook (tick()'s call) and what used to be
 * GameRendererMixin's separate hook (the per-render-frame call, now Minecraft#renderFrame()'s).
 */
@Mixin(Minecraft.class)
public class MinecraftClientMixin {

    // _ World class' getBlockState() bypass.
    @WrapWithCondition(method ="tick", at = @At(value ="INVOKE", target ="Lnet/minecraft/client/Minecraft;pick(F)V"))
    private boolean ean_skipUpdateTargetedEntity(Minecraft instance, float partialTicks){
        return EanClientPlayerData.hasChunkLoadingAbility();
    }

    // _ World class' getBlockState() bypass.
    @WrapWithCondition(method ="renderFrame", at = @At(value ="INVOKE", target ="Lnet/minecraft/client/Minecraft;pick(F)V"))
    private boolean ean_cancelUpdateTargetedEntity(Minecraft instance, float partialTicks){
        return EanClientPlayerData.hasChunkLoadingAbility();
    }
}
