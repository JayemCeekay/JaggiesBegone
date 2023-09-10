package com.jayemceekay.jaggiesbegone.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GameRenderer.class)
public interface GameRendererMixinAA {


    @Accessor("SHADERS_LOCATIONS")
    public default void setSHADERS_LOCATIONS(Identifier[] NEWSHADERS_LOCATIONS) {
    }

}
