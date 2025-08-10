package dto;

public class UserDTO {
    private String userId;
    private String pw;
    private String userName;

    public UserDTO() {
    }

    public UserDTO(String userId, String pw, String userName) {
        this.userId = userId;
        this.pw = pw;
        this.userName = userName;
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
