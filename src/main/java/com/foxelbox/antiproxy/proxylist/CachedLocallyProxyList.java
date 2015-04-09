package com.foxelbox.antiproxy.proxylist;

import java.net.InetAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class CachedLocallyProxyList implements IProxyList {
    private Set<InetAddress> addresses = new HashSet<>();

    protected abstract Collection<InetAddress> loadAddresses() throws Exception;

    @Override
    public void reload() {
        HashSet<InetAddress> newAddresses = new HashSet<>();
        try {
            newAddresses.addAll(loadAddresses());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        addresses = newAddresses;
    }

    @Override
    public boolean isOnList(InetAddress ip) {
        return addresses.contains(ip);
    }
}
