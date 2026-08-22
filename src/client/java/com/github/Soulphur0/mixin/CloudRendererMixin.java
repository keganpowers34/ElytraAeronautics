package com.github.Soulphur0.mixin;

import com.github.Soulphur0.behaviour.client.EanCloudRenderBehaviour;
import com.github.Soulphur0.config.singletons.CloudConfig;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.CloudRenderer;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Intercepts only the vanilla singleton CloudRenderer instance owned by LevelRenderer -
 * the mod's own per-layer CloudRenderer instances (see EanCloudRenderBehaviour) are
 * driven directly and must NOT be re-intercepted here, hence the instance-identity check.
 */
@Mixin(CloudRenderer.class)
public class CloudRendererMixin {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void ean_renderCustomLayers(int color, CloudStatus cloudStatus, float bottomY, int range, Vec3 cameraPosition, long gameTime, float partialTicks, CallbackInfo ci){
        CloudRenderer self = (CloudRenderer)(Object)this;

        if (self == Minecraft.getInstance().levelRenderer.cloudRenderer() && CloudConfig.getOrCreateInstance().isUseEanClouds()){
            ci.cancel();
            // ; `color` here is vanilla's own precomputed ambient/weather-lit cloud color for this frame -
            // ; reused as the base for layers with skyEffects enabled, same as the old world.getCloudsColor() lookup did.
            EanCloudRenderBehaviour.ean_renderClouds(color, cloudStatus, cameraPosition, gameTime, partialTicks);
        }
    }
}
