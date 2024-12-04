package src.solveQ;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class GoToDiary {
    public static void main(String[] args) {
        // 프레임 생성
        JFrame frame = new JFrame("일기장 보러가기");
        frame.setSize(1920, 1080); // 프레임 크기
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 동작 설정

        // 커스텀 패널 생성
        GradientPanel panel = new GradientPanel();
        frame.setContentPane(panel); // 커스텀 패널을 프레임의 컨텐츠 패널로 설정
        frame.setVisible(true); // 프레임 출력
    }

    // 그라데이션과 이미지를 그리는 커스텀 JPanel 클래스
    static class GradientPanel extends JPanel {
        private BufferedImage rogo; // 첫 번째 이미지

        public GradientPanel() {
            try {
                // 이미지 로드 (경로를 상대 경로로 수정했거나 절대 경로를 확인)
                rogo = ImageIO.read(new File("img/solveQ.png"));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("이미지 파일을 로드할 수 없습니다.");
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

            // 이미지 그리기 (중앙 정렬)
            if (rogo != null) {
                int imgWidth = rogo.getWidth();
                int imgHeight = rogo.getHeight();
                int x = (width - imgWidth) / 2; // 중앙 x 좌표

                // float로 중앙 y 좌표 계산
                float y = (height - imgHeight) / 2.5f; // 중앙 y 좌표를 3등분하여 위치 계산

                // float 값을 int로 변환하여 drawImage 메서드에 전달
                g2d.drawImage(rogo, x, (int) y, this); // 이미지 그리기
            }

            // 사각형 버튼 만들기
            g.setColor(Color.white);
            g.fillRoundRect(615, 450, 300, 60, 50, 60);

            Font font;
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Pretendard-Bold.otf"));
                font = font.deriveFont(30f);
            } catch (Exception e) {
                e.printStackTrace();
                font = new Font("Serif", Font.BOLD, 30);
            }
            g.setFont(font);
            drawCenteredText(g, "일기장 보러가기", 670, 355, 195, 170);
        }
        private void drawCenteredText(Graphics g, String text, int x, int y, int width, int height) {
            float fontSize = 30f; // 기본 글씨 크기
            Color color = Color.decode("#41116D"); //글씨 색깔

            Font originalFont = g.getFont();
            Font newFont = originalFont.deriveFont(fontSize); // 글씨 크기 설정
            g.setFont(newFont);

            g.setColor(color);

            FontMetrics metrics = g.getFontMetrics();
            int textX = x + (width - metrics.stringWidth(text)) / 2;
            int textY = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent() + 40;
            g.drawString(text, textX, textY);

            g.setFont(originalFont);
        }

    }
}