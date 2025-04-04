package com.kwesou.hardwareSpoof.client.mixins;

import com.mojang.blaze3d.platform.GLX;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.kwesou.hardwareSpoof.client.HardwareSpoofClient.pickedCPU;

// no, this doesnt work
@Mixin(GLX.class)
public class ChangeCPUInfo {

    //@Inject(method = "_getCpuInfo", at = @At("RETURN"), cancellable = true)
    private static void modifyCPUGL(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue(pickedCPU);
    }
}
