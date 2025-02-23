package com.balugaq.advancedban.core.listeners;

import com.balugaq.advancedban.api.enums.EventType;
import com.balugaq.advancedban.api.utils.Debug;
import com.balugaq.advancedban.api.utils.EventUtil;
import com.balugaq.advancedban.api.utils.Predications;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;

public class BlockPlaceListener implements Listener {
    public static final EventType TYPE = EventType.BLOCK_PLACE;

    public static boolean presetPredications(@NotNull BlockPlaceEvent event, @NotNull EventPriority eventPriority) {
        String slimefunId = EventUtil.getSlimefunId(event);
        return !EventUtil.isBypass(slimefunId, TYPE, event.getPlayer()) && Predications.getPriority(slimefunId, TYPE) == eventPriority;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLowest(@NotNull BlockPlaceEvent event) {
        if (presetPredications(event, EventPriority.LOWEST)) {
            Debug.debug("BlockPlaceListener#onLowest(): Cancelled. 15");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLow(@NotNull BlockPlaceEvent event) {
        if (presetPredications(event, EventPriority.LOW)) {
            Debug.debug("BlockPlaceListener#onLow(): Cancelled. 16");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNormal(@NotNull BlockPlaceEvent event) {
        if (presetPredications(event, EventPriority.NORMAL)) {
            Debug.debug("BlockPlaceListener#onNormal(): Cancelled. 17");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHigh(@NotNull BlockPlaceEvent event) {
        if (presetPredications(event, EventPriority.HIGH)) {
            Debug.debug("BlockPlaceListener#onHigh(): Cancelled. 18");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHighest(@NotNull BlockPlaceEvent event) {
        if (presetPredications(event, EventPriority.HIGHEST)) {
            Debug.debug("BlockPlaceListener#onHighest(): Cancelled. 19");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMonitor(@NotNull BlockPlaceEvent event) {
        if (presetPredications(event, EventPriority.MONITOR)) {
            Debug.debug("BlockPlaceListener#onMonitor(): Cancelled. 20");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }
}
