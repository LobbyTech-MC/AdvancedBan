package com.balugaq.advancedban.core.listeners;

import com.balugaq.advancedban.api.enums.EventType;
import com.balugaq.advancedban.api.utils.Debug;
import com.balugaq.advancedban.api.utils.EventUtil;
import com.balugaq.advancedban.api.utils.Predications;
import io.github.thebusybiscuit.slimefun4.api.events.MultiBlockCraftEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class MultiBlockCraftListener implements Listener {
    public static final EventType TYPE = EventType.MULTI_BLOCK_CRAFT;

    public static boolean presetPredications(@NotNull MultiBlockCraftEvent event, @NotNull EventPriority eventPriority) {
        String slimefunId = EventUtil.getSlimefunId(event);
        return !EventUtil.isBypass(slimefunId, TYPE, event.getPlayer()) && Predications.getPriority(slimefunId, TYPE) == eventPriority;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLowest(@NotNull MultiBlockCraftEvent event) {
        if (presetPredications(event, EventPriority.LOWEST)) {
            Debug.debug("MultiBlockCraftListener#onLowest(): Cancelled. 27");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLow(@NotNull MultiBlockCraftEvent event) {
        if (presetPredications(event, EventPriority.LOW)) {
            Debug.debug("MultiBlockCraftListener#onLow(): Cancelled. 28");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNormal(@NotNull MultiBlockCraftEvent event) {
        if (presetPredications(event, EventPriority.NORMAL)) {
            Debug.debug("MultiBlockCraftListener#onNormal(): Cancelled. 29");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHigh(@NotNull MultiBlockCraftEvent event) {
        if (presetPredications(event, EventPriority.HIGH)) {
            Debug.debug("MultiBlockCraftListener#onHigh(): Cancelled. 30");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHighest(@NotNull MultiBlockCraftEvent event) {
        if (presetPredications(event, EventPriority.HIGHEST)) {
            Debug.debug("MultiBlockCraftListener#onHighest(): Cancelled. 31");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMonitor(@NotNull MultiBlockCraftEvent event) {
        if (presetPredications(event, EventPriority.MONITOR)) {
            Debug.debug("MultiBlockCraftListener#onMonitor(): Cancelled. 32");
            event.setCancelled(true);
            EventUtil.notice(event, TYPE);
        }
    }
}
