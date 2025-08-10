package dto;

public class UserDTO {
    private String userId;
    private String pw;
    private String userName;
    private int empId;

    public UserDTO() {
    }

    public UserDTO(String userId, String pw, String userName, int empId) {
        this.userId = userId;
        this.pw = pw;
        this.userName = userName;
        this.empId = empId;
    }
    
    public int getEmpId() {
        return empId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPw() {
        return pw;
    }

    public String getUserName() {
        return userName;
    }
}
