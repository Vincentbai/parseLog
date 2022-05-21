package au.com.vincentbai;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testCountAndSort(){
    	
    	
    	ArrayList<String> testList = new ArrayList<String>();
    	
    	testList.add("aaa");
    	testList.add("aaa");
    	testList.add("aaa");
    	testList.add("bbb");
    	testList.add("bbb");
    	testList.add("ccc");
    	
    	Map<String, Long> testResult = App.countAndSort(testList);
    	
        assertTrue( testResult.keySet().toArray()[0] == "aaa" );
        assertTrue( testResult.keySet().toArray()[1] == "bbb" );
        assertTrue( testResult.keySet().toArray()[2] == "ccc" );
    }
}
