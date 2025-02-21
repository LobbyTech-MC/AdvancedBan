package com.balugaq.advancedban.core.commands.subcommands;

import com.balugaq.advancedban.api.Lang;
import com.balugaq.advancedban.core.commands.SubCommand;
import com.balugaq.advancedban.implementation.AdvancedBan;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VersionCommand extends SubCommand {
    public VersionCommand(@NotNull JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public @NotNull String getName() {
        return "version";
    }

    @Override
    public boolean canCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            return false;
        }
        return getName().equalsIgnoreCase(args[0]);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage(Lang.getMessage("commands.version.success",
                "version", AdvancedBan.getInstance().getConfigManager().getConfigVersion(),
                "build_station", AdvancedBan.getInstance().getConfigManager().getBuildStation()));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
