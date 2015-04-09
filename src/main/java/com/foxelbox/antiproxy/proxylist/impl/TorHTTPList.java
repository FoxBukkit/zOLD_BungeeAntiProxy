/**
 * This file is part of AntiProxy.
 *
 * AntiProxy is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AntiProxy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with AntiProxy.  If not, see <http://www.gnu.org/licenses/>.
 */
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
