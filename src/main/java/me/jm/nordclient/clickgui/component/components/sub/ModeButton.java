package me.jm.nordclient.clickgui.component.components.sub;

import me.jm.nordclient.clickgui.component.Component;
import me.jm.nordclient.clickgui.component.components.Button;
import me.jm.nordclient.helpers.ColorHelper;
import me.jm.nordclient.module.Module;
import me.jm.nordclient.settings.Setting;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class ModeButton extends Component {

	private boolean hovered;
	private Button parent;
	private Setting set;
	private int offset;
	private int x;
	private int y;
	private Module mod;

	private int modeIndex;
	
	public ModeButton(Setting set, Button button, Module mod, int offset) {
		this.set = set;
		this.parent = button;
		this.mod = mod;
		this.x = button.parent.getX() + button.parent.getWidth();
		this.y = button.parent.getY() + button.offset;
		this.offset = offset;
		this.modeIndex = 0;
	}
	
	@Override
	public void setOff(int newOff) {
		offset = newOff;
	}
	
	@Override
	public void renderComponent() {
		//bgr
		Gui.drawRect(parent.parent.getX() + 2, parent.parent.getY() + offset, parent.parent.getX() + (parent.parent.getWidth() * 1), parent.parent.getY() + offset + 12, ColorHelper.ToOpacityMode(ColorHelper.BGR02));
		//little side thing
		Gui.drawRect(parent.parent.getX(), parent.parent.getY() + offset, parent.parent.getX() + 2, parent.parent.getY() + offset + 12, ColorHelper.ToOpacityMode(ColorHelper.BGR02));
		GL11.glPushMatrix();
		GL11.glScalef(0.5f,0.5f, 0.5f);
		//text
		Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("Mode: " + set.getValString(), (parent.parent.getX() + 7) * 2, (parent.parent.getY() + offset + 2) * 2 + 5, ColorHelper.WHITE02);
		GL11.glPopMatrix();
	}
	
	@Override
	public void updateComponent(int mouseX, int mouseY) {
		this.hovered = isMouseOnButton(mouseX, mouseY);
		this.y = parent.parent.getY() + offset;
		this.x = parent.parent.getX();
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
		if(isMouseOnButton(mouseX, mouseY) && button == 0 && this.parent.open) {
			int maxIndex = set.getOptions().size();

			if(modeIndex >= maxIndex - 1)
				modeIndex = 0;
			else
				modeIndex++;

			set.setValString(set.getOptions().get(modeIndex));
		}
	}
	
	public boolean isMouseOnButton(int x, int y) {
		if(x > this.x && x < this.x + 88 && y > this.y && y < this.y + 12) {
			return true;
		}
		return false;
	}
}
