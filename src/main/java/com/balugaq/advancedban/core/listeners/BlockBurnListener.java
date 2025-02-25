package com.balugaq.advancedban.core.listeners;

import com.balugaq.advancedban.api.enums.EventType;
import com.balugaq.advancedban.api.utils.Debug;
import com.balugaq.advancedban.api.utils.EventUtil;
import com.balugaq.advancedban.api.utils.Predications;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.jetbrains.annotations.NotNull;

public class BlockBurnListener implements Listener {
    public static final EventType TYPE = EventType.BLOCK_BURN;

    public static boolean presetPredications(@NotNull BlockBurnEvent event, @NotNull EventPriority eventPriority) {
        String slimefunId = EventUtil.getSlimefunId(event);
        return Predications.getPriority(slimefunId, TYPE) == eventPriority;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLowest(@NotNull BlockBurnEvent event) {
        if (presetPredications(event, EventPriority.LOWEST)) {
            Debug.debug("BlockBurnListener#onLowest(): Cancelled. 145");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLow(@NotNull BlockBurnEvent event) {
        if (presetPredications(event, EventPriority.LOW)) {
            Debug.debug("BlockBurnListener#onLow(): Cancelled. 146");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNormal(@NotNull BlockBurnEvent event) {
        if (presetPredications(event, EventPriority.NORMAL)) {
            Debug.debug("BlockBurnListener#onNormal(): Cancelled. 147");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHigh(@NotNull BlockBurnEvent event) {
        if (presetPredications(event, EventPriority.HIGH)) {
            Debug.debug("BlockBurnListener#onHigh(): Cancelled. 148");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHighest(@NotNull BlockBurnEvent event) {
        if (presetPredications(event, EventPriority.HIGHEST)) {
            Debug.debug("BlockBurnListener#onHighest(): Cancelled. 149");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMonitor(@NotNull BlockBurnEvent event) {
        if (presetPredications(event, EventPriority.MONITOR)) {
            Debug.debug("BlockBurnListener#onMonitor(): Cancelled. 150");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }
}
