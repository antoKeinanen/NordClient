package me.jm.nordclient.module.render;

import me.jm.nordclient.NordClient;
import me.jm.nordclient.module.Category;
import me.jm.nordclient.module.Module;
import org.lwjgl.input.Keyboard;

public class ClickGUI extends Module {

	public ClickGUI() {
		super("ClickGUI", "menu.skeet", Category.RENDER);
		this.setKey(Keyboard.KEY_RSHIFT);
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		mc.displayGuiScreen(NordClient.instance.clickGui);
		this.setToggled(false);
	}
}
