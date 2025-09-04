package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

import DB.ConnectDB;
import dao.WorkLogDAO;
import dto.LoginLogDTO;

public class WorkWageService {
	public static class LoginResult {
        private int logId;
        private long workMinutes;
        private int dailyWage;
        private LocalDateTime logoutTime;

        // getter / setter
        public int getLogId() { return logId; }
        public void setLogId(int logId) { this.logId = logId; }

        public long getWorkMinutes() { return workMinutes; }
        public void setWorkMinutes(long workMinutes) { this.workMinutes = workMinutes; }

        public int getDailyWage() { return dailyWage; }
        public void setDailyWage(int dailyWage) { this.dailyWage = dailyWage; }

        public LocalDateTime getLogoutTime() { return logoutTime; }
        public void setLogoutTime(LocalDateTime logoutTime) { this.logoutTime = logoutTime; }
    }
	
	// 로그아웃 처리 + 일급 계산
	public LoginResult logout(LoginLogDTO dto) {
        WorkLogDAO dao = new WorkLogDAO();

        // 마지막 로그인 기록 가져오기
        LoginLogDTO lastLog = dao.LastLoginLog(dto.getEmpId());
        if(lastLog == null) {
            return null; // 로그인 기록이 없으면 null 반환
        }

        LocalDateTime logoutTime = LocalDateTime.now();
        long workMinutes = java.time.Duration.between(
                lastLog.getLoginTime().toLocalDateTime(), logoutTime).toMinutes();

        // 일급 계산 (분 단위)
        int dailyWage = (int)(workMinutes * lastLog.getHourlyWage() / 60);

        // DB에 로그아웃 기록 업데이트
        dao.updateLogout(lastLog.getLogId(), logoutTime, workMinutes, dailyWage);

        // 결과 객체 생성
        LoginResult result = new LoginResult();
        result.setLogId(lastLog.getLogId());
        result.setWorkMinutes(workMinutes);
        result.setDailyWage(dailyWage);
        result.setLogoutTime(logoutTime);

        return result;
    }
}
