package com.github.Soulphur0.behaviour.client;

import com.github.Soulphur0.config.objects.CloudLayer;
import com.github.Soulphur0.config.singletons.CloudConfig;
import com.github.Soulphur0.mixin.CloudRendererInvoker;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.CloudRenderer;
import net.minecraft.util.ARGB;
import net.minecraft.util.profiling.InactiveProfiler;
import net.minecraft.world.phys.Vec3;

/**
 * Vanilla rewrote cloud rendering entirely for 26.x - the old immediate-mode
 * Tessellator/BufferBuilder/VertexBuffer/ShaderProgram pipeline this used to hand-roll
 * custom multi-layer geometry with no longer exists. The new {@link CloudRenderer} is a
 * self-contained, GPU-buffer-driven single-layer renderer, but its render() method
 * usefully still takes color/altitude/range/status as plain call parameters - so instead
 * of reimplementing cloud geometry building, each configured layer gets its own
 * CloudRenderer instance (see {@link CloudLayer#getCloudRenderer()}), driven with that
 * layer's settings. See {@link com.github.Soulphur0.mixin.CloudRendererMixin} for where
 * this gets called instead of vanilla's single default render.
 *
 * Known limitation: vanilla's CloudRenderer hardcodes cloud thickness (4 blocks) and
 * scroll speed internally - they're no longer exposed as parameters, so per-layer
 * cloudThickness/cloudSpeed config values are preserved on disk but currently have no
 * effect on rendering. Restoring that would require mixin-patching CloudRenderer's
 * internal constants, not just calling its public API.
 */
@Environment(EnvType.CLIENT)
public class EanCloudRenderBehaviour {

    public static void ean_renderClouds(int ambientColor, CloudStatus cloudStatus, Vec3 cameraPosition, long gameTime, float partialTicks){
        CloudConfig config = CloudConfig.getOrCreateInstance();

        for (int layerNum = 0; layerNum < config.getNumberOfLayers(); layerNum++) {
            CloudLayer layer = CloudConfig.cloudLayers[layerNum];

            double verticalDistance = Math.abs(layer.getAltitude() - cameraPosition.y);
            boolean withinRenderDistance = verticalDistance <= layer.getVerticalRenderDistance();
            boolean withinLodRenderDistance = verticalDistance <= layer.getLodRenderDistance();

            CloudStatus effectiveStatus;
            boolean shouldRender;
            switch (layer.getCloudType()) {
                case FANCY -> {
                    effectiveStatus = CloudStatus.FANCY;
                    shouldRender = withinRenderDistance;
                }
                case FAST -> {
                    effectiveStatus = CloudStatus.FAST;
                    shouldRender = withinRenderDistance;
                }
                case LOD -> {
                    if (withinRenderDistance) {
                        effectiveStatus = CloudStatus.FANCY;
                        shouldRender = true;
                    } else {
                        effectiveStatus = CloudStatus.FAST;
                        shouldRender = withinLodRenderDistance;
                    }
                }
                default -> {
                    effectiveStatus = cloudStatus;
                    shouldRender = true;
                }
            }

            if (!shouldRender) continue;

            CloudRenderer renderer = ean_getOrCreateRenderer(layer);
            int color = ean_computeLayerColor(ambientColor, layer);
            int range = (int) layer.getHorizontalRenderDistance();

            renderer.render(color, effectiveStatus, (float) layer.getAltitude(), range, cameraPosition, gameTime, partialTicks);
        }
    }

    // ? Lazily create and load each layer's own CloudRenderer instance.
    // ¿ CloudRenderer normally loads its texture via the resource-reload system; since these extra
    // ¿ instances aren't registered listeners, prepare()/apply() are invoked manually, once, via the invoker mixin.
    private static CloudRenderer ean_getOrCreateRenderer(CloudLayer layer){
        CloudRenderer renderer = layer.getCloudRenderer();

        if (renderer == null){
            renderer = new CloudRenderer();
            CloudRendererInvoker invoker = (CloudRendererInvoker) renderer;
            var preparations = invoker.ean_prepare(Minecraft.getInstance().getResourceManager(), InactiveProfiler.INSTANCE);
            invoker.ean_apply(preparations, Minecraft.getInstance().getResourceManager(), InactiveProfiler.INSTANCE);
            layer.setCloudRenderer(renderer);
        }

        return renderer;
    }

    // ? Combine the layer's configured color+opacity with vanilla's ambient/weather-lit cloud color when skyEffects is on.
    private static int ean_computeLayerColor(int ambientColor, CloudLayer layer){
        int alpha = (int) (layer.getCloudOpacity() * 255.0F);

        if (layer.isSkyEffects()){
            int red = ARGB.red(ambientColor) * ARGB.red(layer.getCloudColor()) / 255;
            int green = ARGB.green(ambientColor) * ARGB.green(layer.getCloudColor()) / 255;
            int blue = ARGB.blue(ambientColor) * ARGB.blue(layer.getCloudColor()) / 255;
            return ARGB.color(alpha, red, green, blue);
        } else {
            return ARGB.color(alpha, layer.getCloudColor());
        }
    }
}
