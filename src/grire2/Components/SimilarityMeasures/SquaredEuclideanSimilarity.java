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
package grire2.Components.SimilarityMeasures;

import grire2.Components.Interfaces.SimilarityMeasure;
import javafx.concurrent.Task;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SquaredEuclideanSimilarity extends SimilarityMeasure {
    @Override
    public float calculate(float[][] qv, float[][] v) {
        List<float[]> qvList = Arrays.asList(qv);
        List<float[]> vList = Arrays.asList(v);

        double ret = qvList.stream().map(qd-> vList.stream().map(vd->calculateEucl(qd,vd)).min(Float::compare))
                .collect(Collectors.summingDouble(x->x.get()));
        return (float) ret;
    }

    protected float calculateEucl(float[] v1, float[] v2){
        float sum=0;
        for (int i=0;i<v1.length;i++) {
            float v = v1[i] - v2[i];
            sum += v * v;
        }
        return sum;
    }

    @Override
    public SimilarityType getType() {
        return null;
    }

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
