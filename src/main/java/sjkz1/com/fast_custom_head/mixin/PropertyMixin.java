package sjkz1.com.fast_custom_head.mixin;

import com.mojang.authlib.properties.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Property.class)
public interface PropertyMixin {

    @Accessor(value = "value")
    void setValue(String value);
}
