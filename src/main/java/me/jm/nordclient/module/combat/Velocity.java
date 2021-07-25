package me.jm.nordclient.module.combat;

import me.jm.nordclient.NordClient;
import me.jm.nordclient.module.Category;
import me.jm.nordclient.module.Module;
import me.jm.nordclient.settings.Setting;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


import java.util.ArrayList;
import java.util.List;


public class Velocity extends Module {

	public Velocity() {
		super("Anti Knockback", "i hate being knocked back", Category.COMBAT);
		NordClient.instance.settingsManager.rSetting(new Setting("Horizontal", this, 90, 0, 100, true));
		NordClient.instance.settingsManager.rSetting(new Setting("Vertical", this, 100, 0, 100, true));
		NordClient.instance.settingsManager.rSetting(new Setting("Mode", this, "test1", new ArrayList<String>(){{
			add("test1");
			add("test2");
			add("test3");
		}}
		));
	}
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent e) {
		float horizontal = (float) NordClient.instance.settingsManager.getSettingByName(this, "Horizontal").getValDouble();
		float vertical = (float) NordClient.instance.settingsManager.getSettingByName(this, "Vertical").getValDouble();
		
		if (mc.player.hurtTime == mc.player.maxHurtTime && mc.player.maxHurtTime > 0) {
			mc.player.motionX *= (float) horizontal / 100;
			mc.player.motionY *= (float) vertical / 100;
			mc.player.motionZ *= (float) horizontal / 100;
		}
	}
}
