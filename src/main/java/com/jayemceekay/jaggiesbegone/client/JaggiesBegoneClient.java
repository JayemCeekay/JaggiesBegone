package com.jayemceekay.jaggiesbegone.client;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class JaggiesBegoneClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AutoConfig.register(JaggiesBegoneConfig.class, GsonConfigSerializer::new);
        JaggiesBegoneConfig config = AutoConfig.getConfigHolder(JaggiesBegoneConfig.class).getConfig();
        HashMap<Block, RenderLayer> originalRenderLayers = new HashMap<>();

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            if (config.enableFXAA) {
                client.gameRenderer.loadShader(new Identifier("shaders/post/fxaa_of_4x.json"));
            } else {
                if(client.gameRenderer.getShader() != null) {
                    if (client.gameRenderer.getShader().getName().equalsIgnoreCase(new Identifier("shaders/post/fxaa_of_4x.json").toString())) {
                        client.gameRenderer.getShader().close();
                    }
                }
            }

            if (config.enableAutoMipmaps) {
                RenderLayers.BLOCKS.forEach((block, renderLayer) -> {
                    originalRenderLayers.put(block, renderLayer);
                    if (renderLayer == RenderLayer.getCutout()) {
                        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutoutMipped());
                    }
                });
            } else {
                RenderLayers.BLOCKS.forEach((block, renderLayer) -> {
                    if(originalRenderLayers.containsKey(block)) {
                        BlockRenderLayerMap.INSTANCE.putBlock(block, originalRenderLayers.get(block));
                    }
                });
            }
        });
    }
}
