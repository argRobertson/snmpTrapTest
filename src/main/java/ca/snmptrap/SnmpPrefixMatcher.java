package ca.snmptrap;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class SnmpPrefixMatcher {

    /**
     * This function iterates over a list of strings, using 
     * {@link #checkSnmpOidPrefixForMatchWithInput}
     * to check each entry for a match with <code>inputToMatch</code>
     * if any match returns true, else false.
     * @param snmpOIDPrefixList
     * @param inputToMatch
     * @return
     */
    public static boolean checkListOfSnmpPrefixesForMatch(List<String> snmpOIDPrefixList, String inputToMatch){

        if(StringUtils.isBlank(inputToMatch) || snmpOIDPrefixList == null || snmpOIDPrefixList.isEmpty()) {
            return false;
        }

        for(String snmpOIDPrefixToMatch: snmpOIDPrefixList) {
            if ( checkSnmpOidPrefixForMatchWithInput(snmpOIDPrefixToMatch, inputToMatch)) {
                return true;
            }
        }

        return false;
    }
    
    /**
     * This function checks whether or not the <code>inputToMatch</code> matches the given prefix, it does this in three ways
     * <ul>
     *  <li> Checks if the <code>snmpOIDPrefixToMatch</code> is contained in <code>inputToMatch</code>
     *  <li> Check if the <code>snmpOIDPrefixToMatch</code> contains <code>inputToMatch</code>
     *  <li> Check if the <code>snmpOIDPrefixToMatch</code> matches <code>inputToMatch</code> exactly
     *  </ul>
     * 
     * @param snmpOIDPrefixToMatch
     * @param inputToMatch
     * @return
     */
    public static boolean checkSnmpOidPrefixForMatchWithInput(String snmpOIDPrefixToMatch, String inputToMatch){

        if(StringUtils.isBlank(snmpOIDPrefixToMatch) || StringUtils.isBlank(inputToMatch)){
            return false;
        }

        String[] snmpOIDPrefixToMatchSplit = snmpOIDPrefixToMatch.split("\\.");
        String[] inputToMatchSplit = inputToMatch.split("\\.");

        for(int i = 0; i < inputToMatchSplit.length; i++) {
            //If this is true all characters have matched and the entered input is longer than the supplied prefix
            if(snmpOIDPrefixToMatchSplit.length == i) {
                return true;
            }
            //Check for a match on the current character, return false if it fails.
            if(!snmpOIDPrefixToMatchSplit[i].equals(inputToMatchSplit[i])){
                return false;
            }
        }

        return true;
    }
}
