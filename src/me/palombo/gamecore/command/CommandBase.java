package me.palombo.gamecore.command;

import me.palombo.gamecore.module.Module;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class CommandBase<T extends Module> implements CmdExtend {

    private T module;
    private String permission;
    private List<String> aliases;

    public CommandBase(T module, String permission, String... aliases) {
        this.module = module;
        this.permission = permission;
        this.aliases = Arrays.asList(aliases);
    }

    public T getModule() {
        return module;
    }

    public String getPermission() {
        return permission;
    }

    public Collection<String> getAliases() {
        return aliases;
    }
}
