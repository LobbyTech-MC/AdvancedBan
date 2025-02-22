package com.balugaq.advancedban.core.listeners;

import com.balugaq.advancedban.api.Debug;
import com.balugaq.advancedban.api.EventUtil;
import com.balugaq.advancedban.api.Predications;
import com.balugaq.advancedban.api.events.EventType;
import com.balugaq.advancedban.api.events.FakeCraftItemEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.jetbrains.annotations.NotNull;

public class CraftItemListener implements Listener {
    public static final EventType TYPE = EventType.CRAFT_ITEM;

    public static boolean presetPredications(@NotNull FakeCraftItemEvent event, @NotNull EventPriority eventPriority) {
        return Predications.getPriority(event.getSlimefunId(), TYPE) == eventPriority;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void fakeEvent(@NotNull CraftItemEvent event) {
        if (!EventUtil.isFakeEvent(event)) {
            Debug.debug("CraftItemListener#fakeEvent(): New a fake event. 3");
            FakeCraftItemEvent e = new FakeCraftItemEvent(event);
            Bukkit.getPluginManager().callEvent(e);
            if (e.isCancelled()) {
                event.setCancelled(true);
                String id = e.getSlimefunId();
                if (id == null) {
                    return;
                }
                SlimefunItem item = SlimefunItem.getById(id);
                if (item == null) {
                    return;
                }
                event.getView().getPlayer().sendMessage(EventUtil.color(TYPE.getMessage(), "name", item.getItemName()));
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLowest(@NotNull FakeCraftItemEvent event) {
        if (presetPredications(event, EventPriority.LOWEST)) {
            Debug.debug("CraftItemListener#onLowest(): Cancelled. 21");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLow(@NotNull FakeCraftItemEvent event) {
        if (presetPredications(event, EventPriority.LOW)) {
            Debug.debug("CraftItemListener#onLow(): Cancelled. 22");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNormal(@NotNull FakeCraftItemEvent event) {
        if (presetPredications(event, EventPriority.NORMAL)) {
            Debug.debug("CraftItemListener#onNormal(): Cancelled. 23");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHigh(@NotNull FakeCraftItemEvent event) {
        if (presetPredications(event, EventPriority.HIGH)) {
            Debug.debug("CraftItemListener#onHigh(): Cancelled. 24");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHighest(@NotNull FakeCraftItemEvent event) {
        if (presetPredications(event, EventPriority.HIGHEST)) {
            Debug.debug("CraftItemListener#onHighest(): Cancelled. 25");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMonitor(@NotNull FakeCraftItemEvent event) {
        if (presetPredications(event, EventPriority.MONITOR)) {
            Debug.debug("CraftItemListener#onMonitor(): Cancelled. 26");
            event.setCancelled(true);
        }
    }
}
