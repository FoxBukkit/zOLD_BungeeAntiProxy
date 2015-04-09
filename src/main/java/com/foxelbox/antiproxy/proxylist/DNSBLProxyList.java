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
