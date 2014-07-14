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
package org.grire2.Retrieval;

import org.grire2.Components.Interfaces.Clusterer;
import org.grire2.Components.Interfaces.Storer;
import org.grire2.Database.Database;
import org.grire2.Settings.Configuration;

import java.util.stream.Collectors;

public class BOVWRetrievalSystem extends GlobalRetrievalSystem {
    public BOVWRetrievalSystem(Database _database, Configuration _configuration) {
        super(_database, _configuration);
    }

    @Override
    public void index(String _name) {
        super.index(_name+"_local");
    }

    public void createCodebook(String _name){
        Clusterer clusterer = _configuration.getClusterer();
        Storer storer = _configuration.getStorer();
        float[][] classes = clusterer.cluster(_database.asStream().collect(Collectors.toSet()));
        storer.getSet(_name + "_codebook");
    }
}
