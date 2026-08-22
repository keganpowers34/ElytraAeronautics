package com.github.Soulphur0.config.objects;

import com.github.Soulphur0.config.constants.CloudTypes;
import com.google.gson.annotations.Expose;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.CloudRenderer;

@Environment(EnvType.CLIENT)
public class CloudLayer {
    // ; Cloud positioning settings
    @Expose
    private double altitude;

    // ; Cloud rendering settings
    @Expose
    private CloudTypes cloudType = CloudTypes.LOD;
    @Expose
    private float verticalRenderDistance = 1000.0F;
    @Expose
    private int horizontalRenderDistance = 15;
    @Expose
    private float lodRenderDistance = 150.0F;

    // ; Cloud style settings
    @Expose
    private float cloudSpeed = 1.0F;
    @Expose
    private float cloudThickness = 4.0F;
    @Expose
    private int cloudColor = 0xffffff;
    @Expose
    private float cloudOpacity = 0.8F;
    @Expose
    private boolean shading = true;
    @Expose
    private boolean skyEffects = true;

    // = Contextual attributes for rendering
    // ; Each layer drives its own vanilla CloudRenderer instance (loaded lazily, see EanCloudRenderBehaviour) -
    // ; not persisted, not part of the config shape.
    private transient CloudRenderer cloudRenderer;

    public CloudLayer(){

    }

    // $ GETTERS & SETTERS
    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public CloudTypes getCloudType() {
        return cloudType;
    }

    public void setCloudType(CloudTypes cloudType) {
        this.cloudType = cloudType;
    }

    public float getVerticalRenderDistance() {
        return verticalRenderDistance;
    }

    public void setVerticalRenderDistance(float verticalRenderDistance) {
        this.verticalRenderDistance = verticalRenderDistance;
    }

    public int getHorizontalRenderDistance() {
        return horizontalRenderDistance;
    }

    public void setHorizontalRenderDistance(int horizontalRenderDistance) {
        this.horizontalRenderDistance = horizontalRenderDistance;
    }

    public float getLodRenderDistance() {
        return lodRenderDistance;
    }

    public void setLodRenderDistance(float lodRenderDistance) {
        this.lodRenderDistance = lodRenderDistance;
    }

    public float getCloudSpeed() {
        return cloudSpeed;
    }

    public void setCloudSpeed(float cloudSpeed) {
        this.cloudSpeed = cloudSpeed;
    }

    public float getCloudThickness() {
        return cloudThickness;
    }

    public void setCloudThickness(float cloudThickness) {
        this.cloudThickness = cloudThickness;
    }

    public int getCloudColor() {
        return cloudColor;
    }

    public void setCloudColor(int cloudColor) {
        this.cloudColor = cloudColor;
    }

    public float getCloudOpacity() {
        return cloudOpacity;
    }

    public void setCloudOpacity(float cloudOpacity) {
        this.cloudOpacity = cloudOpacity;
    }

    public boolean isShading() {
        return shading;
    }

    public void setShading(boolean shading) {
        this.shading = shading;
    }

    public CloudRenderer getCloudRenderer() {
        return cloudRenderer;
    }

    public void setCloudRenderer(CloudRenderer cloudRenderer) {
        this.cloudRenderer = cloudRenderer;
    }

    public boolean isSkyEffects() {
        return skyEffects;
    }

    public void setSkyEffects(boolean skyEffects) {
        this.skyEffects = skyEffects;
    }
}
