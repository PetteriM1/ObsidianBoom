package opobb;

import cn.nukkit.entity.item.EntityPrimedTNT;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityExplosionPrimeEvent;
import cn.nukkit.plugin.PluginBase;

import java.util.HashMap;
import java.util.Map;

public class Main extends PluginBase implements Listener {

    static Map<Integer, Double> resistance;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        resistance = getConfig().get("resistance", new HashMap<>());
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onBigBoom(EntityExplosionPrimeEvent e) {
        if (e.getEntity() instanceof EntityPrimedTNT) {
            e.setCancelled(true);
            BigExplosion explosion = new BigExplosion(e.getEntity(), 4, e.getEntity());
            if (explosion.explodeA()) {
                explosion.explodeB();
            }
        }
    }
}
