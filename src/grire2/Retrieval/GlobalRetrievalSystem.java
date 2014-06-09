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
 * Copyright (C) 2014 Lazaros Tsochatzidis <ltsochat at ee.duth.gr>
 */

package grire2.Retrieval;

import grire2.Components.FeatureExtractors.CEDDExtractor.CEDDExtractor;
import grire2.Components.Interfaces.SimilarityMeasure;
import grire2.Components.SimilarityMeasures.SquaredEuclideanSimilarity;
import grire2.Components.Storers.MapDBStorer;
import grire2.Database.Database;
import grire2.Database.ImageWrapper;
import grire2.Settings.Configuration;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GlobalRetrievalSystem {

    Database _database;
    Configuration _configuration;

    public GlobalRetrievalSystem(Database _database, Configuration _configuration) {
        this._database = _database;
        this._configuration = _configuration;
    }

    public void index(String _name) {
        extractFeatures(_name);
    }

    public List<RankedItem> query(String id, String indexName){
        Map<String, float[][]> index=_configuration.getStorer()
                .getMap(indexName + "_" + _configuration.getName() + "_features");
        SimilarityMeasure similarityMeasure = _configuration.getSimilarityMeasure();
        float[][] queryDescriptor = index.get(id);
        return index.entrySet().stream()
                .map(img -> new RankedItem(img.getKey(), similarityMeasure.calculate(queryDescriptor, img.getValue())))
                .sorted(similarityMeasure.getType() == SimilarityMeasure.SimilarityType.DISTANCE ?
                        Comparator.<RankedItem>naturalOrder(): Comparator.<RankedItem>reverseOrder())
                .collect(Collectors.toList());
    }

    protected Map extractFeatures(String _name){
        Map fmap = _configuration.getStorer().getMap(
                _name+"_" +
                _configuration.getName() +
                "_features");
        float[][] descs;
        for (ImageWrapper img : _database){
            descs = _configuration.getFeatureExtractor().extract(img);
            fmap.put(img.getId(), descs);
        }
        return fmap;
    }

    public static void main(String[] args) {
        try {
            Database mydb = Database.createDatabase("E:\\GRire test\\db", "E:\\GRire test\\qrels.txt", Arrays.asList(new String[]{"jpg"}));
            Configuration configuration=new Configuration("Test", new MapDBStorer("tempdb"), new CEDDExtractor(), new SquaredEuclideanSimilarity());
            GlobalRetrievalSystem system = new GlobalRetrievalSystem(mydb,configuration);
            system.index("index");
            List<RankedItem> results = system.query("1", "index");
            for (RankedItem item : results)
                System.out.println(item.getId());
        }catch (Exception ex) {}
    }
}
