package net.veroxuniverse.epicsamurai.client.armors.kimono;

import mod.azure.azurelib.renderer.GeoArmorRenderer;
import net.veroxuniverse.epicsamurai.item.armor.KimonoArmorItem;

public class KimonoArmorRenderer extends GeoArmorRenderer<KimonoArmorItem> {
    public KimonoArmorRenderer() {
        super(new KimonoArmorModel());

    }
}