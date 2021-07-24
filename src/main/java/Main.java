import me.jm.nordclient.NordClient;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "nordclient", version = "v1.0.0")
public class Main {

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	NordClient.instance = new NordClient();
    	NordClient.instance.init();
    }
}
