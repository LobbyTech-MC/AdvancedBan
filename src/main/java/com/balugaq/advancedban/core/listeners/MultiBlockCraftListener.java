package com.balugaq.advancedban.core.listeners;

import com.balugaq.advancedban.api.Debug;
import com.balugaq.advancedban.api.EventUtil;
import com.balugaq.advancedban.api.Predications;
import com.balugaq.advancedban.api.events.EventType;
import com.balugaq.advancedban.api.events.FakeMultiBlockCraftEvent;
import io.github.thebusybiscuit.slimefun4.api.events.MultiBlockCraftEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MultiBlockCraftListener implements Listener {
    public static final EventType TYPE = EventType.MULTI_BLOCK_CRAFT;

    public static boolean presetPredications(@NotNull FakeMultiBlockCraftEvent event, @NotNull EventPriority eventPriority) {
        for (String slimefunId : event.getSlimefunIds()) {
            if (Predications.getPriority(slimefunId, TYPE) == eventPriority) {
                return true;
            }
        }

        return false;
    }

    @Nullable
    public static String getBannedSlimefunId(@NotNull FakeMultiBlockCraftEvent event) {
        for (String slimefunId : event.getSlimefunIds()) {
            for (EventPriority priority : EventPriority.values()) {
                if (Predications.getPriority(slimefunId, TYPE) == priority) {
                    return slimefunId;
                }
            }
        }

        return null;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void fakeEvent(@NotNull MultiBlockCraftEvent event) {
        if (!EventUtil.isFakeEvent(event)) {
            Debug.debug("MultiBlockCraftListener#fakeEvent(): New a fake event. 4");
            FakeMultiBlockCraftEvent e = new FakeMultiBlockCraftEvent(event);
            Bukkit.getPluginManager().callEvent(e);
            if (e.isCancelled()) {
                event.setCancelled(true);
                String bannedSlimefunId = getBannedSlimefunId(e);
                if (bannedSlimefunId != null) {
                    SlimefunItem item = SlimefunItem.getById(bannedSlimefunId);
                    if (item == null) {
                        return;
                    }
                    event.getPlayer().sendMessage(EventUtil.color(TYPE.getMessage(), "name", item.getItemName()));
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLowest(@NotNull FakeMultiBlockCraftEvent event) {
        if (presetPredications(event, EventPriority.LOWEST)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLow(@NotNull FakeMultiBlockCraftEvent event) {
        if (presetPredications(event, EventPriority.LOW)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNormal(@NotNull FakeMultiBlockCraftEvent event) {
        if (presetPredications(event, EventPriority.NORMAL)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHigh(@NotNull FakeMultiBlockCraftEvent event) {
        if (presetPredications(event, EventPriority.HIGH)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHighest(@NotNull FakeMultiBlockCraftEvent event) {
        if (presetPredications(event, EventPriority.HIGHEST)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMonitor(@NotNull FakeMultiBlockCraftEvent event) {
        if (presetPredications(event, EventPriority.MONITOR)) {
            event.setCancelled(true);
        }
    }
}
