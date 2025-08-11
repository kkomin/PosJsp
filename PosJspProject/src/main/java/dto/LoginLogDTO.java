package dto;

import java.sql.Date;

public class LoginLogDTO {
    public int logId;
    public int empId;
    public Date loginTime;
    public Date logoutTime;
    public int workMinutes;
    public int dailyWage;
    

    public int getEmpId() {
        return empId;
    }
    
    public Date getLoginTime() {
        return loginTime;
    }
    
    public int getLogId() {
        return logId;
    }
    
    public Date getLogoutTime() {
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
    
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
    
    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }
    
    public void setWorkMinutes(int workMinutes) {
        this.workMinutes = workMinutes;
    }
    
    public void setDailyWage(int dailyWage) {
        this.dailyWage = dailyWage;
    }
}
