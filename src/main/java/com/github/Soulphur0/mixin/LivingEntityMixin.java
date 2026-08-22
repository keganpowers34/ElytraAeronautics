package com.github.Soulphur0.mixin;

import com.github.Soulphur0.behaviour.server.EanFlightBehaviour;
import com.github.Soulphur0.behaviour.server.EanPitchRealignmentBehaviour;
import com.github.Soulphur0.networking.EanPlayerDataCache;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    protected LivingEntityMixin(EntityType<? extends LivingEntity> entityType, Level world){
        super(entityType,world);
    }

    // $ Flight speed injection point.
    // ; travel() was split into travelInFluid/travelFallFlying/travelInAir in 26.x - the flight
    // ; speed logic now belongs on travelFallFlying specifically, which has exactly one
    // ; setDeltaMovement call (ordinal 0), a much more precise target than the old "6th call in
    // ; the whole travel() method" ordinal guess.
    // ; confirmed via javap -v constant pool that this Methodref's owner is LivingEntity itself.
    @ModifyArg(method = "travelFallFlying", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V", ordinal = 0))
    private Vec3 ean_modifyVelocity(Vec3 original){
        return EanFlightBehaviour.ean_flightBehaviour(((LivingEntity)(Object)this), original);
    }

    // $ Pitch realignment injection point.
    // ; travel()'s old limitFallDistance() call this used to anchor on doesn't exist any more
    // ; (travel() is now just a 3-way dispatcher with no other calls at all) - moved to fire once
    // ; per tick at the top of travelFallFlying instead, which preserves the original intent
    // ; (realign pitch while elytra-flying) without depending on an inner call that no longer exists.
    @Inject(method="travelFallFlying", at = @At("HEAD"))
    private void ean_realignPitch(Vec3 input, CallbackInfo ci){
        EanPitchRealignmentBehaviour.realignPitch(((LivingEntity)(Object)this));
    }

    // _ World class' getBlockState() bypass.
    // ; isClimbing() was renamed onClimbable() in 26.2, its first check is still `if (this.isSpectator()) return false;`.
    // ; confirmed via javap -v constant pool that this Methodref's owner is LivingEntity itself.
    @ModifyExpressionValue(method ="onClimbable", at = @At(value ="INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isSpectator()Z"))
    private boolean ean_returnIsClimbing(boolean original){
        LivingEntity livingEntity = ((LivingEntity)(Object)this);

        if (livingEntity instanceof Player player){
            return original || !EanPlayerDataCache.canPlayerLoadChunks(player.getUUID());
        } else
            return original;
    }

    // TODO(port): addPowderSnowSlowIfNeeded() no longer exists as a single method - the powder
    // snow slow effect moved to an attribute-modifier system (SPEED_MODIFIER_POWDER_SNOW_ID) with
    // no single call site to bypass the same way. Dropped rather than guessed.

    // _ World class' getBlockState() bypass.
    @ModifyExpressionValue(method ="baseTick", at = @At(value ="INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isAlive()Z", ordinal = 0))
    private boolean ean_addConditionToAmbientDamageEffectsCalculations(boolean original){
        LivingEntity livingEntity = ((LivingEntity) (Object) this);

        if (livingEntity instanceof Player player){
            return original && EanPlayerDataCache.canPlayerLoadChunks(player.getUUID());
        } else
            return original;
    }

    // _ World class' getBlockState() bypass.
    // ; isWet() was renamed isInWaterOrRain() in 26.2 (same body: isInWater() || isInRain()), and the
    // ; call moved from baseTick() to aiStep() - confirmed by disassembling the actual bytecode
    // ; (javap -c) and mapping the invoke's offset back to its enclosing method, since decompiled
    // ; source line-tracing gave a wrong method boundary here.
    @WrapOperation(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isInWaterOrRain()Z"))
    private boolean ean_skipIsWet(LivingEntity instance, Operation<Boolean> original){
        LivingEntity livingEntity = ((LivingEntity) (Object) this);

        if (livingEntity instanceof Player player){
            if (!EanPlayerDataCache.canPlayerLoadChunks(player.getUUID()))
                return false;
            else return original.call(instance);
        }
        return original.call(instance);
    }

    // _ World class' getFluidState() bypass.
    // ; travel() still opens with `this.shouldTravelInFluid(this.level().getFluidState(this.blockPosition()))`.
    @WrapOperation(method ="travel", at = @At(value ="INVOKE", target ="Lnet/minecraft/world/level/Level;getFluidState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/material/FluidState;"))
    private FluidState ean_bypassGetFluidState(Level instance, BlockPos pos, Operation<FluidState> original){
        LivingEntity livingEntity = ((LivingEntity) (Object) this);

        if (livingEntity instanceof Player player){
            if (!EanPlayerDataCache.canPlayerLoadChunks(player.getUUID()))
                return Fluids.EMPTY.defaultFluidState();
            else return original.call(instance, pos);
        }
        return original.call(instance, pos);
    }
}
