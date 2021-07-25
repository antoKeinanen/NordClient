package me.jm.nordclient.module.movement;

import me.jm.nordclient.NordClient;
import me.jm.nordclient.module.Category;
import me.jm.nordclient.module.Module;
import me.jm.nordclient.settings.Setting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Jesus extends Module {

    private int waterTickTimer = 0;
    private int lavaTickTimer = 0;

    public Jesus() {
        super("Jesus", "Walk on water like jesus", Category.MOVEMENT);
        NordClient.instance.settingsManager.rSetting(new Setting("Water", this, true));
        NordClient.instance.settingsManager.rSetting(new Setting("Lava", this, false));
    }

    @SubscribeEvent
    void onTick(TickEvent e){
        if(NordClient.instance.settingsManager.getSettingByName(this, "Water").getValBoolean()){
            if(mc.player.isInWater()){
                mc.player.addVelocity(0d, 0.01, 0d);
                waterTickTimer = 0;
                return;
            }
            if(waterTickTimer == 0) mc.player.addVelocity(0d, 0.1, 0d);
            else if(waterTickTimer == 1) mc.player.setVelocity(0d, 0d, 0d);
            waterTickTimer++;
        }
        if(NordClient.instance.settingsManager.getSettingByName(this, "Lava").getValBoolean()){
            if(mc.player.isInLava()){
                mc.player.addVelocity(0d, 0.01, 0d);
                lavaTickTimer = 0;
                return;
            }
            if(lavaTickTimer == 0) mc.player.addVelocity(0d, 0.1, 0d);
            else if(lavaTickTimer == 1) mc.player.setVelocity(0d, 0d, 0d);
            lavaTickTimer++;
        }


    }
}
