package mudkip989.plugins.mudsitems.items;

import mudkip989.plugins.mudsitems.*;
import mudkip989.plugins.mudsitems.utils.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.metadata.*;
import org.bukkit.util.*;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.function.*;

import static mudkip989.plugins.mudsitems.MudsItems.*;

public class Dodgeball {




    public Location location;
    public Vector velocity;
    public UUID uuid;
    public ArmorStand entity;
    public UUID thrower;
    public Boolean throwerInclusion = true;
    public Integer ticks = 0;

    public List<Entity> noCollide = new ArrayList<>();


    public static ItemStack registerDodgeball(){
        ItemStack item = ItemFuncs.skull("http://textures.minecraft.net/texture/7a2df315b43583b1896231b77bae1a507dbd7e43ad86c1cfbe3b2b8ef3430e9e");
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Dodgeball");
        item.setItemMeta(meta);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(MudsItems.getInstance(), "mudsitems"), item);
        recipe.shape("***", "*#*", "***");
        recipe.setIngredient('*', Material.SLIME_BALL);
        recipe.setIngredient('#', Material.SAND);
        Bukkit.addRecipe(recipe);
        return item;
    }

    public static Boolean IsItem(ItemStack item){
        if (item.asOne().isSimilar(DodgeballItem)){
            return true;
        }


        return false;
    }


    public Dodgeball(Location loc, Vector vel, UUID puid){
        location = loc;
        velocity = vel;
        thrower = puid;
        try {
            entity = Spawn(location);
            uuid = entity.getUniqueId();

        }catch (Exception e){
            log.severe(e.toString());
        }
        noCollide.add(entity);
        noCollide.add(Bukkit.getPlayer(thrower));


    }

    public ArmorStand Spawn(Location loc){

        ArmorStand ar = (ArmorStand) MudsItems.getInstance().world.spawnEntity(loc, EntityType.ARMOR_STAND, CreatureSpawnEvent.SpawnReason.CUSTOM);

        ar.setGravity(false);
        EntityEquipment eq = ar.getEquipment();
        eq.setHelmet(DodgeballItem);

        return ar;
    }



    public void Tick() {

        Predicate<Entity> filt = x -> (x.getLocation().distance(location) < 1 && noCollide.contains(x));
        RayTraceResult ray = getInstance().world.rayTrace(location, velocity, velocity.length(), FluidCollisionMode.NEVER, false, 0.25, filt);
        location = location.add(velocity);
        entity.teleportAsync(location);
        if (throwerInclusion && !isCollidingWith(Bukkit.getPlayer(thrower))){
            if(noCollide.contains(Bukkit.getPlayer(thrower))){
                noCollide.remove(Bukkit.getPlayer(thrower));
            }
        }
    }

    public boolean isCollidingWith(Entity e){
        BoundingBox bound1 = entity.getBoundingBox();
        BoundingBox bound2 = e.getBoundingBox();


        return bound1.overlaps(bound2);
    }

}
