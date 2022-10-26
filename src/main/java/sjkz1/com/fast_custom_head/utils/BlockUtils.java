package sjkz1.com.fast_custom_head.utils;

import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;

import java.util.Objects;

public class BlockUtils {

    public static void playerHead(Level level, BlockPos blockPos, String string) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity instanceof SkullBlockEntity skullBlockEntity) {
            GameProfile gameProfile = new GameProfile(null, Objects.equals(string, "") ? "MHF_GitHub" : string);
            skullBlockEntity.setOwner(gameProfile);
        }
    }
}
