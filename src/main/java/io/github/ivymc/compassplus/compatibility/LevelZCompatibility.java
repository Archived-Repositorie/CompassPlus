package io.github.ivymc.compassplus.compatibility;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import java.util.List;

public final class LevelZCompatibility {
    public static boolean canUse(PlayerEntity player) {
        List<Object> levelList = net.levelz.data.LevelLists.customItemList;

        if (!net.levelz.stats.PlayerStatsManager.playerLevelisHighEnough(player, levelList, "minecraft:compass", true)) {
            player.sendMessage(new TranslatableText("item.levelz." + levelList.get(levelList.indexOf("minecraft:compass") + 1) + ".tooltip", levelList.get(levelList.indexOf("minecraft:compass") + 2)).formatted(Formatting.RED), true);
            return false;
        }

        return true;
    }
}
