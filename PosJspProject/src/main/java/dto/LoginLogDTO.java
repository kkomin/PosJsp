package dto;

import java.sql.Timestamp;

public class LoginLogDTO {
    private int logId;
    private int empId;
    private Timestamp loginTime;
    private Timestamp logoutTime;
    private int workMinutes;
    private int dailyWage;
    

    public int getEmpId() {
        return empId;
    }
    
    public Timestamp getLoginTime() {
        return loginTime;
    }
    
    public int getLogId() {
        return logId;
    }
    
    public Timestamp getLogoutTime() {
        return logoutTime;
    }
    
    public int getWorkMinutes() {
        return workMinutes;
    }
    
    public int getDailyWage() {
        return dailyWage;
    }
}
