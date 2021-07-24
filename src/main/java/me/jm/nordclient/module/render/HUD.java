package me.jm.nordclient.module.render;

import me.jm.nordclient.NordClient;
import me.jm.nordclient.helpers.ColorHelper;
import me.jm.nordclient.module.Category;
import me.jm.nordclient.module.Module;
import me.jm.nordclient.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class HUD extends Module {

	public HUD() {
		super("HUD", "modules on screen", Category.RENDER);
		NordClient.instance.settingsManager.rSetting(new Setting("Chroma", this, true));
		NordClient.instance.settingsManager.rSetting(new Setting("Color", this, "#FFFFFF"));

	}
	
	@SubscribeEvent
	public void onRender(RenderGameOverlayEvent egoe) {
		if (!egoe.getType().equals(egoe.getType().CROSSHAIRS))
			return;
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		int y = 2;
		for (Module mod : NordClient.instance.moduleManager.getModuleList()) {
			if (!mod.getName().equalsIgnoreCase("HUD") && mod.isToggled() && mod.visible) {
				FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
				fr.drawString(mod.getName(), sr.getScaledWidth() - fr.getStringWidth(mod.getName()) - 1, y,
						(NordClient.instance.settingsManager.getSettingByName(this, "Chroma").getValBoolean())
								? ColorHelper.chroma(y)
								: Color.white.getRGB());
				y += fr.FONT_HEIGHT;
			}
		}
	}
}
