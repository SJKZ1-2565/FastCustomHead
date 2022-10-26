package sjkz1.com.fast_custom_head;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import sjkz1.com.fast_custom_head.client.gui.scren.FastCustomHeadScreen;

public class FastCustomHead implements ModInitializer {

    @Override
    public void onInitialize() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            assert world != null;
            if ((world.getBlockState(hitResult.getBlockPos()).is(Blocks.PLAYER_HEAD) || (world.getBlockState(hitResult.getBlockPos()).is(Blocks.PLAYER_WALL_HEAD))) && player.getItemInHand(hand).getItem() instanceof DyeItem) {
                Minecraft.getInstance().setScreen(new FastCustomHeadScreen((SkullBlockEntity) world.getBlockEntity(hitResult.getBlockPos())));
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        });
    }
}
