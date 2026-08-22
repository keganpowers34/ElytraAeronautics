package com.github.Soulphur0.mixin;

import com.github.Soulphur0.networking.EanClientPlayerData;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientLevel.class)
public class ClientWorldMixin {

    // _ World class' getBlockState() bypass.
    // ? Called in ClientLevel doAnimateTick() (renamed from randomBlockDisplayTick in 26.2, and
    // ? split into animateTick()/doAnimateTick() - the getBlockState/getFluidState calls live in doAnimateTick).
    // ¿ Gets random blocks across the world to tick visual effects.
    // ; confirmed via javap -v constant pool: this self-call's Methodref owner is ClientLevel itself.
    @WrapOperation(method = "doAnimateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;"))
    private BlockState ean_conditionGetBlockState(ClientLevel instance, BlockPos pos, Operation<BlockState> original){
        if (EanClientPlayerData.hasChunkLoadingAbility())
            return original.call(instance, pos);
        else
            return Blocks.VOID_AIR.defaultBlockState();
    }

    // _ World class' getFluidState() bypass.
    // ? Called in ClientLevel doAnimateTick().
    @WrapOperation(method = "doAnimateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;getFluidState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/material/FluidState;"))
    private FluidState ean_conditionGetFluidState(ClientLevel instance, BlockPos pos, Operation<FluidState> original){
        if (EanClientPlayerData.hasChunkLoadingAbility())
            return original.call(instance, pos);
        else
            return Fluids.EMPTY.defaultFluidState();
    }
}
