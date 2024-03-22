package net.veroxuniverse.epicsamurai.client.custom_armors.ninja_armor.diamond;

import net.minecraft.resources.ResourceLocation;
import net.veroxuniverse.epicsamurai.EpicSamuraiMod;
import net.veroxuniverse.epicsamurai.item.armor.DiamondNinjaArmorItem;
import software.bernie.geckolib.model.GeoModel;

public class DiamondNinjaArmorModel extends GeoModel<DiamondNinjaArmorItem> {
    @Override
    public ResourceLocation getModelResource(DiamondNinjaArmorItem object) {
        return new ResourceLocation(EpicSamuraiMod.MOD_ID, "geo/ninja_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DiamondNinjaArmorItem object) {
        return new ResourceLocation(EpicSamuraiMod.MOD_ID, "textures/armor/ninja_diamond_armor_textures.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DiamondNinjaArmorItem animatable) {
        return null;
        //return new ResourceLocation(EpicSamuraiMod.MOD_ID, "animations/samurai_armor_animation.json");
    }

}