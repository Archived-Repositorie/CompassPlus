package io.github.ivymc.compassplus.mixin;

import io.github.ivymc.compassplus.Configs;
import io.github.ivymc.compassplus.player.Data;
import io.github.ivymc.compassplus.player.Helper;
import net.fabricmc.loader.api.FabricLoader;
import net.levelz.data.LevelLists;
import net.levelz.stats.PlayerStatsManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CompassItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

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
            List<Object> levelList = LevelLists.customItemList;
            if (!PlayerStatsManager.playerLevelisHighEnough(player, levelList, "minecraft:compass", true)) {
                player.sendMessage(new TranslatableText("item.levelz." + levelList.get(0) + ".tooltip", levelList.get(1)).formatted(Formatting.RED), true);
                return false;
            }
        }
        return true;
    }
}
