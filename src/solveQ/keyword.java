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

            Font pretendardFont;
            try {
                pretendardFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Pretendard-Bold.otf"));
                pretendardFont = pretendardFont.deriveFont(30f); // 글꼴 크기 설정
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
                pretendardFont = new Font("Serif", Font.BOLD, 30); // 대체 폰트
            }
            g.setFont(pretendardFont);

            choiceKeyword(g, "키워드를 선택해주세요.", 670, 50, 195, 240);

            // 사랑 키워드 사각형
            g.setColor(Color.white);
            g.fillRoundRect(180, 350, 195, 240, 10, 10); //둥근 모서리 사각형 채우기
            drawCenteredString(g, "사랑", 180, 350, 195, 240);

            // 우정 키워드 사각형
            g.setColor(Color.white);
            g.fillRoundRect(425, 350, 195, 240, 10, 10);
            drawCenteredString(g, "우정", 425, 350, 195, 240);

            // 가족 키워드 사각형
            g.setColor(Color.white);
            g.fillRoundRect(665, 350, 195, 240, 10, 10);
            drawCenteredString(g, "가족", 665, 350, 195, 240);

            // 학교 키워드 사각형
            g.setColor(Color.white);
            g.fillRoundRect(905, 350, 195, 240, 10, 10);
            drawCenteredString(g, "학교", 905, 350, 195, 240);

            // 기타 키워드 사각형
            g.setColor(Color.white);
            g.fillRoundRect(1145, 350, 195, 240, 10, 10);
            drawCenteredString(g, "기타", 1145, 350, 195, 240);

        }

        private void drawCenteredString(Graphics g, String text, int x, int y, int width, int height) {
            g.setColor(Color.decode("#7D20D4")); // 글씨 색 설정
            FontMetrics metrics = g.getFontMetrics();
            int textX = x + (width - metrics.stringWidth(text)) / 2;
            int textY = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent() + 40;
            g.drawString(text, textX, textY);
        }

        private void choiceKeyword(Graphics g, String text, int x, int y, int width, int height) {
            g.setColor(Color.white); // 글씨 색 설정

            Font originalFont = g.getFont();
            Font newFont = originalFont.deriveFont(50f); // 원하는 크기 입력
            g.setFont(newFont);

            FontMetrics metrics = g.getFontMetrics();
            int textX = x + (width - metrics.stringWidth(text)) / 2;
            int textY = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent() + 40;
            g.drawString(text, textX, textY);

            g.setFont(originalFont);
        }
    }

    public static void main(String[] args) {
        new keyword();
    }
}