package mudkip989.plugins.mudsitems;

import mudkip989.plugins.mudsitems.items.*;

public class Updater {


    public static void Dodgeballs(){
        if(MudsItems.getInstance().dodgeballs.size()>0){
            for (Dodgeball ball: MudsItems.getInstance().dodgeballs) {
                ball.Tick();
            }
        }
    }
}
