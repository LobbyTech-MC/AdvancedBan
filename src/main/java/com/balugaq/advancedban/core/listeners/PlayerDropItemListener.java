package com.balugaq.advancedban.core.listeners;

import com.balugaq.advancedban.api.enums.EventType;
import com.balugaq.advancedban.api.utils.Debug;
import com.balugaq.advancedban.api.utils.EventUtil;
import com.balugaq.advancedban.api.utils.Predications;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerDropItemListener implements Listener {
    public static final EventType TYPE = EventType.PLAYER_DROP_ITEM;

    public static boolean presetPredications(@NotNull PlayerDropItemEvent event, @NotNull EventPriority eventPriority) {
        return Predications.getPriority(EventUtil.getSlimefunId(event), TYPE) == eventPriority;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLowest(@NotNull PlayerDropItemEvent event) {
        if (presetPredications(event, EventPriority.LOWEST)) {
            Debug.debug("PlayerDropItemListener#onLowest(): Cancelled. 33");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLow(@NotNull PlayerDropItemEvent event) {
        if (presetPredications(event, EventPriority.LOW)) {
            Debug.debug("PlayerDropItemListener#onLow(): Cancelled. 34");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNormal(@NotNull PlayerDropItemEvent event) {
        if (presetPredications(event, EventPriority.NORMAL)) {
            Debug.debug("PlayerDropItemListener#onNormal(): Cancelled. 35");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHigh(@NotNull PlayerDropItemEvent event) {
        if (presetPredications(event, EventPriority.HIGH)) {
            Debug.debug("PlayerDropItemListener#onHigh(): Cancelled. 36");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHighest(@NotNull PlayerDropItemEvent event) {
        if (presetPredications(event, EventPriority.HIGHEST)) {
            Debug.debug("PlayerDropItemListener#onHighest(): Cancelled. 37");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMonitor(@NotNull PlayerDropItemEvent event) {
        if (presetPredications(event, EventPriority.MONITOR)) {
            Debug.debug("PlayerDropItemListener#onMonitor(): Cancelled. 38");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }
}
