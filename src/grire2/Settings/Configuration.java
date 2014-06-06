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

package grire2.Settings;

import grire2.Settings.Interfaces.FeatureExtractor;
import grire2.Settings.Interfaces.SimilarityMeasure;
import grire2.Settings.Interfaces.Storer;

public class Configuration {
    private String _name;
    private RetrievalType _retrievalType;

    private Storer _storer;

    private FeatureExtractor _featureExtractor;
    private SimilarityMeasure _similarityMeasure;

    public Configuration(String _name, Storer _storer, FeatureExtractor _featureExtractor, SimilarityMeasure _similarityMeasure) {
        this._name = _name;
        this._featureExtractor = _featureExtractor;
        this._similarityMeasure = _similarityMeasure;
        this._storer=_storer;
        _retrievalType=RetrievalType.GLOBAL;
    }

    public String getName() {
        return _name;
    }

    public RetrievalType getRetrievalType() {
        return _retrievalType;
    }

    public FeatureExtractor getFeatureExtractor() {
        return _featureExtractor;
    }

    public SimilarityMeasure getSimilarityMeasure() {
        return _similarityMeasure;
    }

    public Storer getStorer() {
        return _storer;
    }

    public enum RetrievalType{
        GLOBAL, BAG_OF_VISUAL_WORDS
    }


}

