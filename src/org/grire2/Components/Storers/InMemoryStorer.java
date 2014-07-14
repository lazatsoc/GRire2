/* This file is part of the GRire project: http://www.grire.net/
 *
 * GRire is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GRire is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (C) 2014 Lazaros Tsochatzidis <ltsochat at ee.duth.gr>
 */
package org.grire2.Components.Storers;

import org.grire2.Components.Interfaces.Storer;
import javafx.concurrent.Task;

import java.util.*;

public class InMemoryStorer extends Storer {

    private HashMap<String,Set> sets=new HashMap<>();
    private HashMap<String,Map> maps=new HashMap<>();

    @Override
    public Map getMap(String name) {
        if (maps.containsKey(name)) return maps.get(name);
        else{
            Map map=new Hashtable<>();
            maps.put(name, map);
            return map;
        }
    }

    @Override
    public Set getSet(String name) {
        if (sets.containsKey(name)) return sets.get(name);
        else {
            Set set=new HashSet<>();
            sets.put(name,set);
            return set;
        }
    }

    //GrirePlugin Methods
    @Override
    public Task setUp(Object... args) throws Exception {
        return null;
    }

    @Override
    public Class getComponentInterface() {
        return null;
    }

    @Override
    public Class[] getParameterTypes() {
        return new Class[0];
    }

    @Override
    public Class[] getSetUpParameterTypes() {
        return new Class[0];
    }

    @Override
    public String[] getParameterNames() {
        return new String[0];
    }

    @Override
    public String[] getSetUpParameterNames() {
        return new String[0];
    }

    @Override
    public String[] getDefaultParameterValues() {
        return new String[0];
    }

    @Override
    public String[] getDefaultSetUpParameterValues() {
        return new String[0];
    }

    @Override
    public boolean requiresSetUp() {
        return false;
    }
}
