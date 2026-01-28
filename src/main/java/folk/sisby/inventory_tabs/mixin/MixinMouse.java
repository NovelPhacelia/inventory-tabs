package folk.sisby.inventory_tabs.mixin;

import folk.sisby.inventory_tabs.TabManager;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MixinMouse {
    @Inject(method = "unlockCursor", at = @At("HEAD"), cancellable = true)
    public void keepCursorWhenChangingTabs(CallbackInfo ci) {
        if (TabManager.nextTab != null) {
            ci.cancel();
        }
    }
}
