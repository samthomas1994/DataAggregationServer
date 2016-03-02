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
public class ImportCSV extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
        //Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        //String fileName = filePart.getSubmittedFileName();
        //InputStream fileContent = filePart.getInputStream();
        // ... (do your job here)
        System.out.println("Form submitted");
    }
}
