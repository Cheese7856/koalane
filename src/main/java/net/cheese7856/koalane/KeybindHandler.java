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
        // Registrer keybind med standardtast K
        warpKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.koalane.warp",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                "key.category.koalane"
        ));

        // Lytter etter tastetrykk
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (warpKey.wasPressed() && client.player != null) {
                client.player.networkHandler.sendChatMessage("/warp Butikken");
            }
        });
    }
}