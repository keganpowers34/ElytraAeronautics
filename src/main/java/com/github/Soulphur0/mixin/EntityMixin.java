package com.github.Soulphur0.mixin;

import com.github.Soulphur0.networking.EanPlayerDataCache;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = Entity.class, priority = 500)
public class EntityMixin {

    // _ World class' getBlockState() bypass.
    // ? Called in Entity move().
    // ¿ Bypasses most of the movement calls and therefore getBlockState() calls that aren't made in spectator mode either.
    @ModifyExpressionValue(method ="move", at = @At(value = "FIELD", target ="Lnet/minecraft/world/entity/Entity;noPhysics:Z"))
    private boolean ean_bypassMovementCalls(boolean original){
        Entity entity = ((Entity)(Object)this);

        if (entity instanceof Player player){
            return original || !EanPlayerDataCache.canPlayerLoadChunks(player.getUUID());
        } else
            return original;
    }

    // TODO(port): updateMovementInFluid/isRegionUnloaded and updateSubmergedInWaterState/getFluidState
    // no longer exist on Entity in 26.2 - fluid interaction was extracted into a separate
    // EntityFluidInteraction helper (Entity#fluidInteraction, see EntityFluidInteraction#update()),
    // which now owns the getFluidState() call this used to bypass. Needs its own mixin targeting
    // EntityFluidInteraction rather than Entity - not yet done, needs real-hardware verification
    // of how much this actually matters for the "fly without loading chunks" feature.
}
