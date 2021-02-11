package net.DropCraft.Finish.Event;

import net.DropCraft.Finish.Utils.CopyFile;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class DeleteWorldEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public DeleteWorldEvent(Plugin plugin){
        for(World world : plugin.getServer().getWorlds()) {
            String worldName = "null";
            if(world.getEnvironment() == World.Environment.NORMAL && !world.getName().equals("WorldCopy")) {
                worldName = world.getName();
                world.getWorldFolder().delete();
                world.getWorldFolder().deleteOnExit();
            }else if(world.getEnvironment() == World.Environment.NORMAL && world.getName().equals("WorldCopy")){
                CopyFile.copyWorld(world, worldName);
                World loadWorld = plugin.getServer().getWorld(worldName);
                CopyFile.loadWorld(loadWorld);
                world.getWorldFolder().delete();
                world.getWorldFolder().deleteOnExit();
            }
        }
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }
}
