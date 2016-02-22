package uk.ac.bath.Web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uk.ac.bath.classes.Activity;
import uk.ac.bath.classes.UserDetails;
import uk.ac.bath.hibernate.AutowiredDatabase;
import uk.ac.bath.hibernate.PersonDAO;
import uk.ac.bath.utils.ParseCSV;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Sam on 11/02/2016.
 */
@CrossOrigin(origins = "http://people.bath.ac.uk")
@RestController
public class WebServer {

    @Autowired
    AutowiredDatabase database;

    @RequestMapping("/test")
    public UserDetails test() {
        System.out.println("Web method called");
        UserDetails userDetails = new UserDetails("First name", "Last name", "Username", "Password");
        return userDetails;
    }


    @RequestMapping("/userFromUsernameAndPassword")
    public List<UserDetails> userFromUsernameAndPassword(@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        List<UserDetails> userDetails = database.getDatabase().userFromUsernameAndPassword(username, password);
        return userDetails;
    }

    @RequestMapping("/activitiesFromUser")
    public List<Activity> activitiesFromUser(@RequestParam(value="userId") Long userId) {
        UserDetails user = database.getDatabase().userFromId(userId);
        List<Activity> activities = database.getDatabase().activitiesFromUser(user);
        return activities;
    }

    @RequestMapping(value = "/fileupload", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    @ResponseBody
    public HttpEntity<UploadedFile> uploadMultipart(
            final HttpServletRequest request,
            final HttpServletResponse response,
            @RequestParam("file") final MultipartFile multiPart) {

        //handle regular MultipartFile

        // IE <=9 offers to save file, if it is returned as json, so set content type to plain.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new HttpEntity<UploadedFile>(headers);
    }

    @RequestMapping(value = "/uploadMyFile", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(MultipartHttpServletRequest request)
            throws Exception {
        Iterator<String> itrator = request.getFileNames();
        MultipartFile multiFile = request.getFile(itrator.next());
        try {
            // just to show that we have actually received the file
            System.out.println("File Length:" + multiFile.getBytes().length);
            System.out.println("File Type:" + multiFile.getContentType());
            String fileName=multiFile.getOriginalFilename();
            System.out.println("File Name:" +fileName);
            String path=request.getServletContext().getRealPath("/");

            //making directories for our required path.
            byte[] bytes = multiFile.getBytes();
            File directory=    new File(path+ "/uploads");
            directory.mkdirs();
            // saving the file
            File file=new File(directory.getAbsolutePath()+System.getProperty("file.separator")+"test.csv");

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(file));
            stream.write(bytes);
            stream.close();

            ParseCSV csvParser = new ParseCSV(database);
            csvParser.importCSV(file);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception("Error while loading the file");
        }
        return toJson("File Uploaded successfully.");
    }

    public String toJson(Object data)
    {
        ObjectMapper mapper=new ObjectMapper();
        StringBuilder builder=new StringBuilder();
        try {
            builder.append(mapper.writeValueAsString(data));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return builder.toString();
    }
}

