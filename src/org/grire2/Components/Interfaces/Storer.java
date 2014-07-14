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
package org.grire2.Components.Interfaces;

import java.util.*;

public abstract class Storer implements GRirePlugin{

    public abstract Map getMap(String name);
    public abstract Set getSet(String name);


    private static Set<Class> allowedKeys=new HashSet<>(Arrays.asList(
            String.class));
    private static Set<Class> allowdValues=new HashSet<>(Arrays.asList(
            Float[].class
    ));

    @Override
    public Class getComponentInterface() {
        return Storer.class;
    }
}
