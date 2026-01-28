package folk.sisby.inventory_tabs.util;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

public class HandlerSlotUtil {
    public static int stashSlot = -1;
    public static int mainHandSwapSlot = -1;

    //Rewrote Item Mover
    public static void push(
            ClientPlayerEntity player,
            ClientPlayerInteractionManager manager,
            ScreenHandler handler,
            boolean doClient
    ) {
        if (!handler.getCursorStack().isEmpty()) {
            stashSlot = player.getInventory().getEmptySlot();
            if (stashSlot != -1) {
                handler.getSlotIndex(player.getInventory(), stashSlot).ifPresent((screenSlot) -> {
                    manager.clickSlot(
                            handler.syncId,
                            screenSlot,
                            0,
                            SlotActionType.PICKUP,
                            player
                    );
                });
            }
        }
    }


    public static void tryPop(ClientPlayerEntity player, ClientPlayerInteractionManager manager, ScreenHandler handler) {
        if (stashSlot != -1) {
            handler.getSlotIndex(player.getInventory(), stashSlot).ifPresent((screenSlot) -> manager.clickSlot(
                    handler.syncId,
                    screenSlot,
                    0, // Mouse Left Click
                    SlotActionType.PICKUP,
                    player
            ));
            stashSlot = -1;
        }
        if (mainHandSwapSlot != -1) {
            handler.getSlotIndex(player.getInventory(), mainHandSwapSlot).ifPresent((screenSlot) -> manager.clickSlot(
                    handler.syncId,
                    screenSlot,
                    player.getInventory().getSelectedSlot(),
                    SlotActionType.SWAP,
                    player
            ));
            mainHandSwapSlot = -1;
        }
    }
}
