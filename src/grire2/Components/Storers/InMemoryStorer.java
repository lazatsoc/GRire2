/* This file is part of the GRire project: https://code.google.com/p/grire/
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
 * Copyright (C) 2013 Lazaros Tsochatzidis <ltsochat at ee.duth.gr>
 */
package grire2.Components.Storers;

import grire2.Components.Interfaces.Storer;
import javafx.concurrent.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InMemoryStorer extends Storer {

    private HashMap<String,List> lists=new HashMap<>();
    private HashMap<String,Set> sets=new HashMap<>();
    private HashMap<String,Map> maps=new HashMap<>();

    @Override
    public Map getMap(String name) {
        return null;
    }

    @Override
    public Set getSet(String name) {
        return null;
    }

    @Override
    public List getList(String name) {
        return null;
    }

    //GrirePlugin Methods.
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
