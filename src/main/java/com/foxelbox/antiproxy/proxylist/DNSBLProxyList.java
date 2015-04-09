package com.foxelbox.antiproxy.proxylist;

import java.net.InetAddress;

public abstract class DNSBLProxyList implements IProxyList {
    public abstract String getProxyListHost();

    @Override
    public void reload() {

    }

    @Override
    public boolean isOnList(InetAddress ip) {
        return false;
    }
}
