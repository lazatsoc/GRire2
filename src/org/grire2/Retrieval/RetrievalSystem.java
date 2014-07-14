package org.grire2.Retrieval;

import java.util.List;

/**
 * Created by Lazaros on 9/6/2014.
 */
public interface RetrievalSystem {
    public void index(String _name);
    public List<RankedItem> query(String id, String indexName);
}
