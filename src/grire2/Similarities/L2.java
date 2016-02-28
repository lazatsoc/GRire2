package grire2.Similarities;

/**
 * Implements the L2 (or Euclidean) distance.
 * Created by Lazaros Tsochatzidis on 26/2/2016.
 */
public class L2 implements Similarity{
    @Override
    public float compare(float[] d1, float[] d2) {
        float d=0;
        for (int i=0;i<d1.length;i++) d+=(d1[i]-d2[i])*(d1[i]-d2[i]);
        return d;
    }

    @Override
    public boolean getAscending() {
        return true;
    }
}
