package ca.snmptrap;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SnmpPrefixListTest {
    
    @Test
    public void test_getSnmpPrefixList_ReturnsCorrectList(){
        List<String> testList = new ArrayList<>();

        SnmpPrefixList testSnmpPrefixList = new SnmpPrefixList(testList);

        assertTrue("The get method should return the same object that it was set with", testList.equals(testSnmpPrefixList.getSnmpPrefixList()));
    }
}
