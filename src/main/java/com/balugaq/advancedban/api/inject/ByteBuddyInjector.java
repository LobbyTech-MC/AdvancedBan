package com.balugaq.advancedban.api.inject;

import com.balugaq.advancedban.api.enums.EventType;
import com.balugaq.advancedban.api.utils.Debug;
import com.balugaq.advancedban.api.utils.EventUtil;
import com.balugaq.advancedban.api.utils.Predications;
import com.balugaq.advancedban.core.listeners.BlockPlaceListener;
import com.balugaq.advancedban.implementation.AdvancedBan;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.BlockListener;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ByteBuddyInjector {
    public static boolean setup = false;

    public static void setup() {
        try {
            ByteBuddyAgent.install();
            setup = true;
        } catch (Throwable e) {
            Debug.log("Failed to install ByteBuddyAgent");
            e.printStackTrace();
        }
    }

    public static void inject() {
        if (!setup) {
            setup();
        }

        Debug.log("Injecting Slimefun4#BlockListener.onBlockPlace(BlockPlaceEvent)");
        try {
            new ByteBuddy()
                    .redefine(BlockListener.class)
                    .visit(Advice.to(BlockListenerAdvice.class).on(ElementMatchers.named("onBlockPlace")))
                    .make()
                    .load(
                            BlockListener.class.getClassLoader(),
                            ClassReloadingStrategy.fromInstalledAgent()
                    );
        } catch (Throwable e) {
            Debug.log("Failed to inject Slimefun4#BlockListener.onBlockPlace(BlockPlaceEvent)");
            e.printStackTrace();
        }

        if (AdvancedBan.getInstance().getIntegrationManager().isEnabledFastMachines()) {
            Debug.log("Injecting FastMachines#SlimefunItemUtils.isDisabled(ItemStack)");
            try {
                Class<?> fastMachines = Class.forName("net.guizhanss.fastmachines.utils.SlimefunItemUtils");

                new ByteBuddy()
                        .redefine(fastMachines)
                        .visit(Advice.to(SlimefunItemUtilsAdvice.class).on(ElementMatchers.named("isDisabled").and(
                                ElementMatchers.takesArguments(ItemStack.class))))
                        .make()
                        .load(
                                fastMachines.getClassLoader(),
                                ClassReloadingStrategy.fromInstalledAgent()
                        );
            } catch (Throwable e) {
                Debug.log("Failed to inject FastMachines#SlimefunItemUtils.isDisabled(ItemStack)");
                e.printStackTrace();
            }
        }
    }

    public static class BlockListenerAdvice {
        @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
        public static boolean onMethodEnter(
                @Advice.Argument(0) @NotNull BlockPlaceEvent event
        ) {
            if (!AdvancedBan.isEnabledPlugin()) {
                return false; // continue the original logic
            }

            Debug.debug("BlockPlaceListener#onMethodEnter(): Entered. 54");

            if (Predications.preset(EventUtil.getSlimefunId(event), BlockPlaceListener.TYPE)) {
                Debug.debug("BlockPlaceListener#onMethodEnter(): Is banned item. 57");
                return true; // ignored the original logic
            }
            return false; // continue the original logic
        }
    }

    public static class SlimefunItemUtilsAdvice {
        @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
        public static boolean onMethodEnter(
                @Advice.Argument(0) @NotNull ItemStack itemStack
        ) {
            if (!AdvancedBan.isEnabledPlugin()) {
                return false; // continue the original logic
            }

            Debug.debug("SlimefunItemUtils#isDisabled: Entered. 58");

            if (Predications.preset(EventUtil.getSlimefunId(itemStack), EventType.MACHINE_CRAFT)) {
                Debug.debug("SlimefunItemUtils#isDisabled: Is banned item. 59");
                return true; // ignored the original logic
            }
            return false; // continue the original logic
        }
    }
}