package net.cheese7856.koalane;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class KoalaneTilButikken implements ClientModInitializer {

	private static KeyBinding warpKey;

	@Override
	public void onInitializeClient() {
		// Eksisterende kommandoerstattelse
		ClientSendMessageEvents.ALLOW_COMMAND.register(command -> {
			if (command.trim().equalsIgnoreCase("warp koalane")) {
				MinecraftClient client = MinecraftClient.getInstance();
				client.execute(() -> {
					if (client.player != null && client.player.networkHandler != null) {
						client.player.networkHandler.sendCommand("warp Butikken");
					}
				});
				return true;
			}
			return false;
		});

		// Ny keybind-funksjonalitet
		warpKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.koalane.warp",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_P,
				"key.category.koalane"
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (warpKey.wasPressed()) {
				if (client.player != null && client.player.networkHandler != null) {
					client.player.networkHandler.sendCommand("warp Butikken");
				}
			}
		});
	}
}