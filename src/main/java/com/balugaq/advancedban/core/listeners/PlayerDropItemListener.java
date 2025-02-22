package com.balugaq.advancedban.core.listeners;

import com.balugaq.advancedban.api.Debug;
import com.balugaq.advancedban.api.EventUtil;
import com.balugaq.advancedban.api.Predications;
import com.balugaq.advancedban.api.events.EventType;
import com.balugaq.advancedban.api.events.FakePlayerDropItemEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerDropItemListener implements Listener {
    public static final EventType TYPE = EventType.PLAYER_DROP_ITEM;

    public static boolean presetPredications(@NotNull FakePlayerDropItemEvent event, @NotNull EventPriority eventPriority) {
        return Predications.getPriority(event.getSlimefunId(), TYPE) == eventPriority;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void fakeEvent(@NotNull PlayerDropItemEvent event) {
        if (!EventUtil.isFakeEvent(event)) {
            Debug.debug("PlayerDropItemListener#fakeEvent(): New a fake event. 5");
            FakePlayerDropItemEvent e = new FakePlayerDropItemEvent(event);
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
    public void onLowest(@NotNull FakePlayerDropItemEvent event) {
        if (presetPredications(event, EventPriority.LOWEST)) {
            Debug.debug("PlayerDropItemListener#onLowest(): Cancelled. 33");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onLow(@NotNull FakePlayerDropItemEvent event) {
        if (presetPredications(event, EventPriority.LOW)) {
            Debug.debug("PlayerDropItemListener#onLow(): Cancelled. 34");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onNormal(@NotNull FakePlayerDropItemEvent event) {
        if (presetPredications(event, EventPriority.NORMAL)) {
            Debug.debug("PlayerDropItemListener#onNormal(): Cancelled. 35");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHigh(@NotNull FakePlayerDropItemEvent event) {
        if (presetPredications(event, EventPriority.HIGH)) {
            Debug.debug("PlayerDropItemListener#onHigh(): Cancelled. 36");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHighest(@NotNull FakePlayerDropItemEvent event) {
        if (presetPredications(event, EventPriority.HIGHEST)) {
            Debug.debug("PlayerDropItemListener#onHighest(): Cancelled. 37");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMonitor(@NotNull FakePlayerDropItemEvent event) {
        if (presetPredications(event, EventPriority.MONITOR)) {
            Debug.debug("PlayerDropItemListener#onMonitor(): Cancelled. 38");
            event.setCancelled(true);
        }
    }
}
