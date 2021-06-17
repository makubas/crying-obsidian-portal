package com.github.makubas.cryingobsidianportal;

import net.fabricmc.api.ModInitializer;
//import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;


public class CryingObsidianPortal implements ModInitializer {

	@Override
	public void onInitialize() {
		System.out.println("Corner rails is initializing!");
		//CustomPortalApiRegistry.addPortal(Blocks.CRYING_OBSIDIAN, new Identifier("the_nether"), 0, 0, 0);
	}
}
