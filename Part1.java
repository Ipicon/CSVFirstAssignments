import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public void tester() {
        FileResource fr  = new FileResource();  
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Nauru"));
        parser = fr.getCSVParser();
        //listExportersTwoProducts(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
        //System.out.println(numberOfExporters(parser, "cocoa"));
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
    }
    public void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2) {
        String notFound = "NOT FOUND";
        for(CSVRecord record : parser) {
            String countryExports = record.get("Exports");
            if(countryExports.contains(exportItem1) && countryExports.contains(exportItem2)) {
                notFound = "";
                System.out.println(record.get("Country"));
            }
        }
        if(!notFound.isEmpty())
            System.out.println(notFound);
    }
    public String countryInfo(CSVParser parser, String country) {
        String countryInfo = "NOT FOUND";
        for(CSVRecord record : parser) {
            String countryName = record.get("Country");
            if(countryName.contains(country)) {
                countryInfo = (countryName + ": " +
                               record.get("Exports") + ": " +
                               record.get("Value (dollars)"));
            }
        }
        return countryInfo;
    }    
    public int numberOfExporters(CSVParser parser,String exportItem) {
        int countryCounter = 0;
        for(CSVRecord record : parser) {
            String countryExport = record.get("Exports");
            if(countryExport.contains(exportItem))
                countryCounter++;
        }
        return countryCounter;
    }
    public void bigExporters(CSVParser parser,String amount) {
        for(CSVRecord record : parser) {
            String money = record.get("Value (dollars)");
            if(money.length() > amount.length()) 
                System.out.println(record.get("Country") + " " + money); 
        }
    }
}


