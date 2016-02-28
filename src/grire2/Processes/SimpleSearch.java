package grire2.Processes;

import grire2.FeatureExtractors.FeatureExtractor;
import grire2.Similarities.Similarity;
import grire2.Structures.Image;
import grire2.Structures.ImagePool;

import java.util.TreeMap;

/**
 * Provides functionality for performing queries to a DB.
 * Created by Lazaros Tsochatzidis on 26/2/2016.
 */
public class SimpleSearch {
    ImagePool imagePool;
    Similarity similarity;
    String featureName;
    long imagePoolSize;

    public SimpleSearch(ImagePool imagePool, Similarity similarity, FeatureExtractor extractor) {
        this.imagePool = imagePool;
        this.similarity = similarity;
        this.featureName=extractor.getName();
        imagePoolSize=imagePool.size();
    }

    public SimpleSearch(ImagePool imagePool, Similarity similarity, String featureName) {
        this.imagePool = imagePool;
        this.similarity = similarity;
        this.featureName=featureName;
        imagePoolSize=imagePool.size();
    }

    /**
     * Query database with the specified image.
     * @param query
     * @return
     */
    public Long[] query(Image query, int amount) {

        TreeMap<Float,Long> distanceMap;
        if (similarity.getAscending())
            distanceMap = new TreeMap<>();
        else
            distanceMap = new TreeMap<>((o1, o2) -> o2.compareTo(o1));

        float[] qdesc=query.featuresMap.get(featureName);
        for (Image image : imagePool) distanceMap.put(similarity.compare(qdesc,image.featuresMap.get(featureName)),image.id);
        Long[] ret=new Long[amount];
        ret=distanceMap.values().toArray(ret);
        return ret;
    }
}
