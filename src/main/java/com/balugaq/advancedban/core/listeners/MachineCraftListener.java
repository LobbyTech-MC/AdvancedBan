package com.balugaq.advancedban.core.listeners;

import com.balugaq.advancedban.api.enums.EventType;
import com.balugaq.advancedban.api.utils.EventUtil;
import com.balugaq.advancedban.api.utils.Predications;
import io.github.thebusybiscuit.slimefun4.api.events.SlimefunItemRegistryFinalizedEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MachineCraftListener implements Listener {
    @Getter
    private static final Map<AContainer, List<MachineRecipe>> deletedRecipes = new HashMap<>();

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInit(SlimefunItemRegistryFinalizedEvent e) {
        for (SlimefunItem item : Slimefun.getRegistry().getAllSlimefunItems()) {
            if (!(item instanceof AContainer aContainer)) {
                break;
            }

            List<MachineRecipe> bannedRecipes = new ArrayList<>();
            List<MachineRecipe> recipes = aContainer.getMachineRecipes();
            for (MachineRecipe recipe : recipes) {
                for (ItemStack itemStack : recipe.getOutput()) {
                    if (Predications.preset(EventUtil.getSlimefunId(itemStack), EventType.MACHINE_CRAFT)) {
                        bannedRecipes.add(recipe);
                        break;
                    }
                }
            }

            for (MachineRecipe recipe : bannedRecipes) {
                deletedRecipes.computeIfAbsent(aContainer, k -> new ArrayList<>()).add(recipe);
                recipes.remove(recipe);
            }
        }
    }
}
