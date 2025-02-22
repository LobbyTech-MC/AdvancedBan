package com.balugaq.advancedban.api.events;

import com.balugaq.advancedban.api.Debug;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import lombok.Getter;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class FakeCraftItemEvent extends CraftItemEvent implements FakeEvent {
    private final @Nullable String slimefunId;

    public FakeCraftItemEvent(@NotNull CraftItemEvent event) {
        this(event.getRecipe(), event.getView(), event.getSlotType(), event.getSlot(), event.getClick(), event.getAction());
    }

    public FakeCraftItemEvent(@NotNull Recipe recipe, @NotNull InventoryView view, @NotNull InventoryType.SlotType slotType, int click, @NotNull ClickType clickType, @NotNull InventoryAction action) {
        super(recipe, view, slotType, click, clickType, action);
        ItemStack itemStack = recipe.getResult();
        SlimefunItem item = SlimefunItem.getByItem(itemStack);
        if (item != null) {
            slimefunId = item.getId();
        } else {
            slimefunId = null;
        }
    }

    @Override
    public void setCancelled(boolean cancelled) {
        super.setCancelled(cancelled);
        Debug.debug("FakeCraftItemEvent#setCancelled() : Called 48");
        Debug.stackTraceManually();
    }
}
