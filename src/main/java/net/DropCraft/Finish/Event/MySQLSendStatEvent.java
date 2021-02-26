package net.DropCraft.Finish.Event;

import net.DropCraft.Finish.Utils.MySQL;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLSendStatEvent  extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public MySQLSendStatEvent(String state, String serverName) {
        if(!getStatement(serverName).equals(state)){
            setStatement(state,serverName);
        }
    }

    public void setStatement(String state, String serverName){
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("UPDATE `DropCraftServ_State` SET `Stat`='" + state + "' WHERE `ServerName`='"+ serverName +"'");
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getStatement(String serverName){
        String dif = "null";
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT `Stat` FROM `DropCraftServ_State` WHERE `ServerName`='" + serverName + "'");
            ResultSet rs = sts.executeQuery();
            if (rs.next()){
                dif = rs.getString("Stat");
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
