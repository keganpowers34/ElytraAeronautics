package com.github.Soulphur0.behaviour.server;

import com.github.Soulphur0.config.singletons.FlightConfig;
import net.minecraft.world.entity.LivingEntity;

public class EanPitchRealignmentBehaviour {

    public static void realignPitch(LivingEntity player){
        FlightConfig configInstance = FlightConfig.getOrCreateInstance();

        if(configInstance.isSneakingRealignsPitch() && player.isShiftKeyDown()){
            float pitch = player.getXRot();

            float alignmentAngle = configInstance.getRealignAngle();
            float alignmentRate = configInstance.getRealignRate();

            if (Math.abs(pitch) <= alignmentRate*2){
                player.setXRot(alignmentAngle);
            } else {
                if (pitch > alignmentAngle){
                    player.setXRot(pitch-alignmentRate);
                } else {
                    player.setXRot(pitch+alignmentRate);
                }
            }
        }
    }
}
