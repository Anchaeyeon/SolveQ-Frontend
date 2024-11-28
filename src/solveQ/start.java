package src.solveQ;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class start extends JFrame {
    public start() {
        setTitle("start화면");
        setSize(1920, 1080); // 프레임 크기
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
            int imgBottomY = 0; // 이미지 하단의 y 좌표를 저장할 변수
            if (image != null) {
                int imgWidth = image.getWidth();
                int imgHeight = image.getHeight();
                int x = (width - imgWidth) / 2; // 화면 가운데 x 좌표
                int y = (height - imgHeight) / 2; // 화면 가운데 y 좌표
                imgBottomY = y + imgHeight; // 이미지의 하단 y 좌표 계산
                g.drawImage(image, x, y, this);
            }

            // 글꼴 설정
            Font pretendardFont;
            try {
                pretendardFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Pretendard-Bold.otf"));
                pretendardFont = pretendardFont.deriveFont(30f); // 글꼴 크기 설정
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
                pretendardFont = new Font("Serif", Font.BOLD, 30); // 대체 폰트
            }
            g.setFont(pretendardFont);

            // 텍스트를 이미지 아래에 배치
            drawCenteredString(g, "로고를 클릭해주세요", 0, imgBottomY + 20, width, height - imgBottomY - 20, Color.white);
        }
    }

    private void drawCenteredString(Graphics g, String text, int x, int y, int width, int height, Color textColor) {
        g.setColor(textColor); // 글씨 색 설정
        FontMetrics metrics = g.getFontMetrics();
        int textX = x + (width - metrics.stringWidth(text)) / 2;
        int textY = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent() - 170; // 위치를 살짝 올리기 위해 -10 추가
        g.drawString(text, textX, textY);
    }

    public static void main(String[] args) {
        new start();
    }
}
