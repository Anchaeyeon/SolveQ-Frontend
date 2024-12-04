package src.solveQ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class worryInsertdb {
    private static final String URL = "jdbc:mysql://localhost:3306/mysql?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "111111";

    public static void saveWorry(String worryContent) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            if (conn != null) {
                System.out.println("Database 연결 성공");

                String query = "insert into worryInsert (worry) values (?)";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, worryContent);
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("고민 저장 완료!!!");
                    } else {
                        System.out.println("고민 저장 실패");
                    }
                } catch (SQLException e) {
                    System.err.println("SQL 에러: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            System.err.println("데이터베이스 연결 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getLatestWorry() {
        String latestWorry = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            if (conn != null) {
                System.out.println("Database 연결 성공");

                String query = "SELECT worry FROM worryInsert ORDER BY id DESC LIMIT 1";
                try (PreparedStatement pstmt = conn.prepareStatement(query);
                     ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        latestWorry = rs.getString("worry");
                    }
                } catch (SQLException e) {
                    System.err.println("SQL 에러: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            System.err.println("데이터베이스 연결 실패: " + e.getMessage());
            e.printStackTrace();
        }

        return latestWorry;
    }
}
