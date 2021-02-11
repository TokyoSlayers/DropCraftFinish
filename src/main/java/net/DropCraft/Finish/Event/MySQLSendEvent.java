package net.DropCraft.Finish.Event;

import net.DropCraft.Finish.Utils.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLSendEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public MySQLSendEvent(String state, String serverName) {
        Bukkit.getPluginManager().callEvent(new MySQLReloadEvent());
        createTable();
        if(getStatement(serverName).isEmpty()){
            createStatement(state,serverName);
        }else{
            setStatement(state,serverName);
        }
    }

    public void createTable(){
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `DropCraftServ_State` (ServerName VARCHAR(255),Stat VARCHAR(255))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createStatement(String state, String serverName){
        try {
            PreparedStatement sts = MySQL.getConnection().prepareStatement("SELECT `Stat` FROM `DropCraftServ_State` WHERE `ServerName`='" + serverName + "'");
            ResultSet rs = sts.executeQuery();
            if(!rs.next()) {
                sts.close();
                sts = MySQL.getConnection().prepareStatement("INSERT INTO `DropCraftServ_State` (ServerName, Stat) VALUES ('" + serverName + "','" + state + "')");
                sts.executeUpdate();
                sts.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
