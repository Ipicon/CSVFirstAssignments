import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class Part2 {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestRecord = null;
        for(CSVRecord record : parser) {
            if(coldestRecord == null)
                coldestRecord = record;
            else {
                double coldestDouble = Double.parseDouble(coldestRecord.get("TemperatureF"));
                double recordDouble = Double.parseDouble(record.get("TemperatureF"));
                if(recordDouble < coldestDouble)
                coldestRecord = record;
            }
        }
        return coldestRecord;
    }
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        System.out.println("The coldest temp is: " + record.get("TemperatureF") +
                            " at: " + record.get("TimeEST"));
    }
    public String fileWithColdestTemperature() {
     DirectoryResource dr = new DirectoryResource();
     CSVRecord coldestRecord = null;
     File coldestF = null;
     String coldestDay = "";
     CSVParser parser = null;
     for(File f : dr.selectedFiles()) {
         FileResource fr = new FileResource(f);
         CSVRecord record = coldestHourInFile(fr.getCSVParser());
         if(coldestRecord == null) {
             coldestRecord = record;
             coldestF = f;
             parser = fr.getCSVParser();
         }
         else {
             double coldestDouble = Double.parseDouble(coldestRecord.get("TemperatureF"));
             double recordDouble = Double.parseDouble(record.get("TemperatureF"));      
             if(recordDouble < coldestDouble && recordDouble != -9999) {
                 coldestRecord = record;
                 coldestF = f;
                 parser = fr.getCSVParser();                 
             }
         }
     }
     for(CSVRecord record : parser) {
         coldestDay = (coldestDay + record.get("DateUTC") +
                       " " + record.get("DateUTC") + " " +
                       record.get("TemperatureF") + "\n");
     }
     String coldestData = ("Coldest day was in file: " + coldestF.getName() +
                           "\nColdest temperature on that day was " + coldestRecord.get("TemperatureF") +
                           "\nAll the temperatures on the coldest day were:\n" + coldestDay);
     return coldestData;
    }
    public void testFileWithColdestTemperature() {
        System.out.println(fileWithColdestTemperature());
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord humidityRecord = null;
        for(CSVRecord record : parser) {
            if(humidityRecord == null)
                humidityRecord = record;
            else if(!(record.get("Humidity")).equals("N/A")) {
                double humidityDouble = Double.parseDouble(humidityRecord.get("Humidity"));
                double recordDouble = Double.parseDouble(record.get("Humidity"));
                if(recordDouble < humidityDouble)
                    humidityRecord = record;
            }
        }
        return humidityRecord;
    }
    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidity = null;
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord record = lowestHumidityInFile(parser);
            if(lowestHumidity == null)
                lowestHumidity = record;
            else {
                double lowestDouble = Double.parseDouble(lowestHumidity.get("Humidity"));
                double recordDouble = Double.parseDouble(record.get("Humidity"));
                if(recordDouble < lowestDouble)
                    lowestHumidity = record;
            }
        }
        return lowestHumidity;
    }
    public void testLowestHumidityInManyFiles() {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + record.get("Humidity") + " at " + record.get("DateUTC"));
    }
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + record.get("Humidity") + " at " + record.get("DateUTC"));
    }
    public double averageTemperatureInFile(CSVParser parser) {
        Double temp = 0.0;
        double counter = 0.0;
        for(CSVRecord record : parser) {
            temp = temp + Double.parseDouble(record.get("TemperatureF"));
            counter++;
        }
        double answer = 0.0;
        if(counter != 0.0)
            answer = temp/counter + 0.0;
        return answer;
    }
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double avg = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + avg);
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double temp = 0.0;
        double counter = 0.0;
        for(CSVRecord record : parser) {
            if(Double.parseDouble(record.get("Humidity")) >= value) {
                temp = temp + Double.parseDouble(record.get("TemperatureF"));                
                counter++;
            }
        }
        double answer = 0.0;
        if(counter != 0.0)
            answer = temp/counter + 0.0;
        return answer;
    }
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double avg = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if(avg == 0)
            System.out.println("No temperatures with that humidity");
        else
            System.out.println("Average temp when high humidity is " + avg);
    }
}