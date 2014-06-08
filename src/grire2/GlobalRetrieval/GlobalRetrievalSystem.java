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

package grire2.GlobalRetrieval;

import grire2.Database.Database;
import grire2.Database.ImageWrapper;
import grire2.Settings.Configuration;

import java.util.Map;

public class GlobalRetrievalSystem {

    String _name;
    Database _database;
    Configuration _configuration;

    public GlobalRetrievalSystem(Database _database, Configuration _configuration) {
        this._database = _database;
        this._configuration = _configuration;
    }

    public GlobalRetrievalSystem(Database _database, Configuration _configuration, String _name) {
        this._database = _database;
        this._configuration = _configuration;
        this._name=_name;
    }

    public void index(){
        extractFeatures();
    }

    protected void extractFeatures(){
        Map fmap = _configuration.getStorer().getMap(
                (_name==null?"":_name+"_") +
                _configuration.getName() +
                "features");
        float[][] descs;
        for (ImageWrapper img : _database){
            descs = _configuration.getFeatureExtractor().extract(img);
            fmap.put(img.getId(), descs);
        }
    }
}
