package grire2.Similarities;

/**
 * Provides an interface for all Similarity classes.
 * Created by Lazaros Tsochatzidis on 26/2/2016.
 */
public interface Similarity {
    float compare(float[] d1, float[] d2);
    boolean getAscending();
}
