package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DB.ConnectDB;
import dto.LoginLogDTO;

public class LoginLogDAO {
    public LoginLogDTO insertLoginLog(int empId) {
        LoginLogDTO loginLog = null;
        
        String insertSql = "insert into login_logs(log_id, emp_id, login_time) values(login_logs_seq.nextval, ?, sysdate)";
        String selectSql = "select log_id, emp_id, login_time, logout_time, work_minutes, daily_wage from login_logs " +
        "where log_id = (select max(log_id) from login_logs where emp_id = ?)";
        
        
        try(Connection conn = ConnectDB.getConnectionDB()) {
            try (PreparedStatement pre = conn.prepareStatement(insertSql)) {
                pre.setInt(1, empId);
                pre.executeUpdate();                
            }
            try (PreparedStatement pre = conn.prepareStatement(selectSql)) {
                pre.setInt(1, empId);
                try(ResultSet rs = pre.executeQuery()) {
                    if(rs.next()) {
                        loginLog = new LoginLogDTO();
                        loginLog.logId = rs.getInt("log_id");
                        loginLog.empId = rs.getInt("emp_id");
                        loginLog.loginTime = rs.getDate("login_time");
                        loginLog.logoutTime = rs.getDate("logout_time");
                        loginLog.workMinutes = rs.getInt("work_minutes");
                        loginLog.dailyWage = rs.getInt("daily_wage");
                    }
                }
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return loginLog;
    }
}