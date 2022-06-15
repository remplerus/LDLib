package com.lowdragmc.lowdraglib.client.particle.impl;

import com.lowdragmc.lowdraglib.client.particle.TrailParticle;
import com.lowdragmc.lowdraglib.client.shader.Shaders;
import com.lowdragmc.lowdraglib.client.shader.management.Shader;
import com.lowdragmc.lowdraglib.client.shader.management.ShaderManager;
import com.lowdragmc.lowdraglib.client.shader.management.ShaderProgram;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

/**
 * @author KilaBash
 * @date 2022/05/30
 * @implNote TestTrailParticle
 */
public class ShaderTrailParticle extends TrailParticle {
    public final ShaderTrailRenderType renderType;

    public ShaderTrailParticle(ClientLevel level, double x, double y, double z, ShaderTrailRenderType renderType) {
        super(level, x, y, z);
        this.renderType = renderType;
    }

    public ShaderTrailParticle(ClientLevel level, double x, double y, double z, double sX, double sY, double sZ, ShaderTrailRenderType renderType) {
        super(level, x, y, z, sX, sY, sZ);
        this.renderType = renderType;
    }

    @Override
    @Nonnull
    public ParticleRenderType getRenderType() {
        return renderType;
    }

    public static class ShaderTrailRenderType implements ParticleRenderType {
        ResourceLocation shader;
        Consumer<ShaderProgram> shaderProgramConsumer;

        public ShaderTrailRenderType(ResourceLocation shader) {
            this.shader = shader;
        }

        public ShaderTrailRenderType(ResourceLocation shader, Consumer<ShaderProgram> shaderProgramConsumer) {
            this(shader);
            this.shaderProgramConsumer = shaderProgramConsumer;
        }

        @Override
        public void begin(@Nonnull BufferBuilder bufferBuilder, @Nonnull TextureManager textureManager) {
            RenderTarget mainTarget = Minecraft.getInstance().getMainRenderTarget();
            RenderTarget target = ShaderManager.getInstance().renderFullImageInFramebuffer(ShaderManager.getTempTarget(), Shaders.load(Shader.ShaderType.FRAGMENT, shader), null, shaderProgramConsumer);

            mainTarget.bindWrite(!ShaderManager.getInstance().hasViewPort());
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.depthMask(true);
            RenderSystem.setShader(Shaders::getParticleShader);
            RenderSystem.setShaderTexture(0, target.getColorTextureId());
            RenderSystem.enableCull();
            bufferBuilder.begin(VertexFormat.Mode.TRIANGLES, DefaultVertexFormat.PARTICLE);
        }

        @Override
        public void end(@Nonnull Tesselator tesselator) {
            tesselator.end();
            RenderSystem.depthMask(true);
        }
    }

}