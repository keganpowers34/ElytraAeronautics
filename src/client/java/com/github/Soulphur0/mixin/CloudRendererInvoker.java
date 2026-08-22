package com.github.Soulphur0.mixin;

import java.util.Optional;
import net.minecraft.client.renderer.CloudRenderer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * Exposes CloudRenderer's protected resource-reload methods so each of the mod's
 * per-layer CloudRenderer instances can have its cloud texture loaded manually,
 * without registering them as real resource reload listeners.
 */
@Mixin(CloudRenderer.class)
public interface CloudRendererInvoker {
    @Invoker("prepare")
    Optional<CloudRenderer.TextureData> ean_prepare(ResourceManager manager, ProfilerFiller profiler);

    @Invoker("apply")
    void ean_apply(Optional<CloudRenderer.TextureData> preparations, ResourceManager manager, ProfilerFiller profiler);
}
