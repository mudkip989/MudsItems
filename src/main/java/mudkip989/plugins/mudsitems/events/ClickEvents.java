package mudkip989.plugins.mudsitems.events;

import mudkip989.plugins.mudsitems.*;
import mudkip989.plugins.mudsitems.items.*;
import mudkip989.plugins.mudsitems.utils.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;
import org.bukkit.scheduler.*;

import java.util.logging.*;

import static mudkip989.plugins.mudsitems.MudsItems.log;

public class ClickEvents implements Listener {


    @EventHandler
    public void OnRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        ItemStack item = player.getInventory().getItemInMainHand();
        player.sendMessage(Dodgeball.IsItem(item).toString());
        if(Dodgeball.IsItem(item)){
            MudsItems.getInstance().dodgeballs.add(new Dodgeball(player.getEyeLocation(), player.getVelocity(), player.getUniqueId()));

        }


    }
}
