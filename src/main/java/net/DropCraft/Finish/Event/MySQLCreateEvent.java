package net.DropCraft.Finish.Event;

import net.DropCraft.Finish.Utils.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLCreateEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public MySQLCreateEvent(String state, String serverName, int tps) {
        createTable();
        createStatement(state,serverName,tps);
    }

    public void createTable(){
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `DropCraftServ_State` (ServerName VARCHAR(255),Stat VARCHAR(255),NumberPlayers int(11),Tps int(11))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createStatement(String state, String serverName,int tps){
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT `Stat` FROM `DropCraftServ_State` WHERE `ServerName`='" + serverName + "'");
            ResultSet rs = sts.executeQuery();
            if(!rs.next()) {
                sts.close();
                sts = MySQL.getConnection().prepareStatement("INSERT INTO `DropCraftServ_State` (ServerName, Stat,NumberPlayers,Tps) VALUES ('" + serverName + "','" + state + "','" + 0 + "','"+ tps +"')");
                sts.executeUpdate();
                sts.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }
}
