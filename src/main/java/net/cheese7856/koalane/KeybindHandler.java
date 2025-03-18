package net.cheese7856.koalane;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import org.lwjgl.glfw.GLFW;

public class KeybindHandler {
    private static KeyBinding warpKey;

    public static void register() {
        warpKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.koalane.warp",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_K, // Tasten "K"
                "key.category.koalane"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (warpKey.wasPressed() && client.player != null) {
                // Bruk sendCommand i stedet for sendChatMessage
                client.player.networkHandler.sendCommand("warp Butikken");
            }
        });
    }
}