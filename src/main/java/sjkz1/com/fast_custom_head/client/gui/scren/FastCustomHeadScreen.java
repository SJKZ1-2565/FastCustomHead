package sjkz1.com.fast_custom_head.client.gui.scren;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

@Environment(EnvType.CLIENT)
public class FastCustomHeadScreen extends Screen {

    private final SkullBlockEntity skullBlockEntity;
    protected EditBox input;

    private String initName;

    public FastCustomHeadScreen(SkullBlockEntity skullBlockEntity) {
        super(Component.literal("Fast CustomHead"));
        this.skullBlockEntity = skullBlockEntity;
    }

    @Override
    protected void init() {
        assert this.minecraft != null;
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        this.addRenderableWidget(new Button(this.width / 2 - 100, this.height / 4 + 120, 200, 20, CommonComponents.GUI_DONE, button -> {
            this.onClose();
            Minecraft.getInstance().gui.getChat().addMessage(Component.literal("Rejoin world!").withStyle(ChatFormatting.RED));
        }));
        this.input = new EditBox(this.font, this.width / 2 - 100, 60, 200, 20, Component.literal("Skull Owner"));
        this.input.setValue(this.initName);
        this.input.setMaxLength(32500);
        this.input.setResponder((string) -> {
            this.initName = string;
            if (!StringUtils.isBlank(string)) {
                GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
                gameProfile.getProperties().put("textures", new Property("Value", string));
                skullBlockEntity.setOwner(gameProfile);
            }
        });
        this.addWidget(this.input);
        this.setInitialFocus(this.input);
    }

    @Override
    public void render(PoseStack poseStack, int i, int j, float f) {
        this.renderBackground(poseStack);
        FastCustomHeadScreen.drawCenteredString(poseStack, this.font, this.title, this.width / 2, 40, 0xFFFFFF);
        this.input.render(poseStack, i, j, f);
        super.render(poseStack, i, j, f);
    }
}
