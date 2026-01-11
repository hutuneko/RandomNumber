package com.hutuneko.randomnumber.mixin;

import com.hutuneko.randomnumber.NumberMaps;
import net.minecraft.client.gui.Font;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.*;


@Mixin(Font.class)
public class MixinFont {

    @ModifyVariable(
            method = "drawInternal(Ljava/lang/String;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/gui/Font$DisplayMode;IIZ)I",
            at = @At("HEAD"),
            argsOnly = true
    )
    private String scrambleDrawInternal(String text) {
        if (text == null) return null;

        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(NumberMaps.getNumberMap().getOrDefault(c, c));
        }
        return sb.toString();
    }
    @ModifyVariable(
            method = "drawInternal(Lnet/minecraft/util/FormattedCharSequence;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/gui/Font$DisplayMode;II)I",
            at = @At("HEAD"),
            argsOnly = true
    )
    private FormattedCharSequence randomNunber$scrambleInternalSequence(FormattedCharSequence original) {
        if (original == null) return null;
        return (visitor) -> original.accept((index, style, codePoint) -> {
            int newCodePoint = NumberMaps.getNumberMap2().getOrDefault(codePoint, codePoint);
            return visitor.accept(index, style, newCodePoint);
        });
    }
}