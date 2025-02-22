package com.balugaq.advancedban.core.listeners;

import com.balugaq.advancedban.api.enums.EventType;
import com.balugaq.advancedban.api.utils.Debug;
import com.balugaq.advancedban.api.utils.EventUtil;
import com.balugaq.advancedban.api.utils.Predications;
import com.balugaq.advancedban.implementation.AdvancedBan;
import io.github.thebusybiscuit.slimefun4.api.events.SlimefunItemRegistryFinalizedEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import lombok.Getter;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import org.bukkit.Bukkit;
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

    public synchronized static void rollback() {
        for (Map.Entry<AContainer, List<MachineRecipe>> entry : deletedRecipes.entrySet()) {
            List<MachineRecipe> recipes = entry.getKey().getMachineRecipes();
            recipes.addAll(entry.getValue());
        }

        deletedRecipes.clear();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onInit(SlimefunItemRegistryFinalizedEvent e) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(AdvancedBan.getInstance(), () -> {
            Debug.debug("MachineCraftListener#onInit(SlimefunItemRegistryFinalizedEvent) : Looking up banned recipes 60");
            for (SlimefunItem item : Slimefun.getRegistry().getAllSlimefunItems()) {
                if (!(item instanceof AContainer aContainer)) {
                    continue;
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

                if (!bannedRecipes.isEmpty()) {
                    Debug.debug("Found " + bannedRecipes.size() + " banned recipes in " + aContainer.getItemName() + " 61");
                    for (MachineRecipe recipe : bannedRecipes) {
                        deletedRecipes.computeIfAbsent(aContainer, k -> new ArrayList<>()).add(recipe);
                        recipes.remove(recipe);
                    }
                }
            }

            Debug.debug("MachineCraftListener#onInit(SlimefunItemRegistryFinalizedEvent) : Banned some recipes from " + deletedRecipes.size() + " kinds of AContainers 62");
            Debug.debug("MachineCraftListener#onInit(SlimefunItemRegistryFinalizedEvent) : Done 63");
        }, 40L);
    }
}
