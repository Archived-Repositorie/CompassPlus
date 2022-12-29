package io.github.ivymc.compassplus.compatibility;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import java.util.List;

public final class LevelZCompatibility {
    public static boolean canUse(PlayerEntity player) {
        List<Object> levelList = net.levelz.data.LevelLists.compassList;

        if (!net.levelz.stats.PlayerStatsManager.playerLevelisHighEnough(player, levelList, null, true)) {
            player.sendMessage(Text.translatable("item.levelz." + levelList.get(0) + ".tooltip", levelList.get(1)).formatted(Formatting.RED), true);
            return false;
        }

        return true;
    }
}
