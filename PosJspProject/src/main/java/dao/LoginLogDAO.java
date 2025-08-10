package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import DB.ConnectDB;

public class LoginLogDAO {
    public void insertLoginLog(int empId) {
        String sql = "insert into login_logs(log_id, emp_id, login_time) values(login_logs_seq.nextval, ?, sysdate)";
        
        try(Connection conn = ConnectDB.getConnectionDB();
                PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, empId);
            pre.executeUpdate();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}