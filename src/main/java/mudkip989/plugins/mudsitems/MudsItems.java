package mudkip989.plugins.mudsitems;

import mudkip989.plugins.mudsitems.events.*;
import mudkip989.plugins.mudsitems.items.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.*;

import java.util.*;
import java.util.logging.*;

public final class MudsItems extends JavaPlugin {

    private static MudsItems instance;
    public static Long day;
    public static UUID DodgeballUUID;
    public static Logger log = Logger.getLogger("MudsItems");
    public static ItemStack DodgeballItem;
    public World world;
    public List<Dodgeball> dodgeballs = new ArrayList<>();


    @Override
    public void onEnable() {
        instance = this;
        FileManager file = new FileManager(MudsItems.getInstance(), "data.yml");
        String uid = file.getConfig().getString("ballId");
        if(uid == null || uid == "null"){
            DodgeballUUID = UUID.randomUUID();
            file.getConfig().set("ballId", DodgeballUUID.toString());
            file.saveConfig();
        }else{
            DodgeballUUID = UUID.fromString(uid);
        }

        System.out.println("Triggering console lol.");
        log.info("Not triggering Console anymore.");
        DodgeballItem = Dodgeball.registerDodgeball();
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ClickEvents(), this);
        world = Bukkit.getWorld("world");
        new BukkitRunnable() {

            @Override
            public void run() {
                Updater.Dodgeballs();
            }
        }.runTaskTimer(MudsItems.getInstance(), 1, 1);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static MudsItems getInstance() {
        return instance;
    }

    public static void Info(String text){
        log.info(text);

    }



}
