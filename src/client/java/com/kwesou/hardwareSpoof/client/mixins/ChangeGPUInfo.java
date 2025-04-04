package com.kwesou.hardwareSpoof.client.mixins;

import net.minecraft.client.gl.GlBackend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.kwesou.hardwareSpoof.client.HardwareSpoofClient.*;

@Mixin(GlBackend.class)
public class ChangeGPUInfo {

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
