package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import DB.ConnectDB;
import dto.LoginLogDTO;

public class WorkLogDAO {
	// 해당 EMPLOYEE가 가장 마지막에 로그인한 기록 가져오기
    public LoginLogDTO LastLoginLog(int empId) {
        String lastLogSql = """
                SELECT l.log_id, l.emp_id, l.login_time, l.logout_time,e.user_name, e.user_id, e.hourly_wage
                   FROM login_logs l
                   JOIN employees e ON l.emp_id = e.emp_id
                   WHERE l.emp_id = ?
                   ORDER BY l.log_id DESC
                   FETCH FIRST 1 ROWS ONLY
            """;

        try(Connection conn = ConnectDB.getConnectionDB();
				PreparedStatement preparedStatement = conn.prepareStatement(lastLogSql)) {
            preparedStatement.setInt(1, empId);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
            	LoginLogDTO log = new LoginLogDTO();
                log.setLogId(rs.getInt("log_id"));
                log.setEmpId(rs.getInt("emp_id"));
                log.setLoginTime(rs.getTimestamp("login_time"));
                log.setLogoutTime(rs.getTimestamp("logout_time"));
                log.setWorkMinutes(rs.getInt("work_minutes"));
                log.setDailyWage(rs.getInt("daily_wage"));

                return log;
            }
        } catch (SQLException e) {
            System.out.println("LOGIN_LOGS SELECT 오류" + e.getMessage());
        }
        return null;
    }
	
	// 로그아웃 시간 업데이트
	public void updateLogout(int logId, LocalDateTime logoutTime, long workMinutes, int dailyWage) {
	    String logoutLog = """
	            UPDATE LOGIN_LOGS
	            SET LOGOUT_TIME = ?, WORK_MINUTES = ?, DAILY_WAGE = ?
	            WHERE LOG_ID = ?
	            """;

	    try(Connection conn = ConnectDB.getConnectionDB();
				PreparedStatement preparedStatement = conn.prepareStatement(logoutLog)) {
		        preparedStatement.setTimestamp(1, Timestamp.valueOf(logoutTime)); // ✅ 날짜+시간
		        preparedStatement.setLong(2, workMinutes);
		        preparedStatement.setInt(3, dailyWage);
		        preparedStatement.setInt(4, logId);
		        preparedStatement.executeUpdate();

	    } catch (SQLException e) {
	        System.out.println("LOGOUT UPDATE 오류" + e.getMessage());
	    }
	}
}
