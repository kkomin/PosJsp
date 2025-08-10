package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DB.ConnectDB;
import dto.UserDTO;

public class UserDAO {
    public UserDTO login(String userId, String userPw) {
        UserDTO user = null;

        String sql = "SELECT user_id, user_pw, user_name from employees where user_id = ? AND user_pw = ?";

        try (Connection conn = ConnectDB.getConnectionDB();
                PreparedStatement pre = conn.prepareStatement(sql)) {
            // 파라미터 할당
            pre.setString(1, userId);
            pre.setString(2, userPw);

            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                user = new UserDTO(
                		rs.getString("user_id"), 
                		rs.getString("user_pw"), 
                		rs.getString("user_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
