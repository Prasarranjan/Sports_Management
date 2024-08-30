package Dao;

import Entity.Registration;
import Entity.Sports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class jdbc {
    public static java.sql.Connection getConnection() {

        java.sql.Connection Con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SportsClub", "root", "prasar123");
            if (Con != null) {
                System.out.println("database successfully connected");
            } else {
                System.out.println("database connection failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Con;
    }
    public static List<Sports> sportname(int clubId) {
        ArrayList<Sports> list = new ArrayList<>();
        try {
            Connection con = getConnection();
            String sql = "SELECT sportsId, sportsName FROM Sports WHERE clubId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, clubId);  // Set the dynamic clubId
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Sports sport = new Sports();
                sport.setSportsId(rs.getInt("sportsId"));
                sport.setSportsName(rs.getString("sportsName"));
                list.add(sport);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static int saveDetails(Registration u){
        int result=0;
        Connection con =getConnection();
        try {
            String createUser="insert into Registration(name,email,mobile,gender,dob,image,clubId,sportsId) values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(createUser);
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getMobile());
            ps.setString(4, u.getGender ());
            ps.setString(5, u.getDob());
            ps.setString(6, u.getImage());
            ps.setInt(7, u.getClubId());
            ps.setInt(8, u.getSportsId());
            result=ps.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static List<Registration> show(int clubId){
        ArrayList<Registration> list = new ArrayList<>();
        try {
            Connection con = getConnection();
            String sql = " SELECT  ROW_NUMBER() OVER(ORDER BY r.regdId) AS slno,  r.name, r.email, r.mobile, r.image, c.clubName, s.sportsName, s.fees FROM Registration r INNER JOIN Club c ON r.clubId = c.clubId INNER JOIN Sports s ON r.sportsId = s.sportsId WHERE r.clubId = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, clubId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Registration rr=new Registration();
               rr.setRegId(rs.getInt(1));
               rr.setName(rs.getString(2));
               rr.setEmail(rs.getString(3));
               rr.setMobile(rs.getString(4));
               rr.setImage(rs.getString(5));
               rr.setClubName(rs.getString(6));
               rr.setSportsName(rs.getString(7));
               rr.setFees(rs.getDouble(8));
               list.add(rr);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public static List<Registration> getall(){
        ArrayList<Registration> list = new ArrayList<>();
        try {
            Connection con = getConnection();
            String sql = " SELECT  ROW_NUMBER() OVER(ORDER BY r.regdId) AS slno,  r.name, r.email, r.mobile, r.image, c.clubName, s.sportsName, s.fees FROM Registration r INNER JOIN Club c ON r.clubId = c.clubId INNER JOIN Sports s ON r.sportsId = s.sportsId ;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Registration rr=new Registration();
                rr.setRegId(rs.getInt(1));
                rr.setName(rs.getString(2));
                rr.setEmail(rs.getString(3));
                rr.setMobile(rs.getString(4));
                rr.setImage(rs.getString(5));
                rr.setClubName(rs.getString(6));
                rr.setSportsName(rs.getString(7));
                rr.setFees(rs.getDouble(8));
                list.add(rr);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
