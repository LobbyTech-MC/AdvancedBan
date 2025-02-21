package com.balugaq.advancedban.api;

import com.balugaq.advancedban.implementation.AdvancedBan;

public class Lang {
    public static String getMessage(String path) {
        return AdvancedBan.getInstance().getLocalizationService().getString("messages." + path);
    }

    public static String getMessage(String path, Object... args) {
        return EventUtil.color(AdvancedBan.getInstance().getLocalizationService().getString("messages." + path), args);
    }
}
