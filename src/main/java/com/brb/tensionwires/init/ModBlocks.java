package com.brb.tensionwires.init;

import com.brb.tensionwires.BrbMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public final class ModBlocks {
    public static Block example;

    private ModBlocks() {}

    public static void registerAll(RegistryEvent.Register<Block> event) {
        // Workaround for Forge event bus bug
        if (!event.getName().equals(ForgeRegistries.BLOCKS.getRegistryName())) return;

        // Register your blocks here
        example = register("example_block", new Block(Block.Properties
                .create(Material.ROCK)
                .hardnessAndResistance(4, 20)
        ));
    }

    private static <T extends Block> T register(String name, T block) {
        BlockItem item = new BlockItem(block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS));
        return register(name, block, item);
    }

    private static <T extends Block> T register(String name, T block, @Nullable BlockItem item) {
        ResourceLocation id = BrbMod.getId(name);
        block.setRegistryName(id);
        ForgeRegistries.BLOCKS.register(block);

        if (item != null) {
            ModItems.BLOCKS_TO_REGISTER.put(name, item);
        }

        return block;
    }
}
