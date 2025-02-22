package com.balugaq.advancedban.core.listeners;

import com.balugaq.advancedban.api.enums.EventType;
import com.balugaq.advancedban.api.utils.Debug;
import com.balugaq.advancedban.api.utils.EventUtil;
import com.balugaq.advancedban.api.utils.Predications;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class BlockBreakListener implements Listener {
    public static final EventType TYPE = EventType.BLOCK_BREAK;

    public static boolean presetPredications(@NotNull BlockBreakEvent event, @NotNull EventPriority eventPriority) {
        return Predications.getPriority(EventUtil.getSlimefunId(event), TYPE) == eventPriority;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLowest(@NotNull BlockBreakEvent event) {
        if (presetPredications(event, EventPriority.LOWEST)) {
            Debug.debug("BlockBreakListener#onLowest(): Cancelled. 9");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLow(@NotNull BlockBreakEvent event) {
        if (presetPredications(event, EventPriority.LOW)) {
            Debug.debug("BlockBreakListener#onLow(): Cancelled. 10");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNormal(@NotNull BlockBreakEvent event) {
        if (presetPredications(event, EventPriority.NORMAL)) {
            Debug.debug("BlockBreakListener#onNormal(): Cancelled. 11");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHigh(@NotNull BlockBreakEvent event) {
        if (presetPredications(event, EventPriority.HIGH)) {
            Debug.debug("BlockBreakListener#onHigh(): Cancelled. 12");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHighest(@NotNull BlockBreakEvent event) {
        if (presetPredications(event, EventPriority.HIGHEST)) {
            Debug.debug("BlockBreakListener#onHighest(): Cancelled. 13");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMonitor(@NotNull BlockBreakEvent event) {
        if (presetPredications(event, EventPriority.MONITOR)) {
            Debug.debug("BlockBreakListener#onMonitor(): Cancelled. 14");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }
}
