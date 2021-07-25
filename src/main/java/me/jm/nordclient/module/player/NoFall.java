package me.jm.nordclient.module.player;

import me.jm.nordclient.NordClient;
import me.jm.nordclient.module.Category;
import me.jm.nordclient.module.Module;
import me.jm.nordclient.settings.Setting;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

public class NoFall extends Module {

    public NoFall() {
        super("No Fall", "removes fall damage", Category.PLAYER);
        NordClient.instance.settingsManager.rSetting(new Setting("Mode", this, "Distance", new ArrayList<String>(){{
            add("Distance");
            add("Damage Multiplier");
            add("Cancel");
        }}));
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingFallEvent e) {
        String mode = NordClient.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        if(mode.equalsIgnoreCase("distance")){
            e.setDistance(0);
        }
        else if(mode.equalsIgnoreCase("damage multiplier")){
            e.setDamageMultiplier(0);
        }
        else  if(mode.equalsIgnoreCase("cancel")){
            e.setCanceled(true);
        }

    }
}
