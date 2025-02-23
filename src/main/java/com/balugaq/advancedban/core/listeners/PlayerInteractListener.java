package com.balugaq.advancedban.core.listeners;

import com.balugaq.advancedban.api.enums.EventType;
import com.balugaq.advancedban.api.utils.Debug;
import com.balugaq.advancedban.api.utils.EventUtil;
import com.balugaq.advancedban.api.utils.Predications;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerInteractListener implements Listener {
    public static final EventType TYPE = EventType.PLAYER_INTERACT;

    public static boolean presetPredications(@NotNull PlayerInteractEvent event, @NotNull EventPriority eventPriority) {
        String slimefunId = EventUtil.getSlimefunId(event);
        return !EventUtil.isBypass(slimefunId, TYPE, event.getPlayer()) && Predications.getPriority(slimefunId, TYPE) == eventPriority;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLowest(@NotNull PlayerInteractEvent event) {
        if (presetPredications(event, EventPriority.LOWEST)) {
            Debug.debug("PlayerInteractListener#onLowest(): Cancelled. 39");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLow(@NotNull PlayerInteractEvent event) {
        if (presetPredications(event, EventPriority.LOW)) {
            Debug.debug("PlayerInteractListener#onLow(): Cancelled. 40");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNormal(@NotNull PlayerInteractEvent event) {
        if (presetPredications(event, EventPriority.NORMAL)) {
            Debug.debug("PlayerInteractListener#onNormal(): Cancelled. 41");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHigh(@NotNull PlayerInteractEvent event) {
        if (presetPredications(event, EventPriority.HIGH)) {
            Debug.debug("PlayerInteractListener#onHigh(): Cancelled. 42");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHighest(@NotNull PlayerInteractEvent event) {
        if (presetPredications(event, EventPriority.HIGHEST)) {
            Debug.debug("PlayerInteractListener#onHighest(): Cancelled. 43");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMonitor(@NotNull PlayerInteractEvent event) {
        if (presetPredications(event, EventPriority.MONITOR)) {
            Debug.debug("PlayerInteractListener#onMonitor(): Cancelled. 44");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }
}
