package com.lowdragmc.lowdraglib.client.utils;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderBufferUtils {

    public static void renderCubeFrame(PoseStack matrixStack, BufferBuilder buffer, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, float r, float g, float b, float a) {
        Matrix4f mat = matrixStack.last().pose();
        buffer.vertex(mat, minX, minY, minZ).color(r, g, b, a).normal(1,0,0).endVertex();
        buffer.vertex(mat, maxX, minY, minZ).color(r, g, b, a).normal(1,0,0).endVertex();

        buffer.vertex(mat, minX, minY, minZ).color(r, g, b, a).normal(0,1,0).endVertex();
        buffer.vertex(mat, minX, maxY, minZ).color(r, g, b, a).normal(0,1,0).endVertex();

        buffer.vertex(mat, minX, minY, minZ).color(r, g, b, a).normal(0,0,1).endVertex();
        buffer.vertex(mat, minX, minY, maxZ).color(r, g, b, a).normal(0,0,1).endVertex();

        buffer.vertex(mat, minX, maxY, maxZ).color(r, g, b, a).normal(1,0,0).endVertex();
        buffer.vertex(mat, maxX, maxY, maxZ).color(r, g, b, a).normal(1,0,0).endVertex();

        buffer.vertex(mat, maxX, minY, maxZ).color(r, g, b, a).normal(0,1,0).endVertex();
        buffer.vertex(mat, maxX, maxY, maxZ).color(r, g, b, a).normal(0,1,0).endVertex();

        buffer.vertex(mat, maxX, maxY, minZ).color(r, g, b, a).normal(0,0,1).endVertex();
        buffer.vertex(mat, maxX, maxY, maxZ).color(r, g, b, a).normal(0,0,1).endVertex();

        buffer.vertex(mat, minX, maxY, minZ).color(r, g, b, a).normal(0,0,1).endVertex();
        buffer.vertex(mat, minX, maxY, maxZ).color(r, g, b, a).normal(0,0,1).endVertex();

        buffer.vertex(mat, minX, maxY, minZ).color(r, g, b, a).normal(1,0,0).endVertex();
        buffer.vertex(mat, maxX, maxY, minZ).color(r, g, b, a).normal(1,0,0).endVertex();

        buffer.vertex(mat, maxX, minY, minZ).color(r, g, b, a).normal(0,0,1).endVertex();
        buffer.vertex(mat, maxX, minY, maxZ).color(r, g, b, a).normal(0,0,1).endVertex();

        buffer.vertex(mat, maxX, minY, minZ).color(r, g, b, a).normal(0,1,0).endVertex();
        buffer.vertex(mat, maxX, maxY, minZ).color(r, g, b, a).normal(0,1,0).endVertex();

        buffer.vertex(mat, minX, minY, maxZ).color(r, g, b, a).normal(1,0,0).endVertex();
        buffer.vertex(mat, maxX, minY, maxZ).color(r, g, b, a).normal(1,0,0).endVertex();

        buffer.vertex(mat, minX, minY, maxZ).color(r, g, b, a).normal(0,1,0).endVertex();
        buffer.vertex(mat, minX, maxY, maxZ).color(r, g, b, a).normal(0,1,0).endVertex();
    }

    public static void renderCubeFace(PoseStack matrixStack, BufferBuilder buffer, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, float red, float green, float blue, float a, boolean shade) {
        Matrix4f mat = matrixStack.last().pose();
        float r = red, g = green, b = blue;

        if (shade) {
            r *= 0.6;
            g *= 0.6;
            b *= 0.6;
        }
        buffer.vertex(mat, minX, minY, minZ).color(r, g, b, a).endVertex();
        buffer.vertex(mat, minX, minY, maxZ).color(r, g, b, a).endVertex();
        buffer.vertex(mat, minX, maxY, maxZ).color(r, g, b, a).endVertex();
        buffer.vertex(mat, minX, maxY, minZ).color(r, g, b, a).endVertex();

        buffer.vertex(mat, maxX, minY, minZ).color(r, g, b, a).endVertex();
        buffer.vertex(mat, maxX, maxY, minZ).color(r, g, b, a).endVertex();
        buffer.vertex(mat, maxX, maxY, maxZ).color(r, g, b, a).endVertex();
        buffer.vertex(mat, maxX, minY, maxZ).color(r, g, b, a).endVertex();

        if (shade) {
            r = red * 0.5f;
            g = green * 0.5f;
            b = blue * 0.5f;
        }
        buffer.vertex(mat, minX, minY, minZ).color(r, g, b, a).endVertex();
        buffer.vertex(mat, maxX, minY, minZ).color(r, g, b, a).endVertex();
        buffer.vertex(mat, maxX, minY, maxZ).color(r, g, b, a).endVertex();
        buffer.vertex(mat, minX, minY, maxZ).color(r, g, b, a).endVertex();

        if (shade) {
            r = red;
            g = green;
            b = blue;
        }
        buffer.vertex(mat, minX, maxY, minZ).color(r, g, b, a).endVertex();
        buffer.vertex(mat, minX, maxY, maxZ).color(r, g, b, a).endVertex();
        buffer.vertex(mat, maxX, maxY, maxZ).color(r, g, b, a).endVertex();
        buffer.vertex(mat, maxX, maxY, minZ).color(r, g, b, a).endVertex();

        if (shade) {
            r = red * 0.8f;
            g = green * 0.8f;
            b = blue * 0.8f;
        }
        buffer.vertex(mat, minX, minY, minZ).color(r, g, b, a).endVertex();
        buffer.vertex(mat, minX, maxY, minZ).color(r, g, b, a).endVertex();
        buffer.vertex(mat, maxX, maxY, minZ).color(r, g, b, a).endVertex();
        buffer.vertex(mat, maxX, minY, minZ).color(r, g, b, a).endVertex();

        buffer.vertex(mat, minX, minY, maxZ).color(r, g, b, a).endVertex();
        buffer.vertex(mat, maxX, minY, maxZ).color(r, g, b, a).endVertex();
        buffer.vertex(mat, maxX, maxY, maxZ).color(r, g, b, a).endVertex();
        buffer.vertex(mat, minX, maxY, maxZ).color(r, g, b, a).endVertex();
    }

}
