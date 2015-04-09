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
