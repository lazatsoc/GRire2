package grire2.FeatureExtractors;

import grire2.Structures.Image;

/**
 * Interface for all FeatureExtractors
 * Created by Lazaros Tsochatzidis on 26/2/2016.
 */
public interface FeatureExtractor {
    float[] getDescriptor(Image image);
    String getName();
}
