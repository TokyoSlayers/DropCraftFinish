package net.DropCraft.Finish.Event;

import net.DropCraft.Finish.Utils.MySQL;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLSendPlayerSizeEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public MySQLSendPlayerSizeEvent(int playerSize, String serverName) {
        if(getNumberPlayer(serverName) != playerSize){
            setNumberPlayer(playerSize,serverName);
        }
    }

    public void setNumberPlayer(int playerSize, String serverName){
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("UPDATE `DropCraftServ_State` SET `NumberPlayers`='" + playerSize + "' WHERE `ServerName`='"+ serverName +"'");
            sts.executeUpdate();
            sts.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getNumberPlayer(String serverName){
        int dif = 0;
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT `NumberPlayers` FROM `DropCraftServ_State` WHERE `ServerName`='" + serverName + "'");
            ResultSet rs = sts.executeQuery();
            if (rs.next()){
                dif = rs.getInt("NumberPlayers");
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
