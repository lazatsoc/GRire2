package grire2;

import grire2.FeatureExtractors.All64OneExtractor;
import grire2.FeatureExtractors.FeatureExtractor;
import grire2.FeatureExtractors.RandomExtractor;
import grire2.Processes.FeatureExtraction;
import grire2.Processes.SimpleSearch;
import grire2.Similarities.L2;
import grire2.Structures.Image;
import grire2.Structures.ImagePool;
import grire2.Structures.Storer;
import grire2.Structures.StorerProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Lazaros Tsochatzidis on 26/2/2016.
 */
public class    StructureTests {

    Storer storer;
    ImagePool pool;
    StorerProperties properties=new StorerProperties();

    @Before
    public void setUp() throws Exception {
        storer=new Storer("testdb",properties);
        pool=new ImagePool(storer,"testpool");
    }

    @Test
    public void testProcess() throws Exception {

        pool.add(new Image("./test_dataset/ukbench00000.jpg"));
        pool.add(new Image("./test_dataset/ukbench00001.jpg"));
        pool.add(new Image("./test_dataset/ukbench00012.jpg"));
        pool.add(new Image("./test_dataset/ukbench00013.jpg"));
        pool.add(new Image("./test_dataset/ukbench00016.jpg"));
        pool.add(new Image("./test_dataset/ukbench00017.jpg"));

        resetStorer();

        assertEquals(pool.size(),6);
        assertEquals("./test_dataset/ukbench00000.jpg",pool.get(0).path);
        assertEquals(pool.get(0).id,0);
        assertEquals("./test_dataset/ukbench00001.jpg",pool.get(1).path);
        assertEquals(pool.get(1).id,1);
        assertEquals("./test_dataset/ukbench00012.jpg",pool.get(2).path);
        assertEquals(pool.get(2).id,2);
        assertEquals("./test_dataset/ukbench00013.jpg",pool.get(3).path);
        assertEquals(pool.get(3).id,3);
        assertEquals("./test_dataset/ukbench00016.jpg",pool.get(4).path);
        assertEquals(pool.get(4).id,4);
        assertEquals("./test_dataset/ukbench00017.jpg",pool.get(5).path);
        assertEquals(pool.get(5).id,5);

        FeatureExtractor extractor = new All64OneExtractor();
        FeatureExtraction extraction=new FeatureExtraction(pool, extractor);
        extraction.start();

        resetStorer();

        assertEquals(pool.get(0).featuresMap.size(),1);
        assertEquals(pool.get(1).featuresMap.size(),1);
        assertEquals(pool.get(2).featuresMap.size(),1);
        assertEquals(pool.get(3).featuresMap.size(),1);
        assertEquals(pool.get(4).featuresMap.size(),1);
        assertEquals(pool.get(5).featuresMap.size(),1);

        String featurename="64_1_TestExtractor";
        float[] desc=new float[64];
        Arrays.fill(desc,1);

        assertArrayEquals(pool.get(0).featuresMap.get(featurename),desc,0.0001f);
        assertArrayEquals(pool.get(1).featuresMap.get(featurename),desc,0.0001f);
        assertArrayEquals(pool.get(2).featuresMap.get(featurename),desc,0.0001f);
        assertArrayEquals(pool.get(3).featuresMap.get(featurename),desc,0.0001f);
        assertArrayEquals(pool.get(4).featuresMap.get(featurename),desc,0.0001f);
        assertArrayEquals(pool.get(5).featuresMap.get(featurename),desc,0.0001f);

        extractor = new RandomExtractor();
        extraction=new FeatureExtraction(pool, extractor);
        extraction.start();

        resetStorer();

        SimpleSearch simpleSearch=new SimpleSearch(pool,new L2(),extractor);
        Long[] ranked = simpleSearch.query(pool.get(2),2);

        assertEquals((Long) 2L,ranked[0]);
    }

    private void resetStorer() {
        storer.close();
        storer=new Storer("testdb",properties);
        pool=new ImagePool(storer,"testpool");
    }

    @After
    public void tearDown() throws Exception {
        storer.close();
        properties.DELETE_AFTER_CLOSE=true;
        storer=new Storer("testdb",properties);
        storer.close();

    }
}
