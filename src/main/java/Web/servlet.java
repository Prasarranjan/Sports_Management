package Web;

import Entity.Registration;
import Entity.Sports;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import Dao.jdbc;
import jakarta.servlet.http.Part;

@WebServlet("/servlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,       // 10MB
        maxRequestSize = 1024 * 1024 * 50)    // 50MB
public class servlet extends HttpServlet {
    private static final String UPLOAD_DIR = "image";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html");
      PrintWriter out = resp.getWriter();
      String event = req.getParameter("event");

        if ("sports".equals(event)) {  // Correct comparison
            int clubId = Integer.parseInt(req.getParameter("clubId"));  // Get the clubId from request
            jdbc ed = new jdbc();
            List<Sports> sportsList = ed.sportname(clubId);
            Sports sp=new Sports();
            Gson gson = new GsonBuilder().create();
            String jsonResponse = gson.toJson(sportsList);  // Convert list to JSON
            out.print(jsonResponse);  // Return JSON response to AJAX
        } else if ("player".equals(event)) {


            try {
                String name = req.getParameter("name");
                String email = req.getParameter("email");
                String mobile = req.getParameter("mobile");
                String dob = req.getParameter("dob");
                String gender = req.getParameter("gender");
                int clubId = Integer.parseInt(req.getParameter("clubName"));
                int sportsId = Integer.parseInt(req.getParameter("sportsId"));

                // Get the image file from the form
                Part filePart = req.getPart("image");
                System.out.println(filePart);
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                if (filePart != null) {
                    String fs = filePart.getSubmittedFileName();
                    System.out.println("File Name: " + fs);
                } else {
                    System.out.println("No file part found in the request.");
                }
                // Define the upload directory
              //  String uploadPath = getServletContext().getRealPath("/image/")+ File.separator+UPLOAD_DIR;
                String uploadPath = "/home/prasar/Documents/sports/image";

                File uploadDir = new File(uploadPath);
                String contentType = req.getContentType();
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();  // Use mkdirs to create parent directories if necessary
                }
                System.out.println("Content-Type: " + contentType);
                System.out.println(uploadPath);
               // File uploadDir = new File(uploadPath);



                // Save the file in the specified directory
                filePart.write(uploadPath + File.separator + fileName);
                System.out.println("Upload Path: " + uploadPath);


                Registration u = new Registration();
                u.setName(name);
                u.setEmail(email);
                u.setMobile(mobile);
                u.setDob(dob);
                u.setGender(gender);
                u.setImage(fileName);
                u.setClubId(clubId);
                u.setSportsId(sportsId);

                int status = jdbc.saveDetails(u);

                if (status == 1) {
                    out.println("done");
                } else {
                    out.println("error");
                }

            } catch (Exception e) {
                e.printStackTrace();
                out.println("Invalid number format");
            }
        } else if (event.equals("viewplayer")) {
            int id=Integer.parseInt(req.getParameter("ClubId"));
            jdbc ed= new jdbc();
            List<Registration> ser=ed.show(id);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson  gson = gsonBuilder.create();
            String JSONObject = gson.toJson(ser);
            out.print(JSONObject);
        } else if (event.equals("viewplayerall")) {
            //System.out.println(id);
            jdbc ed= new jdbc();
            List<Registration> ser=ed.getall();
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson  gson = gsonBuilder.create();
            String JSONObject = gson.toJson(ser);
            out.print(JSONObject);
        }
    }
}
