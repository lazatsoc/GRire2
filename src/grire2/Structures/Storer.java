package grire2.Structures;

import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

import java.io.File;

/**
 * This class provides access to low-level operations to the higher-level Structures.
 * Created by Lazaros Tsochatzidis on 26/2/2016.
 */
public class Storer {
    private String path;
    private StorerProperties properties;
    private DB db;

    /**
     * Create a Storer that writes/reads to the specified file with the specified properties.
     * @param path
     * @param properties
     */
    public Storer(String path, StorerProperties properties) {
        this.path = path;
        this.properties = properties;

        DBMaker.Maker maker = DBMaker.fileDB(new File(path)).closeOnJvmShutdown();
        if (!properties.TRANSACTIONS) maker=maker.transactionDisable();
        if (properties.MMAP) maker=maker.fileMmapEnableIfSupported();
        if (properties.HASH_TABLE_CACHE) maker=maker.cacheHashTableEnable();
        if (properties.DELETE_AFTER_CLOSE) maker=maker.deleteFilesAfterClose();

        db=maker.make();
    }

    public DB getDB() {
        return db;
    }

    public String getPath() {
        return path;
    }

    public StorerProperties getProperties() {
        return properties;
    }

    public <T1,T2> HTreeMap<T1, T2> getHashMap(String name){
        return db.hashMap(name);
    }

    public <T1,T2> BTreeMap<T1, T2> getBTreeMap(String name){
        return db.treeMap(name);
    }

    /**
     * Commits to the changes made to any Structure that is linked to this storer.
     * Structures must handle the commiting.
     */
    public void commit() {
        db.commit();
    }

    /**
     * Compacts the DB in order to get less space. This should be done less frequently.
     */
    public void compact(){
        db.compact();
    }

    /**
     * Closes the DB.
     */
    public void close() {
        db.close();
    }

    /**
     * Reopens the current storer with new properties.
     * @param properties
     */
    public void reopen(StorerProperties properties){
        db.close();
        this.properties=properties;
        DBMaker.Maker maker = DBMaker.fileDB(new File(path)).closeOnJvmShutdown();
        if (!properties.TRANSACTIONS) maker=maker.transactionDisable();
        if (properties.MMAP) maker=maker.fileMmapEnableIfSupported();
        if (properties.HASH_TABLE_CACHE) maker=maker.cacheHashTableEnable();
        if (properties.DELETE_AFTER_CLOSE) maker=maker.deleteFilesAfterClose();
        db=maker.make();
    }
}
