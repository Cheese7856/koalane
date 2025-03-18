package net.cheese7856.koalane;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.minecraft.client.MinecraftClient;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;

@Environment(EnvType.CLIENT)
public class KoalaneTilButikkenClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// Registrer keybinding
		KeybindHandler.register();

		// Erstatt alle varianter av /warp ko, koa, koalane
		ClientSendMessageEvents.ALLOW_COMMAND.register(command -> {
			String normalizedCommand = command.trim().toLowerCase();

			if (normalizedCommand.matches("warp\\s+(k|ko|koa|koal|koala|koalan|koalane)")) {
				MinecraftClient client = MinecraftClient.getInstance();
				client.execute(() -> {
					if (client.player != null && client.player.networkHandler != null) {
						client.player.networkHandler.sendCommand("warp Butikken");
					}
				});
				return false; // Blokker original kommando
			}
			return true; // Tillat andre kommandoer
		});
	}
}