package addonFeatures;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Containers.CMIUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class addonMethods {

    public static void disableFly(Player p){
        if(Bukkit.getPluginManager().getPlugin("CMI")!=null){
            CMIUser user = CMI.getInstance().getPlayerManager().getUser(p);
            user.setAllowFlight(false);
            user.setFlying(false);
        }
    }
}
