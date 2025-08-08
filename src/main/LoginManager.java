package src.main;

import java.sql.Connection;
import java.sql.SQLException;

import src.DB.ConnectDB;

public class LoginManager {
    public static void main(String[] args) {
        // DB 연결 시도
        try(Connection conn = ConnectDB.getConnectionDB()) {
            // DB 연결 성공
            System.out.println("DB 연결 성공");
        } catch (SQLException e) {
            // DB 연결 실패
            System.out.println("DB 연결 실패: " + e.getMessage());
        }
    }
}
