package mudkip989.plugins.mudsitems.utils;

import org.bukkit.util.*;
import org.jetbrains.annotations.*;

public class VectorFunc {
    public static @NotNull Vector reflect(Vector vec, Vector normal){
        Double dot = vec.dot(normal);
        Vector r = vec.subtract(normal.multiply(dot*2));

        return r;
    }
}
