/*
 * Copyright (C) 2017-2019  C4
 *
 * This file is part of Comforts, a mod made for Minecraft.
 *
 * Comforts is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Comforts is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Comforts.  If not, see <https://www.gnu.org/licenses/>.
 */

package top.theillusivec4.comforts;

import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.quetzi.morpheus.Morpheus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.comforts.client.EventHandlerClient;
import top.theillusivec4.comforts.client.renderer.TileEntityHammockRenderer;
import top.theillusivec4.comforts.client.renderer.TileEntitySleepingBagRenderer;
import top.theillusivec4.comforts.common.ComfortsConfig;
import top.theillusivec4.comforts.common.EventHandlerCommon;
import top.theillusivec4.comforts.common.block.BlockHammock;
import top.theillusivec4.comforts.common.block.BlockRopeAndNail;
import top.theillusivec4.comforts.common.block.BlockSleepingBag;
import top.theillusivec4.comforts.common.capability.CapabilitySleepData;
import top.theillusivec4.comforts.common.init.ComfortsBlocks;
import top.theillusivec4.comforts.common.init.ComfortsTileEntities;
import top.theillusivec4.comforts.common.item.ItemComfortsBase;
import top.theillusivec4.comforts.common.item.ItemHammock;
import top.theillusivec4.comforts.common.item.ItemSleepingBag;
import top.theillusivec4.comforts.common.tileentity.TileEntityHammock;
import top.theillusivec4.comforts.common.tileentity.TileEntitySleepingBag;
import top.theillusivec4.comforts.integration.morpheus.MorpheusDayHandler;

import java.util.Arrays;

@Mod(Comforts.MODID)
public class Comforts {

    public static final String MODID = "comforts";

    public static final ItemGroup CREATIVE_TAB = new ItemGroup(-1, "comforts") {
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(ComfortsBlocks.SLEEPING_BAGS.get(EnumDyeColor.RED));
        }
    };

    public static final Logger LOGGER = LogManager.getLogger();

    public Comforts() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::setup);
        eventBus.addListener(this::setupClient);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ComfortsConfig.serverSpec);
    }

    private void setup(FMLCommonSetupEvent evt) {
        MinecraftForge.EVENT_BUS.register(new EventHandlerCommon());
        CapabilitySleepData.register();

        if (ModList.get().isLoaded("morpheus")) {
            Morpheus.register.registerHandler(new MorpheusDayHandler(), 0);
        }
    }

    private void setupClient(FMLClientSetupEvent evt) {
        MinecraftForge.EVENT_BUS.register(new EventHandlerClient());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySleepingBag.class, new TileEntitySleepingBagRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHammock.class, new TileEntityHammockRenderer());
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> evt) {
            IForgeRegistry<Block> registry = evt.getRegistry();
            Arrays.stream(EnumDyeColor.values()).forEach(color -> {
                BlockSleepingBag sleepingBag = new BlockSleepingBag(color);
                ComfortsBlocks.SLEEPING_BAGS.put(color, sleepingBag);
                BlockHammock hammock = new BlockHammock(color);
                ComfortsBlocks.HAMMOCKS.put(color, hammock);
                registry.registerAll(sleepingBag, hammock);
            });
            registry.register(new BlockRopeAndNail());
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> evt) {
            IForgeRegistry<Item> registry = evt.getRegistry();
            ComfortsBlocks.SLEEPING_BAGS.values().forEach(block -> registry.register(new ItemSleepingBag(block)));
            ComfortsBlocks.HAMMOCKS.values().forEach(block -> registry.register(new ItemHammock(block)));
            registry.register(new ItemComfortsBase(ComfortsBlocks.ROPE_AND_NAIL));
        }

        @SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> evt) {
            evt.getRegistry().registerAll(ComfortsTileEntities.SLEEPING_BAG_TE, ComfortsTileEntities.HAMMOCK_TE);
        }
    }
}