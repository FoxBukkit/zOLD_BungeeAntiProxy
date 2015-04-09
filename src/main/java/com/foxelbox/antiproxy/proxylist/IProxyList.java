package com.foxelbox.antiproxy.proxylist;

import java.net.InetAddress;

public interface IProxyList {
    void reload();
    boolean isOnList(InetAddress ip);
}
