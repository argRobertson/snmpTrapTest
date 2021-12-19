package ca.snmptrap;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Simple class used as a receptacle for list of prefixes from snmp.yaml when parsed by the jackson library.
 */
public class SnmpPrefixList {

    @JsonProperty("trap-type-oid-prefix")
    private ArrayList<String> snmpPrefixList;
    
    public SnmpPrefixList(List<String> snmpPrefixList){
        this.snmpPrefixList = new ArrayList<>(snmpPrefixList);
    }

    public SnmpPrefixList(){}

    public ArrayList<String> getSnmpPrefixList(){
        return snmpPrefixList;
    }
}
