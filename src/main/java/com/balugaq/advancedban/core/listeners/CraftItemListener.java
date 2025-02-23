package com.balugaq.advancedban.core.listeners;

import com.balugaq.advancedban.api.enums.EventType;
import com.balugaq.advancedban.api.utils.Debug;
import com.balugaq.advancedban.api.utils.EventUtil;
import com.balugaq.advancedban.api.utils.Predications;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.jetbrains.annotations.NotNull;

public class CraftItemListener implements Listener {
    public static final EventType TYPE = EventType.CRAFT_ITEM;

    public static boolean presetPredications(@NotNull CraftItemEvent event, @NotNull EventPriority eventPriority) {
        String slimefunId = EventUtil.getSlimefunId(event);
        return !EventUtil.isBypass(slimefunId, TYPE, event.getWhoClicked()) && Predications.getPriority(slimefunId, TYPE) == eventPriority;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLowest(@NotNull CraftItemEvent event) {
        if (presetPredications(event, EventPriority.LOWEST)) {
            Debug.debug("CraftItemListener#onLowest(): Cancelled. 21");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLow(@NotNull CraftItemEvent event) {
        if (presetPredications(event, EventPriority.LOW)) {
            Debug.debug("CraftItemListener#onLow(): Cancelled. 22");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNormal(@NotNull CraftItemEvent event) {
        if (presetPredications(event, EventPriority.NORMAL)) {
            Debug.debug("CraftItemListener#onNormal(): Cancelled. 23");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHigh(@NotNull CraftItemEvent event) {
        if (presetPredications(event, EventPriority.HIGH)) {
            Debug.debug("CraftItemListener#onHigh(): Cancelled. 24");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHighest(@NotNull CraftItemEvent event) {
        if (presetPredications(event, EventPriority.HIGHEST)) {
            Debug.debug("CraftItemListener#onHighest(): Cancelled. 25");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMonitor(@NotNull CraftItemEvent event) {
        if (presetPredications(event, EventPriority.MONITOR)) {
            Debug.debug("CraftItemListener#onMonitor(): Cancelled. 26");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }
}
