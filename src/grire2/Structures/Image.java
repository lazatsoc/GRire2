package grire2.Structures;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lazaros Tsochatzidis on 26/2/2016.
 */
public class Image implements Serializable{
    public Image(String path) {
        this.path = path;
        featuresMap=new HashMap<>();
    }

    public String path;
    public long id;
    public Map<String,float[]> featuresMap;
}
