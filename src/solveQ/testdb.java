package src.solveQ;

import java.sql.*;

public class testdb {
    public static void main(String[] args) {
        // MySQL 연결 정보
        String url = "jdbc:mysql://localhost:3306/mysql?useSSL=false"; // 데이터베이스 이름 확인
        String username = "root"; // MySQL 사용자 이름
        String password = "111111"; // MySQL 비밀번호

        try {
            // 드라이버를 명시적으로 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스 연결 시도
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                if (conn != null) {
                    System.out.println("Database 연결 성공");

                    // 예시: 데이터 조회
                    String query = "select * from solveQ"; // 테이블 이름 확인
                    try (Statement stmt = conn.createStatement()) {
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            // 테이블의 컬럼 이름이 정확한지 확인
                            System.out.println("ID: " + rs.getInt("worry") + ", Name: " + rs.getString("solve"));
                        }
                    }
                }
            } catch (SQLException e) {
                // SQLException 처리 블록
                System.err.println("Connection failed: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            // 드라이버 클래스를 찾을 수 없는 경우 처리
            System.err.println("JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
