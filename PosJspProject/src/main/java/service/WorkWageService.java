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
	// 로그아웃 처리 + 일급 계산
    public LoginResult logout(LoginLogDTO user) throws SQLException {
        try (Connection connection = ConnectDB.getConnectionDB()) {
            WorkLogDAO dao = new WorkLogDAO();

            // 마지막 로그인 기록 가져오기
            LoginLogDTO lastLog = dao.LastLoginLog(user.getEmpId());

            if (lastLog == null) {
                return new LoginResult(0, 0); // 로그인 기록 없음
            }

            Timestamp loginTimestamp = lastLog.getLoginTime();
            LocalDateTime loginTime = loginTimestamp.toLocalDateTime();
            LocalDateTime logoutTime = LocalDateTime.now();

            // 근무 시간 계산 (분)
            long workMinutes = Duration.between(loginTime, logoutTime).toMinutes();

            // DB에서 기본 시급 가져오기
            int hourlyWage = lastLog.getHourlyWage();
            if (hourlyWage <= 0) {
                System.out.println("[WARN] logout: hourlyWage is " + hourlyWage + " for empId=" + user.getEmpId());
            }

            // 일급 계산
            int dailyWage = (int) Math.round(((double) hourlyWage / 60) * workMinutes);

            // DB 업데이트
            dao.updateLogout(lastLog.getLogId(), logoutTime, workMinutes, dailyWage);

            return new LoginResult(workMinutes, dailyWage);
        }
    }

    // 일급 계산 결과 담을 DTO
    public static class LoginResult {
        private final long workMinutes;
        private final int dailyWage;

        public LoginResult(long workMinutes, int dailyWage) {
            this.workMinutes = workMinutes;
            this.dailyWage = dailyWage;
        }

        public long getWorkMinutes() {
            return workMinutes;
        }

        public int getDailyWage() {
            return dailyWage;
        }
    }
}
