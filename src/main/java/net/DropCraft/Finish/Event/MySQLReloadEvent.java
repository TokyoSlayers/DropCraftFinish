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

    public MySQLReloadEvent(int iReload) {
        int reload = iReload*20;
        new BukkitRunnable() {
            @Override
            public void run() {
                counter++;

                if (counter == reload) {
                    if (!MySQL.isConnected()) {
                        MySQL.disconnect();
                        MySQL.connect("91.234.195.40", "c1500257c_dropcraft-serv", 3306, "c1500257c_plugin", ";HPyt@fVBT47");
                        System.out.println("MySQL ckeck REDEMARER !");
                    } else {
                        System.out.println("MySQL check OK !");
                    }
                    counter = 0;
                }
            }
        };
    }

    public MySQLReloadEvent() {
        if (!MySQL.isConnected()) {
            MySQL.disconnect();
            MySQL.connect("91.234.195.40", "c1500257c_dropcraft-serv", 3306, "c1500257c_plugin", ";HPyt@fVBT47");
            System.out.println("MySQL ckeck REDEMARER !");
        } else {
            System.out.println("MySQL check OK !");
        }
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }
}
