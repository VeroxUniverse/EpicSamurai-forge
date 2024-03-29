package net.veroxuniverse.epicsamurai;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.veroxuniverse.epicsamurai.client.entities.*;
import net.veroxuniverse.epicsamurai.client.projectiles.ThrownShurikenRenderer;
import net.veroxuniverse.epicsamurai.compat.*;
import net.veroxuniverse.epicsamurai.curios.layers.KitsuneMaskRenderer;
import net.veroxuniverse.epicsamurai.curios.layers.OniMaskRedRenderer;
import net.veroxuniverse.epicsamurai.curios.layers.OniMaskRenderer;
import net.veroxuniverse.epicsamurai.curios.layers.OniMaskWhiteRenderer;
import net.veroxuniverse.epicsamurai.datagen.loot.ModLootModifiers;
import net.veroxuniverse.epicsamurai.enchantment.ModEnchantments;
import net.veroxuniverse.epicsamurai.entity.ModEntityTypes;
import net.veroxuniverse.epicsamurai.item.armor.BrassSamuraiArmorItem;
import net.veroxuniverse.epicsamurai.registry.BlocksRegistry;
import net.veroxuniverse.epicsamurai.registry.CreativeTabRegistry;
import net.veroxuniverse.epicsamurai.registry.ItemsRegistry;
import net.veroxuniverse.epicsamurai.registry.ParticlesInit;
import net.veroxuniverse.epicsamurai.sound.ESSounds;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import static com.simibubi.create.content.equipment.goggles.GogglesItem.addIsWearingPredicate;


@Mod(EpicSamuraiMod.MOD_ID)
public class EpicSamuraiMod
{
    public static final String MOD_ID = "epicsamurai";
    public static final Logger LOGGER = LogUtils.getLogger();

    public EpicSamuraiMod()
    {


        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLootModifiers.register(modEventBus);
        ModEnchantments.register(modEventBus);

        ModEntityTypes.register(modEventBus);
        if(ModList.get().isLoaded("ars_nouveau")) {
            GeckoLib.initialize();
        }
        //MixinExtrasBootstrap.init();

        ItemsRegistry.register(modEventBus);
        BlocksRegistry.register(modEventBus);
        ParticlesInit.register(modEventBus);

        CreativeTabRegistry.register(modEventBus);


        if(ModList.get().isLoaded("deeperdarker")) {
            DeeperDarkerCompat.register(modEventBus);
        }
        if(ModList.get().isLoaded("create")) {
            CreateCompat.register(modEventBus);
        }
        if(ModList.get().isLoaded("ars_nouveau")) {
            ArsNouveauCompat.register(modEventBus);
        }
        if(ModList.get().isLoaded("aquaculture")) {
            AquacultureCompat.register(modEventBus);
        }
        if(ModList.get().isLoaded("eldritch_end")) {
            EldritchEndCompat.register(modEventBus);
        }
        if(ModList.get().isLoaded("bloodmagic")) {
            BloodMagicCompat.register(modEventBus);
        }
        if(ModList.get().isLoaded("forbidden_arcanus")) {
            ForbiddenCompat.register(modEventBus);
        }

        ESSounds.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {


        if(ModList.get().isLoaded("ars_nouveau")) {
            ArsNouveauCompat.registerPerkProviders();
        }

        if(ModList.get().isLoaded("create")) {
            addIsWearingPredicate((player) ->
                    player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof BrassSamuraiArmorItem);
        }

        //ModMessages.register();

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntityTypes.AKANAME.get(), AkanameRenderer::new);
            EntityRenderers.register(ModEntityTypes.ENENRA.get(), EnenraRenderer::new);
            EntityRenderers.register(ModEntityTypes.ONI.get(), OniRenderer::new);
            EntityRenderers.register(ModEntityTypes.ONIBI.get(), OnibiRenderer::new);
            EntityRenderers.register(ModEntityTypes.KITSUNE.get(), KitsuneRenderer::new);
            EntityRenderers.register(ModEntityTypes.JOROGUMO.get(), JorogumoRenderer::new);
            EntityRenderers.register(ModEntityTypes.FUJIN.get(), FujinRenderer::new);
            EntityRenderers.register(ModEntityTypes.RAIJIN.get(), RaijinRenderer::new);
            EntityRenderers.register(ModEntityTypes.KOMAINU.get(), KomainuRenderer::new);
            EntityRenderers.register(ModEntityTypes.TANUKI.get(), TanukiRenderer::new);
            EntityRenderers.register(ModEntityTypes.KAWAUSO.get(), KawausoRenderer::new);
            EntityRenderers.register(ModEntityTypes.TWOTAILED.get(), TwoTailedRenderer::new);
            EntityRenderers.register(ModEntityTypes.SHURIKEN.get(), ThrownShurikenRenderer::new);

            EntityRenderers.register(ModEntityTypes.KITSUNE_PROJECTILE.get(), KitsuneProjectileRenderer::new);
            EntityRenderers.register(ModEntityTypes.BULLET.get(), BulletRenderer::new);
            EntityRenderers.register(ModEntityTypes.KUNAI.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntityTypes.KUNAI_NETHERITE.get(), ThrownItemRenderer::new);

            CuriosRendererRegistry.register(ItemsRegistry.ONI_MASK.get(), OniMaskRenderer::new);
            //CuriosRendererRegistry.register(ItemsRegistry.ONI_MASK_RED.get(), OniMaskRedRenderer::new);
            //CuriosRendererRegistry.register(ItemsRegistry.ONI_MASK_WHITE.get(), OniMaskWhiteRenderer::new);
            CuriosRendererRegistry.register(ItemsRegistry.KITSUNE_MASK.get(), KitsuneMaskRenderer::new);

        }
    }

}
