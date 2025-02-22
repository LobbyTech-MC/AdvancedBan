package com.balugaq.advancedban.core.listeners;

import com.balugaq.advancedban.api.Debug;
import com.balugaq.advancedban.api.EventUtil;
import com.balugaq.advancedban.api.Predications;
import com.balugaq.advancedban.api.events.EventType;
import com.balugaq.advancedban.api.events.FakeBlockBreakEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class BlockBreakListener implements Listener {
    public static final EventType TYPE = EventType.BLOCK_BREAK;

    public static boolean presetPredications(@NotNull FakeBlockBreakEvent event, @NotNull EventPriority eventPriority) {
        return Predications.getPriority(event.getSlimefunId(), TYPE) == eventPriority;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void fakeEvent(@NotNull BlockBreakEvent event) {
        if (!EventUtil.isFakeEvent(event)) {
            Debug.debug("BlockBreakListener#fakeEvent(): New a fake event. 1");
            FakeBlockBreakEvent e = new FakeBlockBreakEvent(event);
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
                event.getPlayer().sendMessage(EventUtil.color(TYPE.getMessage(), "name", item.getItemName()));
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLowest(@NotNull FakeBlockBreakEvent event) {
        if (presetPredications(event, EventPriority.LOWEST)) {
            Debug.debug("BlockBreakListener#onLowest(): Cancelled. 9");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLow(@NotNull FakeBlockBreakEvent event) {
        if (presetPredications(event, EventPriority.LOW)) {
            Debug.debug("BlockBreakListener#onLow(): Cancelled. 10");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNormal(@NotNull FakeBlockBreakEvent event) {
        if (presetPredications(event, EventPriority.NORMAL)) {
            Debug.debug("BlockBreakListener#onNormal(): Cancelled. 11");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHigh(@NotNull FakeBlockBreakEvent event) {
        if (presetPredications(event, EventPriority.HIGH)) {
            Debug.debug("BlockBreakListener#onHigh(): Cancelled. 12");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHighest(@NotNull FakeBlockBreakEvent event) {
        if (presetPredications(event, EventPriority.HIGHEST)) {
            Debug.debug("BlockBreakListener#onHighest(): Cancelled. 13");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMonitor(@NotNull FakeBlockBreakEvent event) {
        if (presetPredications(event, EventPriority.MONITOR)) {
            Debug.debug("BlockBreakListener#onMonitor(): Cancelled. 14");
            event.setCancelled(true);
        }
    }
}
