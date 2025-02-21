package com.balugaq.advancedban.api.events;

public enum ConfigVersion {
    C_UNKNOWN,
    C_20250221_1,
    C_20250221_2
    ;
    public static boolean isBefore(ConfigVersion version1, ConfigVersion version2) {
        return version1.ordinal() < version2.ordinal();
    }

    public static boolean isAfter(ConfigVersion version1, ConfigVersion version2) {
        return version1.ordinal() > version2.ordinal();
    }

    public static boolean isAtLeast(ConfigVersion version1, ConfigVersion version2) {
        return version1.ordinal() >= version2.ordinal();
    }

    public static boolean isAtMost(ConfigVersion version1, ConfigVersion version2) {
        return version1.ordinal() <= version2.ordinal();
    }
    ConfigVersion() {

    }
}
