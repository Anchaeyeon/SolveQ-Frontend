package src.solveQ;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class GiveSolve {
    public static void main(String[] args) {
        // 프레임 생성
        JFrame frame = new JFrame("GiveSolve 화면");
        frame.setSize(1920, 1080); // 프레임 크기
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 동작 설정

        // 커스텀 패널 생성
        GradientPanel panel = new GradientPanel();
        frame.setContentPane(panel); // 커스텀 패널을 프레임의 컨텐츠 패널로 설정
        frame.setVisible(true); // 프레임 출력
    }

    // 그라데이션과 이미지를 그리는 커스텀 JPanel 클래스
    static class GradientPanel extends JPanel {
        private BufferedImage image1; // 첫 번째 이미지
        private BufferedImage image2; // 두 번째 이미지
        private BufferedImage image3; // 세 번째 이미지
        private BufferedImage image4; // 네 번째 이미지
        private BufferedImage image5; // 다섯 번째 이미지

        private final int xOffset1 = -550; // 첫 번째 이미지 왼쪽으로 이동할 오프셋 (음수값)
        private final int xOffset2 = -440; // 두 번째 이미지 왼쪽으로 이동할 오프셋 (음수값)
        private final int xOffset3 = 550;  // 세 번째 이미지 오른쪽으로 이동할 오프셋 (양수값)
        private final int xOffset4 = 440;  // 네 번째 이미지 오른쪽으로 이동할 오프셋 (양수값)

        public GradientPanel() {
            try {
                // 이미지 로드
                image1 = ImageIO.read(new File("img/Line.png"));
                image2 = ImageIO.read(new File("img/round.png"));
                image3 = ImageIO.read(new File("img/Line.png"));
                image4 = ImageIO.read(new File("img/round.png"));
                image5 = ImageIO.read(new File("img/plug.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // 그라데이션 색상 설정
            Color startColor = Color.decode("#B365FD"); // HEX 색상 #B365FD
            Color endColor = Color.decode("#41116D");   // HEX 색상 #41116D
            int width = getWidth();
            int height = getHeight();

            // 세로 방향으로 그라데이션 적용
            GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, height, endColor);
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, width, height); // 그라데이션으로 배경 채우기

            // 첫 번째 이미지 그리기
            if (image1 != null) {
                int imgWidth = image1.getWidth();
                int imgHeight = image1.getHeight();
                int x = (width - imgWidth) / 2 + xOffset1; // 첫 번째 이미지의 x좌표 계산
                int y = (height - imgHeight) / 2; // y좌표는 화면 중앙으로
                g.drawImage(image1, x, y, this);
            }

            // 두 번째 이미지 그리기
            if (image2 != null) {
                int imgWidth = image2.getWidth();
                int imgHeight = image2.getHeight();
                int x = (width - imgWidth) / 2 + xOffset2; // 두 번째 이미지의 x좌표 계산
                int y = (height - imgHeight) / 2; // y좌표는 화면 중앙으로
                g.drawImage(image2, x, y, this);
            }

            // 세 번째 이미지 그리기
            if (image3 != null) {
                int imgWidth = image3.getWidth();
                int imgHeight = image3.getHeight();
                int x = (width - imgWidth) / 2 + xOffset3; // 세 번째 이미지의 x좌표 계산
                int y = (height - imgHeight) / 2; // y좌표는 화면 중앙으로
                g.drawImage(image3, x, y, this);
            }

            // 네 번째 이미지 그리기
            if (image4 != null) {
                int imgWidth = image4.getWidth();
                int imgHeight = image4.getHeight();
                int x = (width - imgWidth) / 2 + xOffset4; // 네 번째 이미지의 x좌표 계산
                int y = (height - imgHeight) / 2; // y좌표는 화면 중앙으로
                g.drawImage(image4, x, y, this);
            }

            // 다섯 번째 이미지 고정 위치에 그리기
            if (image5 != null) {
                int imgWidth = image5.getWidth();
                int imgHeight = image5.getHeight();
                int x = 1420; // 다섯 번째 이미지의 x좌표
                int y = 700;  // 다섯 번째 이미지의 y좌표
                g.drawImage(image5, x, y, this);
            }
        }
    }
}
