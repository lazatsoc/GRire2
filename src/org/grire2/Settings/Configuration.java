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

package org.grire2.Settings;

import org.grire2.Components.Interfaces.*;

public class Configuration {
    private String _name;
    private RetrievalType _retrievalType;

    private Storer _storer;

    private FeatureExtractor _featureExtractor;
    private SimilarityMeasure _similarityMeasure;

    //BOVW Specific
    private Clusterer _clusterer;
    private Stemmer _stemmer;

    private Configuration(){}

    public Configuration(String _name, Storer _storer, FeatureExtractor _featureExtractor, SimilarityMeasure _similarityMeasure) {
        this._name = _name;
        this._featureExtractor = _featureExtractor;
        this._similarityMeasure = _similarityMeasure;
        this._storer=_storer;
        _retrievalType=RetrievalType.GLOBAL;
    }

    public Configuration(String _name, Storer _storer, FeatureExtractor _featureExtractor, SimilarityMeasure _similarityMeasure, Clusterer _clusterer, Stemmer _stemmer) {
        this._name = _name;
        this._storer = _storer;
        this._featureExtractor = _featureExtractor;
        this._similarityMeasure = _similarityMeasure;
        this._clusterer = _clusterer;
        this._stemmer = _stemmer;
        _retrievalType=RetrievalType.BAG_OF_VISUAL_WORDS;
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

    public Clusterer getClusterer() {
        return _clusterer;
    }

    public Stemmer getStemmer() {
        return _stemmer;
    }

    public enum RetrievalType{
        GLOBAL, BAG_OF_VISUAL_WORDS
    }

}

