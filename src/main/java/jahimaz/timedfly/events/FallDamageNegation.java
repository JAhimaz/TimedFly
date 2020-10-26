package jahimaz.timedfly.events;

import jahimaz.timedfly.TimedFly;
import jahimaz.timedfly.commands.FlyCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.EventListener;

public class FallDamageNegation implements Listener {

    TimedFly plugin;

    public FallDamageNegation(TimedFly instance){
        this.plugin = instance;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerFalling(EntityDamageEvent e){
        Player player = (Player) e.getEntity();
        if (e.getCause() == EntityDamageEvent.DamageCause.FALL){
            if(FlyCommand.fallingPlayers.contains(player)){
                e.setCancelled(true);
                FlyCommand.fallingPlayers.remove(player);
                return;
            }
        }else{
            return;
        }
    }

}
