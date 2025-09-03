package dto;

import java.sql.Timestamp;

public class LoginLogDTO {
    public int logId;
    public int empId;
    public Timestamp loginTime;
    public Timestamp logoutTime;
    public int workMinutes;
    public int dailyWage;
    private int hourlyWage;
    

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
    
    public int getHourlyWage() {
		return hourlyWage;
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
    
    public void setHourlyWage(int hourlyWage) {
		this.hourlyWage = hourlyWage;
	}
}
