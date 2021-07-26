package me.jm.nordclient.module.render;

import me.jm.nordclient.helpers.RenderHelper;
import me.jm.nordclient.module.Category;
import me.jm.nordclient.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class ESP extends Module {

    private transient List<Entity> ENTITIES = new ArrayList<Entity>();
    private transient int BOX = 0;


    public ESP() {
        super("Esp", "Allows you to see entities through walls.", Category.RENDER);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        BOX = GL11.glGenLists(1);
        GL11.glNewList(BOX, GL11.GL_COMPILE);
        RenderHelper.drawOutlinedBox(new AxisAlignedBB(-0.5, 0, -0.5, 0.5, 1, 0.5));
        GL11.glEndList();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        GL11.glDeleteLists(BOX, 1);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e){
        ENTITIES.clear();
        for (EntityPlayer entity : mc.world.playerEntities){
            if(entity.isDead || entity.getHealth() < 0) //Todo add invisible support and not render self
                continue;
            ENTITIES.add(entity);
        }
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent e){
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT | GL11.GL_COLOR_BUFFER_BIT | GL11.GL_LINE_BIT | GL11.GL_CURRENT_BIT);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glLineWidth(2);

        GL11.glPushMatrix();
        GL11.glTranslated(-mc.getRenderManager().renderViewEntity.posX,
                -mc.getRenderManager().renderViewEntity.posY,
                -mc.getRenderManager().renderViewEntity.posZ);

        RenderHelper.drawESPBoxes(ENTITIES, BOX, e.getPartialTicks());

        GL11.glPopMatrix();

        GL11.glPopAttrib();
    }
}
