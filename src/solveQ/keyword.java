package src.solveQ;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class keyword extends JFrame {
    public keyword() {
        setTitle("keyword");
        setSize(1920, 1080); // 프레임 크기
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 동작 설정

        // 커스텀 패널 추가
        GradientPanel panel = new GradientPanel();
        setContentPane(panel); // 프레임의 기본 컨텐츠 패널로 설정
        setVisible(true); // 프레임 출력
    }

    // 그라데이션을 그리는 커스텀 패널 클래스
    class GradientPanel extends JPanel {
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

            if (choice_k != null) {
                int imgWidth = choice_k.getWidth();
                int imgHeight = choice_k.getHeight();
                int x = (width - imgWidth) / 2;
                int y = (height - imgHeight) / 4;
                g.drawImage(choice_k, x, y, this);
            }
        }

        private BufferedImage choice_k;
        // 생성자에서 이미지 로드
        public GradientPanel() {
            try {
                // 이미지 파일 경로를 입력하세요.
                choice_k = ImageIO.read(new File("img/choice_keyword.png"));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new keyword();
    }
}