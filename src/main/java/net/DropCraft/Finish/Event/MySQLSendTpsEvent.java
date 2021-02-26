package net.DropCraft.Finish.Event;

import net.DropCraft.Finish.Utils.MySQL;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLSendTpsEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public MySQLSendTpsEvent(int tps, String serverName){
        if(getTps(serverName) != tps){
            setTps(tps,serverName);
        }
    }

    public void setTps(int tps, String serverName){
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("UPDATE `DropCraftServ_State` SET `Tps`='" + tps + "' WHERE `ServerName`='"+ serverName +"'");
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getTps(String serverName){
        int dif = 0;
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT `Tps` FROM `DropCraftServ_State` WHERE `ServerName`='" + serverName + "'");
            ResultSet rs = sts.executeQuery();
            if (rs.next()){
                dif = rs.getInt("Tps");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dif;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

}
