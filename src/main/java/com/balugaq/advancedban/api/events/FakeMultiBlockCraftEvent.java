package com.balugaq.advancedban.api.events;

import io.github.thebusybiscuit.slimefun4.api.events.MultiBlockCraftEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FakeMultiBlockCraftEvent extends MultiBlockCraftEvent implements FakeEvent {
    private final @NotNull List<String> slimefunIds = new ArrayList<>();

    public FakeMultiBlockCraftEvent(@NotNull MultiBlockCraftEvent event) {
        this(event.getPlayer(), event.getMachine(), event.getInput(), event.getOutput());
    }

    public FakeMultiBlockCraftEvent(@NotNull Player p, @NotNull MultiBlockMachine machine, ItemStack @NotNull [] input, @NotNull ItemStack output) {
        super(p, machine, input, output);
        for (ItemStack itemStack : input) {
            SlimefunItem item = SlimefunItem.getByItem(itemStack);
            if (item != null) {
                slimefunIds.add(item.getId());
            }
        }
        SlimefunItem item = SlimefunItem.getByItem(output);
        if (item != null) {
            slimefunIds.add(item.getId());
        }
    }
}
