package io.github.ivymc.compassplus.mixin;

import io.github.ivymc.compassplus.Configs;
import io.github.ivymc.compassplus.compatibility.LevelZCompatibility;
import io.github.ivymc.compassplus.player.Data;
import io.github.ivymc.compassplus.player.Helper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CompassItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CompassItem.class)
public abstract class CompassItemMixin extends Item {

    public CompassItemMixin(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!Configs.compassConfig.data.rightClick) return TypedActionResult.pass(user.getStackInHand(hand));
        if(!canUse(user)) return TypedActionResult.fail(user.getStackInHand(hand));
        if(user instanceof ServerPlayerEntity player) {
            if(Helper.of(player).getData().rightClick) return TypedActionResult.pass(user.getStackInHand(hand));
            Data data = new Data();
            data.rightClick = true;
            Helper.of(player).writeData(data);
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }
    public boolean canUse(PlayerEntity player) {
        if(FabricLoader.getInstance().isModLoaded("levelz")) {
            if (!LevelZCompatibility.canUse(player)) {
                return false;
            }
        }

        return true;
    }
}
