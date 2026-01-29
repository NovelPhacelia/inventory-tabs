package folk.sisby.inventory_tabs.mixin;

import folk.sisby.inventory_tabs.TabManager;
import folk.sisby.inventory_tabs.ScreenSupport;
import net.minecraft.client.gui.screen.Screen;
import folk.sisby.inventory_tabs.duck.InventoryTabsScreen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.RecipeBookScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeBookScreen.class)
public abstract class MixinRecipeBookScreen {
    @Unique Boolean inventoryTabs$allowTabs = true;

    // @Inject(method = "<init>", at = @At("TAIL"))
    // private void checkSupported(ScreenHandler handler, PlayerInventory inventory, Text title, CallbackInfo ci) {
    //     System.out.println("[InventoryTabs DEBUG] MixinHandledScreen checkSupported called: " + this.getClass().getName());
    //     inventoryTabs$allowTabs = ScreenSupport.allowTabs(this);
    // }

    @Inject(method = "render", at = @At("TAIL"))
    protected void render(DrawContext drawContext, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (!inventoryTabs$allowTabs) return;
        TabManager.render(drawContext, mouseX, mouseY);
    }

    // @Inject(method = "isClickOutsideBounds", at = @At("RETURN"), cancellable = true)
    // protected void isClickOutsideBounds(double mouseX, double mouseY, int left, int top, CallbackInfoReturnable<Boolean> cir) {
    //     System.out.println("[InventoryTabs DEBUG] MixinHandledScreen isClickOutsideBounds called: " + this.getClass().getName());
    //     if (inventoryTabs$allowTabs && cir.getReturnValue()) {
    //         cir.setReturnValue(TabManager.isClickOutsideBounds(mouseX, mouseY));
    //     }
    // }

}