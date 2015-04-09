package com.foxelbox.antiproxy.proxylist.impl;

import com.foxelbox.antiproxy.AntiProxyPlugin;
import com.foxelbox.antiproxy.proxylist.HTTPDownloadedProxyList;
import net.md_5.bungee.api.config.ListenerInfo;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collection;

public class TorHTTPList extends HTTPDownloadedProxyList {
    private static URL torURL;

    @Override
    public void reload() {
        Collection<ListenerInfo> listenerInfos = AntiProxyPlugin.instance.getProxy().getConfig().getListeners();
        String primaryListenerAddress = null;
        int primaryListenerPort = -1;
        for(ListenerInfo listenerInfo : listenerInfos) {
            final InetAddress address = listenerInfo.getHost().getAddress();
            if(address.isLinkLocalAddress() || address.isLoopbackAddress() || address.isSiteLocalAddress() ||  address.isMulticastAddress()) {
                continue;
            }
            primaryListenerAddress = address.getHostAddress();
            primaryListenerPort = listenerInfo.getHost().getPort();
            break;
        }

        if(primaryListenerAddress == null || primaryListenerPort <= 0) {
            throw new RuntimeException("Could not find public listener");
        }

        try {
            torURL = new URL("https://check.torproject.org/cgi-bin/TorBulkExitList.py?ip=" + URLEncoder.encode(primaryListenerAddress, "UTF-8") + "&port=" + primaryListenerPort);
        } catch (MalformedURLException|UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        super.reload();
    }

    @Override
    protected URL getURL() {
        return torURL;
    }
}
