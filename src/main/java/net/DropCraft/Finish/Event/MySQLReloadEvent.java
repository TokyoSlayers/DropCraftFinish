package net.DropCraft.Finish.Event;

import net.DropCraft.Finish.Utils.MySQL;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class MySQLReloadEvent extends Event {

    private int counter;

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public MySQLReloadEvent(int iReload,String host,String database,int port,String user,String password) {
        int reload = iReload*20;
        new BukkitRunnable() {
            @Override
            public void run() {
                counter++;

                if (counter == reload) {
                    if (!MySQL.isConnected()) {
                        MySQL.disconnect();
                        MySQL.connect(host, database, port, user, password);
                        System.out.println("MySQL ckeck REDEMARER !");
                    } else {
                        System.out.println("MySQL check OK !");
                    }
                    counter = 0;
                }
            }
        };
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }
}
