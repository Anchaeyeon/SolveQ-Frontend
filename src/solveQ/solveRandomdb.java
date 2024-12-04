package src.solveQ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class solveRandomdb {
    // 데이터베이스 연결 정보
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mysql?useSSL=false";
    private static final String DATABASE_USER = "root"; // 사용자 이름
    private static final String DATABASE_PASSWORD = "111111"; // 비밀번호

    public void saveAdviceToDatabase(String advice) {
        String sql = "INSERT INTO solveRandom (advice_text) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, advice);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("데이터베이스 저장 오류: " + e.getMessage());
        }
    }

    public String getLatestAdvice() {
        String latestAdvice = null;
        String sql = "SELECT advice_text FROM solveRandom ORDER BY id DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                latestAdvice = rs.getString("advice_text");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("데이터베이스 조회 오류: " + e.getMessage());
        }
        return latestAdvice;
    }
}
