package com.github.Soulphur0.mixin;

import com.github.Soulphur0.networking.EanClientPlayerData;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FogRenderer.class)
public class BackgroundRendererMixin {

    // _ World class' getFluidState() bypass.
    // ; render()/applyFog() both got restructured around a shared FogData/setupFog() model in 26.x,
    // ; and both old call sites to Camera#getSubmersionType() (now getFluidInCamera()) collapsed
    // ; into this one shared private getFogType(Camera) helper - one hook now covers both old ones.
    @WrapOperation(method = "getFogType", at = @At(value ="INVOKE", target = "Lnet/minecraft/client/Camera;getFluidInCamera()Lnet/minecraft/world/level/material/FogType;"))
    private FogType ean_bypassGetSubmersionType(Camera instance, Operation<FogType> original){
        if (EanClientPlayerData.hasChunkLoadingAbility())
            return original.call(instance);
        else
            return FogType.NONE;
    }
}
