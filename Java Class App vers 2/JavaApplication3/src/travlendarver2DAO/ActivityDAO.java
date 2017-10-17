/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travlendarver2DAO;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import travlendarver2.Activity;

/**
 *
 * @author kivla
 */
public class ActivityDAO extends DAO {
    
    public static List<Activity> getAll(){
        List<Activity> Activity = new ArrayList<>();
        try {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM activity");
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Activity act = new Activity();
            act.setId_Activity(rs.getInt("ID_Activity"));
            act.setId_StartLocation(rs.getInt("ID_StartLocation"));
            act.setId_Destination(rs.getInt("ID_Dastination"));
            act.setTransportationCode(rs.getString("TransportationCode"));
            act.setEventName(rs.getString("Activity_Name"));
            act.setEventDate(rs.getDate("Date"));
            act.setStartEventTime(rs.getTime("Start_Time"));
            act.setEndEventTime(rs.getTime("End_Time"));
            act.setPriority(rs.getInt("Priority_Scale"));
            Activity.add(act);
        }
        } catch (SQLException ex){
            System.out.println(ex);
        }
        disconnect();
        return Activity;
    }
    
    public static int save(Activity _activity){
        int stats = 0;
        try{
            Connection con = getConnection();
            Statement st = con.createStatement();
            
            Integer id_Activity = _activity.getId_Activity();
            Integer id_StartLocation = _activity.getId_StartLocation();
            Integer id_Destination = _activity.getId_Destination();
            String transportationCode = _activity.getTransportationCode();
            String eventName = _activity.getEventName();
            java.sql.Date eventDate = (java.sql.Date) _activity.getEventDate();
            Time startTime = _activity.getStartEventTime();
            Time endTime = _activity.getEndEventTime();
            int priority = _activity.getPriority();
            String sql = "INSERT INTO activity (ID_Activity,ID_StartLocation,ID_Destination,TransportationCode,Activity_Name,Start_Time,End_Time,Priority_Scale) VALUES(\"" + id_Activity + "\",\"" + id_StartLocation + "\",\"" + id_Destination + "\",\"" + transportationCode + "\",\"" + eventName + "\",\"" + eventDate + "\",\"" + startTime + "\",\"" + endTime + "\",\"" + priority + "\");";
            stats = st.executeUpdate(sql);
        } catch (SQLException ex){
            System.out.println(ex);
        }
        disconnect();
        return stats;
    }
}
