package grire2.Processes;

import grire2.FeatureExtractors.FeatureExtractor;
import grire2.Structures.Image;
import grire2.Structures.ImagePool;

/**
 * Provides functionality for extracting the features of the images in an ImagePool.
 * The images are processed by the FeatureExtractor provided.
 * Created by Lazaros Tsochatzidis on 26/2/2016.
 */
public class FeatureExtraction {
    ImagePool imagePool;
    FeatureExtractor extractor;

    public FeatureExtraction(ImagePool imagePool, FeatureExtractor extractor) {
        this.imagePool = imagePool;
        this.extractor = extractor;
    }

    public void start() {
        float[] desc;
        for (Image image : imagePool) {
            desc=extractor.getDescriptor(image);
            image.featuresMap.put(extractor.getName(),desc);
            imagePool.edit(image.id,image);
        }
    }
}
