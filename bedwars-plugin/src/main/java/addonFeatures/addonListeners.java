package addonFeatures;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent;
import com.andrei1058.bedwars.api.events.player.PlayerLevelUpEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class addonListeners implements Listener {

    @EventHandler
    public void onArenaJoin(PlayerJoinArenaEvent e){
        Bukkit.getServer().getScheduler().runTaskLater(BedWars.plugin, ()->{
            Player p = e.getPlayer();
            IArena a = e.getArena();
            if(Bukkit.getServer().getOnlinePlayers().contains(p)){
                if(a.getStatus()!= GameState.playing)
                    p.teleport(a.getWaitingLocation());
                else if(a.getWaitingLocation().getWorld()!=null
                        &&!(a.getWaitingLocation().getWorld().getName().equals(p.getWorld().getName())))
                    p.kickPlayer("§cERROR");

            }
        }, 10L);
    }

    @EventHandler
    public void onLevelUp(PlayerLevelUpEvent e) {
        for(String s : BedWars.config.getYml().getConfigurationSection("LevelUp-Commands").getKeys(false)){
            try{
                Integer.parseInt(s);
            }catch (Exception ex){
                continue;
            }
            if(e.getNewLevel()==Integer.parseInt(s)){
                for(String s1 : BedWars.config.getYml().getStringList("LevelUp-Commands."+s)){
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), s1);
                }
            }
        }
    }
}
