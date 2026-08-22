package com.github.Soulphur0.mixin;

import com.github.Soulphur0.networking.EanClientPlayerData;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Camera.class)
public class CameraMixin {

    // ; the old "ean_test" @Inject that overwrote Camera#horizontalPlane in the constructor is
    // ; dropped - that field doesn't exist anywhere in 26.2's Camera, and given the method's own
    // ; name and the fact it just hardcoded a debug-looking value, this looks like leftover
    // ; experimental/debug code from the original mod rather than a real feature.

    // _ World class' getBlockState() bypass.
    // ? Called in Camera getFov(). getSubmersionType()/ready were renamed getFluidInCamera()/initialized in 26.2.
    // ¿ Gets submersion type for fluids in order to change the FOV.
    @ModifyExpressionValue(method ="getFluidInCamera", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Camera;initialized:Z"))
    private boolean ean_bypassCameraSubmersionType(boolean original) {
            return original || !EanClientPlayerData.hasChunkLoadingAbility();
    }
}
