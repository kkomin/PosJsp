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
	    String sql = """
	        SELECT l.log_id, l.emp_id, l.login_time, l.logout_time,
	               l.work_minutes, l.daily_wage,
	               e.hourly_wage, e.user_name
	        FROM login_logs l
	        JOIN employees e ON l.emp_id = e.emp_id
	        WHERE l.emp_id = ?
	        ORDER BY l.log_id DESC
	        FETCH FIRST 1 ROWS ONLY
	    """;

	    try (Connection conn = ConnectDB.getConnectionDB();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, empId);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                LoginLogDTO log = new LoginLogDTO();
	                log.setLogId(rs.getInt("log_id"));
	                log.setEmpId(rs.getInt("emp_id"));

	                Timestamp loginTs = rs.getTimestamp("login_time");
	                Timestamp logoutTs = rs.getTimestamp("logout_time");

	                // work_minutes, daily_wage - null 허용이므로 getInt 후 wasNull 체크
	                int wm = rs.getInt("work_minutes");

	                // 직원의 기본 시급도 dto에 담기
	                int hw = rs.getInt("hourly_wage");

	                return log;
	            } else {
	                System.out.println("[DEBUG] LastLoginLog: no row for empId=" + empId);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("LOGIN_LOGS SELECT 오류: " + e.getMessage());
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
