package jahimaz.timedfly;

import jahimaz.timedfly.commands.FlyCommand;
import jahimaz.timedfly.events.FallDamageNegation;
import org.bukkit.plugin.java.JavaPlugin;

public final class TimedFly extends JavaPlugin {

    @Override
    public void onEnable() {
        loadCommands();
        loadListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadCommands(){
        getCommand("fly").setExecutor(new FlyCommand(this));
    }

    private void loadListeners(){
        new FallDamageNegation(this);
    }
}
