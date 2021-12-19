package ca.snmptrap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SnmpPrefixMatcherTest {

    @Test
    public void test_checkSnmpOidPrefixForMatchWithInput_EmptyInputStringReturnsFalse(){
        String validPrefix = "someString";
        String invalidInput = "";

        assertFalse("An empty input should return false", SnmpPrefixMatcher.checkSnmpOidPrefixForMatchWithInput(validPrefix, invalidInput));

    }

    @Test
    public void test_checkSnmpOidPrefixForMatchWithInput_EmptyPrefixStringReturnsFalse(){
        String invalidPrefix = "";
        String validInput = "someString";

        assertFalse("An empty prefix should return false", SnmpPrefixMatcher.checkSnmpOidPrefixForMatchWithInput(invalidPrefix, validInput));

    }

    @Test
    public void test_checkSnmpOidPrefixForMatchWithInput_ExactMatchReturnsTrue(){
        String testSnmpString = ".1.3.6.1.4.1.9.10.137.0.1";

        assertTrue("The same input should return true", SnmpPrefixMatcher.checkSnmpOidPrefixForMatchWithInput(testSnmpString, testSnmpString));
    }

    @Test
    public void test_checkSnmpOidPrefixForMatchWithInput_LongerInputMatchesShorterPrefixReturnsTrue(){
        String testSnmpPrefix = ".1.3.6.1.4.1.9.10.137";
        String testInput = ".1.3.6.1.4.1.9.10.137.0.1";

        assertTrue("An input that contains the given prefix should return true", SnmpPrefixMatcher.checkSnmpOidPrefixForMatchWithInput(testSnmpPrefix, testInput));
    }

    @Test
    public void test_checkSnmpOidPrefixForMatchWithInput_ShorterInputMatchesLongerPrefixReturnsTrue(){
        String testInput = ".1.3.6.1.4.1.9.10.137";
        String testSnmpPrefix = ".1.3.6.1.4.1.9.10.137.0.1";

        assertTrue("An input contained in the prefix should return true", SnmpPrefixMatcher.checkSnmpOidPrefixForMatchWithInput(testSnmpPrefix, testInput));
    }

    @Test
    public void test_checkSnmpOidPrefixForMatchWithInput_InputsDoNotMatchReturnsFalse(){
        String testInput = ".1.3.6.1.4.1.9.10.137";
        String testSnmpPrefix = ".1.3.6.3.2.45";

        assertFalse("An input that does not match the prefix should return false", SnmpPrefixMatcher.checkSnmpOidPrefixForMatchWithInput(testSnmpPrefix, testInput));
    }

    @Test
    public void test_checkSnmpOidPrefixForMatchWithInput_FinalNumbersDoNotMatchReturnsFalse(){
        String testInput = ".1.3.6.1.4.1.9.10.137";
        String testSnmpPrefix = ".1.3.6.1.4.1.9.10.1371";

        assertFalse("An input that does not match the prefix should return false", SnmpPrefixMatcher.checkSnmpOidPrefixForMatchWithInput(testSnmpPrefix, testInput));
    }

        
    @Test
    public void test_checkListOfSnmpPrefixesForMatch_EmptyPrefixListReturnsFalse() {
        List<String> emptyList = new ArrayList<>();
        String someInput = "someInput";

        assertFalse("An empty list should return false", SnmpPrefixMatcher.checkListOfSnmpPrefixesForMatch(emptyList, someInput));
    }

    @Test
    public void test_checkListOfSnmpPrefixesForMatch_NullPrefixListReturnsFalse() {
        List<String> nullList = null;
        String someInput = "someInput";

        assertFalse("A null list should return false", SnmpPrefixMatcher.checkListOfSnmpPrefixesForMatch(nullList, someInput));
    }

    @Test
    public void test_checkListOfSnmpPrefixesForMatch_EmptyInputStringReturnsFalse() {
        List<String> validList = new ArrayList<>();
        validList.add("someString");
        String someInput = "";

        assertFalse("An empty input should return false", SnmpPrefixMatcher.checkListOfSnmpPrefixesForMatch(validList, someInput));
    }

    @Test
    public void test_checkListOfSnmpPrefixesForMatch_NoMatchesReturnsFalse() {
        List<String> testList = new ArrayList<>();
        testList.add(".1.3.6.1.4.1.9.10.137.0.1");
        String testInput = ".1.3.6.1.4.1.9.10.23";

        assertFalse("An input that does not match any prefix should return false", SnmpPrefixMatcher.checkListOfSnmpPrefixesForMatch(testList, testInput));
    }

    @Test
    public void test_checkListOfSnmpPrefixesForMatch_FinalEntryInListMatchesReturnsTrue() {
        List<String> testList = new ArrayList<>();
        testList.add(".1.3.6.1.4.1.9.10.137.0.1");
        testList.add(".1.3.6.1.4.1.9.10.25");
        testList.add(".1.3.6.1.4.1.9.10.23");

        String testInput = ".1.3.6.1.4.1.9.10.23";

        assertTrue("An input that does match a prefix should return true", SnmpPrefixMatcher.checkListOfSnmpPrefixesForMatch(testList, testInput));
    }
}
