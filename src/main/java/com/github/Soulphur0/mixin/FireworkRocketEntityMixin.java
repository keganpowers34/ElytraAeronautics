package com.github.Soulphur0.mixin;

import com.github.Soulphur0.behaviour.server.EanRocketBoostBehaviour;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(FireworkRocketEntity.class)
public class FireworkRocketEntityMixin   {

    // ; "shooter" was renamed "attachedToEntity" in 26.x - the boost logic in tick() now only
    // ; runs when attachedToEntity.isFallFlying(), same semantic as before (elytra-attached rocket).
    @Shadow private @Nullable LivingEntity attachedToEntity;

    // ; confirmed via javap -v constant pool: this call's Methodref owner is LivingEntity, matching
    // ; the declared type of the attachedToEntity field.
    @ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V", ordinal = 0))
    private Vec3 ean_modifyRocketBoostVelocity(Vec3 original){
        return EanRocketBoostBehaviour.calcFireworkRocketBoost(attachedToEntity, original);
    }
}
