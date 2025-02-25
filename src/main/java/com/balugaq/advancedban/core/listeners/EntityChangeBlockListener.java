package com.balugaq.advancedban.core.listeners;

import com.balugaq.advancedban.api.enums.EventType;
import com.balugaq.advancedban.api.utils.Debug;
import com.balugaq.advancedban.api.utils.EventUtil;
import com.balugaq.advancedban.api.utils.Predications;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.jetbrains.annotations.NotNull;

public class EntityChangeBlockListener implements Listener {
    public static final EventType TYPE = EventType.ENTITY_CHANGE_BLOCK;

    public static boolean presetPredications(@NotNull EntityChangeBlockEvent event, @NotNull EventPriority eventPriority) {
        String slimefunId = EventUtil.getSlimefunId(event);
        return Predications.getPriority(slimefunId, TYPE) == eventPriority;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLowest(@NotNull EntityChangeBlockEvent event) {
        if (presetPredications(event, EventPriority.LOWEST)) {
            Debug.debug("EntityChangeBlockListener#onLowest(): Cancelled. 165");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLow(@NotNull EntityChangeBlockEvent event) {
        if (presetPredications(event, EventPriority.LOW)) {
            Debug.debug("EntityChangeBlockListener#onLow(): Cancelled. 166");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNormal(@NotNull EntityChangeBlockEvent event) {
        if (presetPredications(event, EventPriority.NORMAL)) {
            Debug.debug("EntityChangeBlockListener#onNormal(): Cancelled. 167");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHigh(@NotNull EntityChangeBlockEvent event) {
        if (presetPredications(event, EventPriority.HIGH)) {
            Debug.debug("EntityChangeBlockListener#onHigh(): Cancelled. 168");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHighest(@NotNull EntityChangeBlockEvent event) {
        if (presetPredications(event, EventPriority.HIGHEST)) {
            Debug.debug("EntityChangeBlockListener#onHighest(): Cancelled. 169");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMonitor(@NotNull EntityChangeBlockEvent event) {
        if (presetPredications(event, EventPriority.MONITOR)) {
            Debug.debug("EntityChangeBlockListener#onMonitor(): Cancelled. 164");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }
}
