package grire2.Structures;

/**
 * Properties for the creation of the storer.
 * Created by Lazaros Tsochatzidis on 26/2/2016.
 */
public class StorerProperties {
    /**
     * Transactions (write-ahead-log) can be disabled with DBMaker.transactionDisable(), this will MapDB much faster.
     * However, without WAL the store gets corrupted when not closed correctly.
     */
    public boolean TRANSACTIONS=true;

    /**
     * MapDB is much faster with memory mapped files. But those cause problems on 32bit JVMs and are disabled by default.
     * Use DBMaker.fileMmapEnableIfSupported() to enable them on 32bit systems.
     */
    public boolean MMAP=false;

    /**
     * This instance cache uses more memory, but makes MapDB faster.
     */
    public boolean HASH_TABLE_CACHE=false;

    /**
     * Delete files after running. Used for tests.
     */
    public boolean DELETE_AFTER_CLOSE=false;

}
