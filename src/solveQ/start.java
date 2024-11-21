package src.solveQ;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class start extends JFrame {
    public start() {
        setTitle("start화면");
        setSize(1440, 1024); // 프레임 크기
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 동작 설정

        // 커스텀 패널 추가
        GradientPanel panel = new GradientPanel();
        setContentPane(panel); // 프레임의 기본 컨텐츠 패널로 설정
        setVisible(true); // 프레임 출력
    }

    // 그라데이션을 그리는 커스텀 패널 클래스
    class GradientPanel extends JPanel {
        private BufferedImage image;

        // 생성자에서 이미지 로드
        public GradientPanel() {
            try {
                // 이미지 파일 경로를 입력하세요.
                image = ImageIO.read(new File("img/solveQ.png"));
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

            // 이미지 그리기 (중앙 정렬)
            if (image != null) {
                int imgWidth = image.getWidth();
                int imgHeight = image.getHeight();
                int x = (width - imgWidth) / 2; // 화면 가운데 x 좌표
                int y = (height - imgHeight) / 2; // 화면 가운데 y 좌표
                g.drawImage(image, x, y, this);

                // 텍스트 그리기 (이미지 아래에)
                String text = "로고를 클릭해주세요";
                Font font = new Font("맑은 고딕", Font.BOLD, 24);
                g2d.setFont(font);
                g2d.setColor(Color.WHITE);
                FontMetrics metrics = g2d.getFontMetrics(font);

                int textWidth = metrics.stringWidth(text);
                int textX = (width - textWidth) / 2; // 텍스트 가운데 x 좌표
                int textY = y + imgHeight + 50; // 이미지 아래로 약간 떨어진 위치
                g2d.drawString(text, textX, textY);
            }
        }
    }

    public static void main(String[] args) {
        new start();
    }
}
