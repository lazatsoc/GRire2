package grire2.FeatureExtractors;

import grire2.FeatureExtractors.FeatureExtractor;
import grire2.Structures.Image;

import java.util.Arrays;

/**
 * Test-only feature extractor. Returns a feature vector consisting of 64 ones.
 * Created by Lazaros Tsochatzidis on 26/2/2016.
 */
public class All64OneExtractor implements FeatureExtractor {
    @Override
    public float[] getDescriptor(Image image) {
        float[] descriptor = new float[64];
        Arrays.fill(descriptor,1);
        return descriptor;
    }

    @Override
    public String getName() {
        return "64_1_TestExtractor";
    }
}
