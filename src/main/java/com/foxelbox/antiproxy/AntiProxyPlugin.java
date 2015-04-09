package com.foxelbox.antiproxy;

import net.md_5.bungee.api.plugin.Plugin;

public class AntiProxyPlugin extends Plugin {
    public static AntiProxyPlugin instance;

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
    }
}
