package net.DropCraft.Finish.Event;

import io.github.leonardosnt.bungeechannelapi.BungeeChannelApi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class RestartFinalEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public RestartFinalEvent(Player player, String kickMessage, BungeeChannelApi api, Plugin plugin) {
        player.sendMessage(kickMessage);
        api.connect(player,"Lobby");
        Bukkit.getPluginManager().callEvent(new DeleteWorldEvent(plugin));
        plugin.getServer().shutdown();
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }
}
