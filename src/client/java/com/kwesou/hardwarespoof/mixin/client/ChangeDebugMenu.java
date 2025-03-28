package com.kwesou.hardwarespoof.mixin.client;

import com.mojang.blaze3d.platform.GlDebugInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.kwesou.hardwarespoof.HardwareSpoofClient.*;

@Mixin(value = GlDebugInfo.class)
public class ChangeDebugMenu {

    @Inject(method = "getCpuInfo", at = @At("RETURN"), cancellable = true)
    private static void modifyCPUGL(CallbackInfoReturnable<String> cir) {
            cir.setReturnValue(pickedCPU);
    }

    @Inject(method = "getRenderer", at = @At("RETURN"), cancellable = true)
    private static void modifyGPUGL(CallbackInfoReturnable<String> cir) {
            cir.setReturnValue(pickedGPU);
    }

    @Inject(method = "getVersion", at = @At("RETURN"), cancellable = true)
    private static void modifyDriverGL(CallbackInfoReturnable<String> cir) {
            cir.setReturnValue(pickedGPUDriver);
    }

    @Inject(method = "getVendor", at = @At("RETURN"), cancellable = true)
    private static void modifyVendorGL(CallbackInfoReturnable<String> cir) {
            cir.setReturnValue(pickedGPUVendor);
    }
}
