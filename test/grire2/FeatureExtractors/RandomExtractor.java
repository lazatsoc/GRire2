package grire2.FeatureExtractors;

import grire2.FeatureExtractors.FeatureExtractor;
import grire2.Structures.Image;

import java.util.Random;

/**
 * Test-only feature extractor. Returns a feature vector consisting of 64 random numbers.
 * Created by Lazaros Tsochatzidis on 27/2/2016.
 */
public class RandomExtractor implements FeatureExtractor {
    private static Random random=new Random();

    @Override
    public float[] getDescriptor(Image image) {
        float[] floats = new float[64];
        for (int i=0;i<64;i++) {
            floats[i]=random.nextFloat();
        }
        return floats;
    }

    @Override
    public String getName() {
        return null;
    }
}
