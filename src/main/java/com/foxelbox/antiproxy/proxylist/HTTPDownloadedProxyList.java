package com.foxelbox.antiproxy.proxylist;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class HTTPDownloadedProxyList extends CachedLocallyProxyList {
    protected abstract URL getURL();

    @Override
    protected Collection<InetAddress> loadAddresses() throws Exception {
        ArrayList<InetAddress> addresses = new ArrayList<>();
        URLConnection connection = getURL().openConnection();
        BufferedReader connReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while((line = connReader.readLine()) != null) {
            line = line.trim();
            if(line.charAt(0) == '#' || line.isEmpty()) {
                continue;
            }
            addresses.add(InetAddress.getByName(line));
        }
        connReader.close();
        return addresses;
    }
}
