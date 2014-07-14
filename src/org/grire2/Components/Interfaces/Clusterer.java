package org.grire2.Components.Interfaces;

import java.util.Set;

public abstract class Clusterer implements GRirePlugin {
    public abstract float[][] cluster(Set features);

    @Override
    public Class getComponentInterface() {
        return Clusterer.class;
    }
}
