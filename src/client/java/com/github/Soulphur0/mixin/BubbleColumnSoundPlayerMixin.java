package com.github.Soulphur0.mixin;

import com.github.Soulphur0.networking.EanClientPlayerData;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.stream.Stream;
import net.minecraft.client.resources.sounds.BubbleColumnAmbientSoundHandler;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

@Mixin(BubbleColumnAmbientSoundHandler.class)
public class BubbleColumnSoundPlayerMixin {

    // _ World class' getBlockState() bypass.
    // ? Called in ClientPlayerEntity tick().
    // ¿ Ticks this ClientPlayerTickable.
    // ; getStatesInBoxIfLoaded was renamed getBlockStatesIfLoaded in 26.2. Confirmed via javap -v
    // ; constant pool: the local `level` variable has static type Level, so that's the Methodref owner
    // ; even though the method body itself is a default method on the LevelReader interface.
    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target ="Lnet/minecraft/world/level/Level;getBlockStatesIfLoaded(Lnet/minecraft/world/phys/AABB;)Ljava/util/stream/Stream;"))
    private Stream<BlockState> ean_bypassTickCalculations(Level instance, AABB box, Operation<Stream<BlockState>> original){
        if (EanClientPlayerData.hasChunkLoadingAbility())
            return original.call(instance, box);
        else
            return Stream.empty();
    }
}
