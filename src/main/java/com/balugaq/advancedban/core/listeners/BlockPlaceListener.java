package com.balugaq.advancedban.core.listeners;

import com.balugaq.advancedban.api.Debug;
import com.balugaq.advancedban.api.EventUtil;
import com.balugaq.advancedban.api.Predications;
import com.balugaq.advancedban.api.events.EventType;
import com.balugaq.advancedban.api.events.FakeBlockPlaceEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;

public class BlockPlaceListener implements Listener {
    public static final EventType TYPE = EventType.BLOCK_PLACE;

    public static boolean presetPredications(@NotNull FakeBlockPlaceEvent event, @NotNull EventPriority eventPriority) {
        return Predications.getPriority(event.getSlimefunId(), TYPE) == eventPriority;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void fakeEvent(@NotNull BlockPlaceEvent event) {
        if (!EventUtil.isFakeEvent(event)) {
            Debug.debug("BlockPlaceListener#fakeEvent(): New a fake event. 2");
            FakeBlockPlaceEvent e = new FakeBlockPlaceEvent(event);
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
    public void onLowest(@NotNull FakeBlockPlaceEvent event) {
        if (presetPredications(event, EventPriority.LOWEST)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLow(@NotNull FakeBlockPlaceEvent event) {
        if (presetPredications(event, EventPriority.LOW)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNormal(@NotNull FakeBlockPlaceEvent event) {
        if (presetPredications(event, EventPriority.NORMAL)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHigh(@NotNull FakeBlockPlaceEvent event) {
        if (presetPredications(event, EventPriority.HIGH)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHighest(@NotNull FakeBlockPlaceEvent event) {
        if (presetPredications(event, EventPriority.HIGHEST)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMonitor(@NotNull FakeBlockPlaceEvent event) {
        if (presetPredications(event, EventPriority.MONITOR)) {
            event.setCancelled(true);
        }
    }
}
