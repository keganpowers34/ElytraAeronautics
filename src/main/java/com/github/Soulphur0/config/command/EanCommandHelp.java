package com.github.Soulphur0.config.command;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public class EanCommandHelp {

    // : FLIGHT CONFIG ---------------------------------------------------------------------------------------------------
    public static Component setAltitudeDeterminesSpeed(){
        return Component.literal("\n")
                .append(Component.literal("Altitude determines flight speed").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [true/false]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Makes elytra flight faster at higher altitudes.
                      
                        """
                ))
                .append(Component.literal("Default value: true").withStyle(ChatFormatting.AQUA));
    }

    public static Component setMinSpeed(){
        return Component.literal("\n")
                .append(Component.literal("Minimal flight speed").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [numerical]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Minimal flight speed achieved by travelling at a pitch of 0°.
                        This speed may increase a little when 'nosediving'.
                        
                        It can be lowered to achieve speeds lower than vanilla's.
                      
                        """
                ))
                .append(Component.literal("Default value: 30.35 (m/s) (Vanilla flight speed)").withStyle(ChatFormatting.AQUA));
    }

    public static Component setMaxSpeed(){
        return Component.literal("\n")
                .append(Component.literal("Maximum flight speed").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [numerical]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Maximum flight speed achieved by travelling at a pitch of 0°.
                        This speed may increase a little when 'nosediving'.
                        
                        This value can be cranked up to the hundred of thousands of
                        blocks per second but it is highly recommended to adjust the
                        WorldRendering options accordingly.
                      
                        """
                ))
                .append(Component.literal("Default value: 257.22 (m/s)").withStyle(ChatFormatting.AQUA));
    }

    public static Component setMinHeight(){
        return Component.literal("\n")
                .append(Component.literal("Minimal height").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [numerical]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Altitude at which flight speed starts to increase.
                      
                        """
                ))
                .append(Component.literal("Default value: 250 (Y)").withStyle(ChatFormatting.AQUA));
    }

    public static Component setMaxHeight(){
        return Component.literal("\n")
                .append(Component.literal("Maximum height").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [numerical]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Altitude at which flight speed reaches its maximum.
                      
                        """
                ))
                .append(Component.literal("Default value: 1000 (Y)").withStyle(ChatFormatting.AQUA));
    }

    public static Component setSneakingRealignsPitch(){
        return Component.literal("\n")
                .append(Component.literal("Sneaking realigns pitch").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [true/false]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Makes the player realign their pitch when sneaking mid-flight.
                      
                        """
                ))
                .append(Component.literal("Default value: true").withStyle(ChatFormatting.AQUA));
    }

    public static Component setRealignAngle(){
        return Component.literal("\n")
                .append(Component.literal("Pitch realignment angle").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [numerical]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Pitch angle at which the player aligns
                        towards when sneaking mid-flight.
                        
                        Measured in angle degrees.
                      
                        """
                ))
                .append(Component.literal("Default value: 0.0 (degrees)").withStyle(ChatFormatting.AQUA));
    }

    public static Component setRealignRate(){
        return Component.literal("\n")
                .append(Component.literal("Pitch realignment rate").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [numerical]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Amount of rotation at which players realign
                        towards the "realignAngle".
                        
                        Measured in degrees-per-tick.
                      
                        """
                ))
                .append(Component.literal("Default value: 0.1 (degrees-per-tick)").withStyle(ChatFormatting.AQUA));
    }

    // : WORLD RENDERING CONFIG ---------------------------------------------------------------------------------------------------
    public static Component setUseEanChunkUnloading(){
        return Component.literal("\n")
                .append(Component.literal("Use chunk unloading").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [true/false]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Stop chunk load or generation given certain set conditions.
                        Those can be set with the 'setChunkUnloadingCondition' option.
                      
                        """
                ))
                .append(Component.literal("Default value: true").withStyle(ChatFormatting.AQUA));
    }

    public static Component setChunkUnloadingCondition(){
        return Component.literal("\n")
                .append(Component.literal("Chunk unloading condition").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal("\n[speed/height/speed_or_height/speed_and_height]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Determines when chunks will stop loading and generating.
                        This will happen when exceeding a flight speed and/or height.
                        These thresholds can be adjusted using the other options.
                      
                        """
                ))
                .append(Component.literal("Default value: SPEED_OR_HEIGHT").withStyle(ChatFormatting.AQUA));
    }

    public static Component setChunkUnloadingSpeed(){
        return Component.literal("\n")
                .append(Component.literal("Chunk unloading speed threshold").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [numerical]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Speed at which chunks will stop to load while elytra-flying.
                        Measured in blocks/meters per second.
                      
                        """
                ))
                .append(Component.literal("Default value: 100 (m/s)").withStyle(ChatFormatting.AQUA));
    }

    public static Component setChunkUnloadingHeight(){
        return Component.literal("\n")
                .append(Component.literal("Chunk unloading height threshold").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [numerical]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Altitude at which chunks will stop to load while elytra-flying.
                        This number represents a 'Y' coordinate.
                      
                        """
                ))
                .append(Component.literal("Default value: 320 (Y)").withStyle(ChatFormatting.AQUA));
    }


    // : CLOUD CONFIG ---------------------------------------------------------------------------------------------------

    public static Component useEanCloudRendering(){
        return Component.literal("\n")
                .append(Component.literal("Use custom cloud rendering").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [true/false]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Use the multi-layer cloud rendering and cloud customization.
                      
                        """
                ))
                .append(Component.literal("Default value: true").withStyle(ChatFormatting.AQUA));
    }

    public static Component setCloudLayerAmount(){
        return Component.literal("\n")
                .append(Component.literal("Cloud layer amount").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [numerical]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Amount of cloud layers to render.
                        Use the 'configCloudLayer' option to config one or all layers.
                      
                        """
                ))
                .append(Component.literal("Default value: 3").withStyle(ChatFormatting.AQUA));
    }

    public static Component loadPreset(){
        return Component.literal("\n")
                .append(Component.literal("Load cloud preset").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [select]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Use this option to select a cloud preset:
                        Default, Windy, Puffy, Rainbow, Sky_highway, Sea_mist.
                        
                        Give them a try to see the potential of cloud customization.
                      
                        """
                ))
                .append(Component.literal("Default value: DEFAULT").withStyle(ChatFormatting.AQUA));
    }

    public static Component altitude(){
        return Component.literal("\n")
                .append(Component.literal("Layer altitude").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [numerical]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Altitude at which the selected cloud layer/s will render.
                        
                        """
                ))
                .append(Component.literal("""
                        
                        Values of the default layers:
                        
                        192.0 (Vanilla value)
                        250.0 (speed curve start)
                        1000.0 (speed curve end)
                        
                        """).withStyle(ChatFormatting.AQUA));
    }

    public static Component cloudType(){
        return Component.literal("\n")
                .append(Component.literal("Cloud type").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [select]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        The type of cloud to render.
                        There are three types:
                        
                        FAST - Vanilla fast clouds
                        FANCY - Vanilla fancy clouds
                        LOD - 'FAST' when far away, 'FANCY' when close to them.
                        
                        The LOD clouds' FAST to FANCY transition distance can be set.

                        """
                ))
                .append(Component.literal("Values of the default layers: FANCY, LOD, LOD").withStyle(ChatFormatting.AQUA));
    }

    public static Component verticalRenderDistance(){
        return Component.literal("\n")
                .append(Component.literal("Vertical render distance").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [numerical]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Maximum vertical distance at which the cloud layer renders.
                        Measured in blocks from the cloud layer, vertically.

                        """
                ))
                .append(Component.literal("Default value: 1000 (blocks)").withStyle(ChatFormatting.AQUA));
    }

    public static Component horizontalRenderDistance(){
        return Component.literal("\n")
                .append(Component.literal("Horizontal render distance").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [numerical]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Area of the sky that the selected cloud layer will occupy.
                        Measured in approximations of chunks, like vanilla Minecraft.

                        """
                ))
                .append(Component.literal("Default value: 15 (chunks) (Vanilla value)").withStyle(ChatFormatting.AQUA));
    }

    public static Component lodRenderDistance(){
        return Component.literal("\n")
                .append(Component.literal("LOD render distance").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [numerical]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Vertical distance at which LOD clouds will change.
                        Below this value clouds are shown as FANCY.
                        Above this value clouds are shown as FAST.
                        
                        The cloud type for the layer must be LOD for this to work.

                        """
                ))
                .append(Component.literal("Default value: 150 (blocks)").withStyle(ChatFormatting.AQUA));
    }

    public static Component thickness(){
        return Component.literal("\n")
                .append(Component.literal("Cloud thickness").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [numerical]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Amount of blocks that the clouds will occupy vertically.

                        """
                ))
                .append(Component.literal("Default value: 4 (blocks) (Vanilla value)").withStyle(ChatFormatting.AQUA));
    }

    public static Component color(){
        return Component.literal("\n")
                .append(Component.literal("Cloud color").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [hexadecimal]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Color that the clouds will be rendered with.
                        This value must be an hexadecimal color code.

                        """
                ))
                .append(Component.literal("Default value: FFFFFF (white) (Vanilla value)").withStyle(ChatFormatting.AQUA));
    }

    public static Component opacity(){
        return Component.literal("\n")
                .append(Component.literal("Cloud opacity").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [numerical]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Opacity that the clouds will be rendered with.
                        It ranges between 1.0 (opaque) and 0.0 (invisible).

                        """
                ))
                .append(Component.literal("Default value: 0.8 (Vanilla value)").withStyle(ChatFormatting.AQUA));
    }

    public static Component shading(){
        return Component.literal("\n")
                .append(Component.literal("Cloud shading").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [true/false]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        The different sides of the clouds will have different tones.
                        Otherwise renders clouds with a solid, monochromatic, color.

                        """
                ))
                .append(Component.literal("Default value: true (Vanilla value)").withStyle(ChatFormatting.AQUA));
    }

    public static Component speed(){
        return Component.literal("\n")
                .append(Component.literal("Cloud speed").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [numerical]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Speed at which the clouds will travel.
                        This value represents a multiplier for the vanilla cloud speed.
                        
                        For example, '2.0' will mean x2 the vanilla cloud speed.

                        """
                ))
                .append(Component.literal("Default value: 1.0 (x1.0 speed) (Vanilla value)").withStyle(ChatFormatting.AQUA));
    }

    public static Component skyEffects(){
        return Component.literal("\n")
                .append(Component.literal("Sky effects").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD))
                .append(Component.literal(" [true/false]\n").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA))
                .append(Component.nullToEmpty(
                        """
                        
                        Clouds turn darker at night and under weather conditions.
                        When disabled, clouds render like an emissive texture.

                        """
                ))
                .append(Component.literal("Default value: true (Vanilla value)").withStyle(ChatFormatting.AQUA));
    }
}
