package io.github.ivymc.compassplus.mixin;

import io.github.ivymc.compassplus.Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.command.GameRuleCommand;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "updatePositionAndAngles", at = @At("HEAD"))
    private void updatePositionAndAngles(double x, double y, double z, float yaw, float pitch, CallbackInfo ci) {
        Main.g.LOGGER.info(String.valueOf(Main.CONFIG.enabled));
        if(!Main.CONFIG.enabled) return;

        if(!((Entity) (Object) this instanceof ServerPlayerEntity player)) return;
        if(!(player.getMainHandStack().isOf(Items.COMPASS) || player.getOffHandStack().isOf(Items.COMPASS))) return;

        float degreee = MathHelper.wrapDegrees(player.getHeadYaw());
        String name = "";
        if (degreee > -22.5 && degreee <= 22.5)
            name = "SOUTH";
        else if (degreee > 22.5 && degreee <= 67.5)
            name = "SOUTH WEST";
        else if (degreee > 67.5 && degreee <= 112.5)
            name = "WEST";
        else if (degreee > 112.5 && degreee <= 157.5)
            name = "NORTH WEST";
        else if (degreee > 157.5 || degreee <= -157.5)
            name = "NORTH";
        else if (degreee > -157.5 && degreee <= -112.5)
            name = "NORTH EAST";
        else if (degreee > -112.5 && degreee <= -67.5)
            name = "EAST";
        else if (degreee > -67.5 && degreee <= -22.5)
            name = "SOUTH EAST";
        var server = player.getServer();

        if(!server.getGameRules().getBoolean(GameRules.REDUCED_DEBUG_INFO) && Main.CONFIG.reduceDebug) {
            var rule = server.getGameRules().get(GameRules.REDUCED_DEBUG_INFO);
            rule.set(true, server);
        }
        int[] pos = {player.getBlockPos().getX(), player.getBlockPos().getY(), player.getBlockPos().getZ()};
        Main.g.LOGGER.info(String.valueOf(pos));
        player.sendMessageToClient(Text.of(String.format("X:%d Y:%d Z:%d %s", pos[0], pos[1], pos[2], name)), true);
    }
}
