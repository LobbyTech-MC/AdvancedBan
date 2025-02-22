package com.balugaq.advancedban.api;

import com.balugaq.advancedban.core.listeners.BlockPlaceListener;
import io.github.thebusybiscuit.slimefun4.implementation.listeners.BlockListener;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;

public class ByteBuddyInjector {
    public static boolean setup = false;
    public static void setup() {
        ByteBuddyAgent.install();
        setup = true;
    }

    public static void inject() {
        if (!setup) {
            setup();
        }

        new ByteBuddy()
            .redefine(BlockListener.class)
            .visit(Advice.to(BlockPlaceListener.class).on(ElementMatchers.named("onBlockPlace")))
            .make()
            .load(
                BlockListener.class.getClassLoader(),
                ClassReloadingStrategy.fromInstalledAgent()
            );
    }
}