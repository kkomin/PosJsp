package dto;

import java.sql.Timestamp;

public class LoginLogDTO {
    public int logId;
    public int empId;
    public Timestamp loginTime;
    public Timestamp logoutTime;
    public int workMinutes;
    public int dailyWage;
    

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
    
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    
    public void setLogId(int logId) {
        this.logId = logId;
    }
    
    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }
    
    public void setLogoutTime(Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }
    
    public void setWorkMinutes(int workMinutes) {
        this.workMinutes = workMinutes;
    }
    
    public void setDailyWage(int dailyWage) {
        this.dailyWage = dailyWage;
    }
}
