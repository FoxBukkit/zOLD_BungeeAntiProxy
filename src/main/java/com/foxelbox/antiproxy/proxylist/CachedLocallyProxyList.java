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
