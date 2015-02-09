package com.SinfulPixel.DeathBan;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Min3 on 2/8/2015.
 */
public class DeathBan extends JavaPlugin {
    public static HashMap<String, Long> banned = new HashMap<String, Long>();
    public static String Path = "plugins/DeathBan" + File.separator + "BanList.dat";
    public DBListener Listener = new DBListener(this);
    public OnDeath odl = new OnDeath(this);
    public Server server;


    public void onEnable(){
        server = this.getServer();
        server.getPluginManager().registerEvents(Listener, this);
        server.getPluginManager().registerEvents(odl, this);
        File file = new File(Path);
        new File("plugins/DeathBan").mkdir();
        if(file.exists()){ banned = load();}
        if(banned == null){banned = new HashMap<String, Long>();}
        this.getCommand("unban").setExecutor(new BanCmd(this));
        checkHash();
    }

    public void onDisable(){
        save();
    }
    public static void checkHash(){
        for(String s:banned.keySet()){
            String k = s;
            String v = banned.get(s).toString();
            System.out.println(k+"|"+v);
        }
    }
    public static void save(){
        File file = new File("plugins/DeathBan" + File.separator + "BanList.dat");
        new File("plugins/DeathBan").mkdir();
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Path));
            oos.writeObject(banned);
            oos.flush();
            oos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    public static HashMap<String, Long> load(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Path));
            Object result = ois.readObject();
            ois.close();
            return (HashMap<String,Long>)result;
        }catch(Exception e){
            return null;
        }
    }
}
