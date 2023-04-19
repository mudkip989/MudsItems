package mudkip989.plugins.mudsitems.utils;

import com.destroystokyo.paper.profile.PlayerProfile;
import mudkip989.plugins.mudsitems.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.profile.*;

import java.lang.reflect.*;
import java.net.*;
import java.util.*;

public class ItemFuncs {
    public static ItemStack skull(String url) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        PlayerProfile prof = Bukkit.createProfile(MudsItems.DodgeballUUID);
        PlayerTextures text = prof.getTextures();
        try {
            text.setSkin(new URL(url));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        prof.setTextures(text);
        skullMeta.setPlayerProfile(prof);


        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }
}
