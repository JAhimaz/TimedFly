package jahimaz.timedfly.commands;

import jahimaz.timedfly.TimedFly;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

import static jahimaz.timedfly.utils.Utils.ChatMessage;

public class FlyCommand implements CommandExecutor {

    private ArrayList<Player> flyingPlayers = new ArrayList<>();
    public static ArrayList<Player> fallingPlayers = new ArrayList<>();

    private TimedFly plugin;

    public FlyCommand(TimedFly instance){
        this.plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("timedfly.donator")){
                setFlyState(player, "Donator");
            }else if(player.hasPermission("timedfly.member")){
                setFlyState(player, "Member");
            }else{
                player.sendMessage(ChatColor.RED + "You Do Not Have Access To This Command!");
            }
        }
        return true;
    }

    private void setFlyState(Player player, String playerType){
        if(flyingPlayers.contains(player)){
            flyingPlayers.remove(player);
            player.setFlying(false);
            player.setAllowFlight(false);
            fallingPlayers.add(player);
            player.sendMessage(ChatMessage("&aYou Have Disabled" + playerType + "Fly"));
        }else{
            flyingPlayers.add(player);
            player.setAllowFlight(true);
            player.setFlying(true);
            player.sendMessage(ChatMessage("&aYou Have Enabled" + playerType + "Fly"));
        }
    }
}
