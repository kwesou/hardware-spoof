package com.kwesou.hardwarespoof;

import com.kwesou.hardwarespoof.config.cpuList;
import com.kwesou.hardwarespoof.config.gpuDrivers;
import com.kwesou.hardwarespoof.config.gpuList;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.Random;

public class HardwareSpoofClient implements ClientModInitializer {

	private static final List<String> cpus = cpuList.listofCPUs;
	private static final List<String> gpus = gpuList.listofGPUs;
	private static final List<String> pullednvidiaDrivers = gpuDrivers.nvidiaDrivers;
	private static final List<String> pulledamdDrivers = gpuDrivers.amdDrivers;
	private static final List<String> pulledintelDrivers = gpuDrivers.intelDrivers;
	private static final List<String> pulledmesaDrivers = gpuDrivers.mesaDrivers;
	private static int f3Counter = 0;

	public static String pickedCPU = cpus.get(new Random().nextInt(cpus.size()));
	public static String pickedGPU = gpus.get(new Random().nextInt(gpus.size()));
	public static String pickedGPUVendor = "null";
	public static String pickedGPUDriver = "null";

	private static final KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.examplemod.spook", // The translation key of the keybinding's name
			InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
			GLFW.GLFW_KEY_F3, // The keycode of the key
			"category.examplemod.test" // The translation key of the keybinding's category.
	));

	@Override
	public void onInitializeClient() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (keyBinding.wasPressed()) {
				f3Counter++;
				if (f3Counter % 2 != 0) {
					changeHardware();
				}
			}
		});
	}

	public static void changeHardware() {
		Random rand = new Random();
		pickedCPU = cpus.get(rand.nextInt(cpus.size()));
		pickedGPU = gpus.get(rand.nextInt(gpus.size()));

		if (pickedGPU.contains("Intel")) {
			pickedGPUVendor = "Intel";
			pickedGPUDriver = Math.random() < 0.5 ? "3.2.0" + " " + pulledintelDrivers.get(new Random().nextInt(pulledintelDrivers.size()))
					: "4.6.0" + " " + pulledintelDrivers.get(new Random().nextInt(pulledintelDrivers.size()));
		} else if (pickedGPU.contains("GeForce")) {
			pickedGPUVendor = "NVIDIA Corporation";
			pickedGPUDriver = Math.random() < 0.5 ? "3.2.0" + " " + pullednvidiaDrivers.get(new java.util.Random().nextInt(pullednvidiaDrivers.size()))
					: "4.6.0" + " " + pullednvidiaDrivers.get(new java.util.Random().nextInt(pullednvidiaDrivers.size()));
		} else if (pickedGPU.contains("AMD")) {
			pickedGPUVendor = "ATI Technologies Inc.";
			pickedGPUDriver = pulledamdDrivers.get(rand.nextInt(pulledamdDrivers.size()));
		} else {
			pickedGPUVendor = "null";
			pickedGPUDriver = Math.random() < 0.5 ? "3.2" + " " + pulledmesaDrivers.get(new Random().nextInt(pulledmesaDrivers.size()))
					: "4.6" + " " + pulledmesaDrivers.get(new Random().nextInt(pulledmesaDrivers.size()));
		}
	}
}