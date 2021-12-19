package ca.snmptrap;

import java.io.Console;
import java.io.File;
import java.io.IOException;


import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import org.apache.commons.lang3.StringUtils;

public class SnmpTestApp 
{
    private static final String SNMP_PREFIX_FILE_NAME = "src/main/resources/snmp.yml";
    public static void main( String[] args )
    {
        File file = new File(SNMP_PREFIX_FILE_NAME);
        SnmpPrefixList snmpPrefixList = null;
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        Console console = System.console();

        try{
            snmpPrefixList = om.readValue(file, SnmpPrefixList.class);
        } catch(StreamReadException | DatabindException ex ) {
            System.out.println("The format of the provided snmp prefix list is invalid: " + ex.toString());
            System.exit(0);
        } catch( IOException ex ) {
            System.out.println("There was a problem accessing the snmp prefix file: " + ex.toString());
            System.exit(0);
        }

        String input = "";

        while(true) {
            input = console.readLine("Input: ");
            if(StringUtils.equalsIgnoreCase(input, "exit")){
                System.exit(0);
            }
            System.out.println("Result for input: " + input + ": " + SnmpPrefixMatcher.checkListOfSnmpPrefixesForMatch(snmpPrefixList.getSnmpPrefixList(), input));
        }


    }
}
