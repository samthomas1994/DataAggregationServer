package uk.ac.bath.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Sam on 01/02/2016.
 */
public class ParseCSV {

    public void ParseCSV() {

    }

    public void importCSV() {

        String csvLocation = "/Users/Sam/FinalYearProject/CSV/Test.csv";
        try {
            FileReader csvFile = new FileReader(csvLocation);
            readCSV(csvFile);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void readCSV(FileReader csvFile) {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(csvFile);
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] items = line.split(cvsSplitBy);

                for(int i = 0; i < items.length; i++) {
                    System.out.println(items[i]);
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Done");
    }
}
