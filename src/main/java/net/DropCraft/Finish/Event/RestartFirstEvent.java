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
    private String kickMessage;


    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public RestartFirstEvent(int timer, Plugin plugin,String kickMessage) {
        this.kickMessage = kickMessage;
        this.timer = timer;

        Bukkit.broadcastMessage("Le serveur se fermera dans :" + timer*20);
         BungeeChannelApi api = BungeeChannelApi.of(plugin);

        schedulers.scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                if(getKickMessage().isEmpty()){
                    setkickMessage("Serveur Close");
                }
                for(Player player : plugin.getServer().getOnlinePlayers()){
                    new RestartFinalEvent(player,getKickMessage(),api,plugin);
                }
            }
        },timer* 20L);
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public String getKickMessage() {
        return kickMessage;
    }

    public void setkickMessage(String kickMessage) {
        this.kickMessage = kickMessage;
    }

}
