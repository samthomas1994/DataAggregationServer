package uk.ac.bath.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

/**
 * Created by Sam on 01/02/2016.
 */
@CrossOrigin(origins = "http://people.bath.ac.uk", methods = RequestMethod.POST)
@WebServlet("/upload")
@Controller
@MultipartConfig
public class ParseCSV extends HttpServlet {

//    public void ParseCSV() {
//
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = filePart.getSubmittedFileName();
        InputStream fileContent = filePart.getInputStream();
        // ... (do your job here)
        System.out.println("Form submitted");
    }

//    public void importCSV() {
//
//        String csvLocation = "/Users/Sam/FinalYearProject/CSV/Test.csv";
//        try {
//            FileReader csvFile = new FileReader(csvLocation);
//            readCSV(csvFile);
//        } catch(FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }

//    public void readCSV(FileReader csvFile) {
//
//        BufferedReader br = null;
//        String line = "";
//        String cvsSplitBy = ",";
//
//        try {
//
//            br = new BufferedReader(csvFile);
//            while ((line = br.readLine()) != null) {
//
//                // use comma as separator
//                String[] items = line.split(cvsSplitBy);
//
//                for(int i = 0; i < items.length; i++) {
//                    System.out.println(items[i]);
//                }
//
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        System.out.println("Done");
//    }
}
