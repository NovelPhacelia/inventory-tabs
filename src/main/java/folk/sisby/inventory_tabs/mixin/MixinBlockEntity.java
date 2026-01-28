package folk.sisby.inventory_tabs.mixin;

import com.mojang.serialization.JsonOps;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryOps;
import net.minecraft.text.Text;
import net.minecraft.text.TextCodecs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntity.class)
public class MixinBlockEntity {
    @Inject(method = "toInitialChunkDataNbt", at = @At("RETURN"))
    public void sendCustomNames(CallbackInfoReturnable<NbtCompound> cir) {
        if (((BlockEntity) (Object) this) instanceof LockableContainerBlockEntity lcbe) {
            Text name = lcbe.getCustomName();
            if (name != null) {
                var ops = RegistryOps.of(JsonOps.INSTANCE, DynamicRegistryManager.EMPTY);
                String json = TextCodecs.CODEC.encodeStart(ops, name)
                    .getOrThrow()
                    .toString();

                cir.getReturnValue().putString("CustomName", json);
            }
        }
    }
}
//Rewrote Lockable Containers