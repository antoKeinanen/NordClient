package me.jm.nordclient.module.movement;

import me.jm.nordclient.NordClient;
import me.jm.nordclient.module.Category;
import me.jm.nordclient.module.Module;
import me.jm.nordclient.settings.Setting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import org.lwjgl.input.Keyboard;

import java.util.OptionalInt;


public class AirJump extends Module {

    public AirJump() {
        super("Air Jump", "Allows you to jump in the air. Tip: use no fall.", Category.MOVEMENT);
        NordClient.instance.settingsManager.rSetting(new Setting("Maintain Level", this, false));
    }

    private int level;

    @Override
    public void onEnable(){
        MinecraftForge.EVENT_BUS.register(this);
        level = mc.player.getPosition().getY();
    }

    @SubscribeEvent
    void onKey(KeyInputEvent e){
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
            level = mc.player.getPosition().getY();
            mc.player.jump();
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
            level--;
        }
    }

    @SubscribeEvent
    void onTick(TickEvent e){
        if (NordClient.instance.settingsManager.getSettingByName(this, "Maintain Level").getValBoolean()){
            int pLevel = mc.player.getPosition().getY();
            if(pLevel < level){
                mc.player.jump();
            }
        }
    }
}
