package src.solveQ;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class GiveSolve extends JFrame {
    public GiveSolve() {
        setTitle("GiveSolve 화면");
        setSize(1920, 1080); // 프레임 크기
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 동작 설정

        // 커스텀 패널 추가
        GradientPanel panel = new GradientPanel();
        setContentPane(panel); // 프레임의 기본 컨텐츠 패널로 설정
        setVisible(true); // 프레임 출력
    }

    // 그라데이션을 그리고 이미지를 표시하는 커스텀 패널 클래스
    class GradientPanel extends JPanel {
        private BufferedImage image1; // 첫 번째 이미지
        private BufferedImage image2; // 두 번째 이미지
        private BufferedImage image3; // 세 번째 이미지
        private BufferedImage image4; // 네 번째 이미지

        private final int xOffset1 = -550; // 첫 번째 이미지 왼쪽으로 이동할 오프셋 (음수값)
        private final int xOffset2 = -440; // 두 번째 이미지 왼쪽으로 이동할 오프셋
        private final int xOffset3 = 550;  // 세 번째 이미지 오른쪽으로 이동할 오프셋
        private final int xOffset4 = 440;  // 네 번째 이미지 오른쪽으로 이동할 오프셋

        public GradientPanel() {
            try {
                // 첫 번째 이미지 파일 경로
                image1 = ImageIO.read(new File("img/Line.png"));
                // 두 번째 이미지 파일 경로
                image2 = ImageIO.read(new File("img/round.png"));
                // 세 번째 이미지 파일 경로
                image3 = ImageIO.read(new File("img/Line.png"));
                // 네 번째 이미지 파일 경로
                image4 = ImageIO.read(new File("img/round.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // 그라데이션 설정 (시작 색상, 끝 색상, 방향)
            Color startColor = Color.decode("#B365FD"); // HEX 색상 #B365FD
            Color endColor = Color.decode("#41116D");   // HEX 색상 #41116D
            int width = getWidth();
            int height = getHeight();

            // 위에서 아래로 향하는 그라데이션
            GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, height, endColor);
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, width, height); // 그라데이션으로 채우기

            // 첫 번째 이미지 그리기
            if (image1 != null) {
                int imgWidth = image1.getWidth();
                int imgHeight = image1.getHeight();
                int x = (width - imgWidth) / 2 + xOffset1; // xOffset1 만큼 이동
                int y = (height - imgHeight) / 2; // 화면 가운데 y 좌표
                g.drawImage(image1, x, y, this);
            }

            // 두 번째 이미지 그리기
            if (image2 != null) {
                int imgWidth = image2.getWidth();
                int imgHeight = image2.getHeight();
                int x = (width - imgWidth) / 2 + xOffset2; // xOffset2 만큼 이동
                int y = (height - imgHeight) / 2; // 화면 가운데 y 좌표
                g.drawImage(image2, x, y, this);
            }

            // 세 번째 이미지 그리기
            if (image3 != null) {
                int imgWidth = image3.getWidth();
                int imgHeight = image3.getHeight();
                int x = (width - imgWidth) / 2 + xOffset3; // xOffset3 만큼 이동
                int y = (height - imgHeight) / 2; // 화면 가운데 y 좌표
                g.drawImage(image3, x, y, this);
            }

            // 네 번째 이미지 그리기
            if (image4 != null) {
                int imgWidth = image4.getWidth();
                int imgHeight = image4.getHeight();
                int x = (width - imgWidth) / 2 + xOffset4; // xOffset4 만큼 이동
                int y = (height - imgHeight) / 2; // 화면 가운데 y 좌표
                g.drawImage(image4, x, y, this);
            }
        }
    }

    public static void main(String[] args) {
        new GiveSolve(); // 클래스 이름
    }
}
