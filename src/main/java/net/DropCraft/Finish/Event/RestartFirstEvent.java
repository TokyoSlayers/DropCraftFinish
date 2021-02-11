package net.DropCraft.Finish.Event;

import io.github.leonardosnt.bungeechannelapi.BungeeChannelApi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

public class RestartFirstEvent extends Event {

    private int timer;
    private BukkitScheduler schedulers;

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public RestartFirstEvent(int timer, Plugin plugin) {
        this.timer = timer;

        Bukkit.broadcastMessage("Le serveur se fermera dans :" + timer);
         BungeeChannelApi api = BungeeChannelApi.of(plugin);

        schedulers.scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                for(Player player : plugin.getServer().getOnlinePlayers()){
                    new RestartFinalEvent(player,"Serveur close",api,plugin);
                }
            }
        },timer);
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }
}
