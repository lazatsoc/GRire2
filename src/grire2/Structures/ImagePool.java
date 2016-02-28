package grire2.Structures;

import org.mapdb.HTreeMap;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

/**
 * This structure allows the storage of the data that concern the imported images. It serves as base to access images.
 * Created by Lazaros Tsochatzidis on 26/2/2016.
 */
public class ImagePool implements Iterable<Image>{
    private Storer storer;
    private String name;
    private HTreeMap<Long,Image> map;
    private long nextKey=0;

    /**
     * Creates an ImagePool on the db defined by the storer with a specified name.
     * @param storer
     * @param name
     */
    public ImagePool(Storer storer, String name) {
        this.storer = storer;
        this.name = name;
        map=storer.getHashMap("pool:"+name);

        Set<Long> longs = map.keySet();
        Optional<Long> max = longs.stream().max((o1, o2) -> o1.compareTo(o2));
        if (max.isPresent()) nextKey= max.get() + 1;
    }

    /**
     * Adds a new image into the pool and assigns an id to it.
     * @param images
     */
    public void add(Image... images) {
        for (Image image : images) {
            map.put(nextKey, image);
            image.id = nextKey;
            nextKey++;
        }
        storer.commit();
    }

    public void edit(long id, Image image){
        map.put(id,image);
        storer.commit();
    }

    /**
     * Return the number of images contained in the ImagePool.
     * @return
     */
    public long size() {
        return map.size();
    }

    /**
     * Returns the image stored under the specified id.
     * @param id
     * @return
     */
    public Image get(long id) {
        return map.get(id);
    }

    @Override
    public Iterator<Image> iterator() {
        return map.values().iterator();
    }
}
