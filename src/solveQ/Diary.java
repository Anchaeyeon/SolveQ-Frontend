package src.solveQ;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Diary {
    public static void main(String[] args) {
        // 프레임 생성
        JFrame frame = new JFrame("Diary 화면");
        frame.setSize(1920, 1080); // 프레임 크기
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 동작 설정

        // 커스텀 패널 생성
        JPanel panel = new DiaryPanel(); // 내부 클래스 사용
        frame.setContentPane(panel); // 커스텀 패널을 프레임의 컨텐츠 패널로 설정
        frame.setVisible(true); // 프레임 출력
    }

    // DiaryPanel은 Diary 클래스의 내부 클래스
    static class DiaryPanel extends JPanel {
        private BufferedImage image;
        private BufferedImage image2;
        private BufferedImage image3;
        private BufferedImage image4;
        private BufferedImage image5;
        private BufferedImage image6;
        private BufferedImage image7;
        private BufferedImage image8;

        public DiaryPanel() {
            try {
                // 이미지 파일 로드
                image = ImageIO.read(new File("img/DiaryLeft.png"));
                image2 = ImageIO.read(new File("img/DiaryRight.png"));
                image3 = ImageIO.read(new File("img/Line3.png"));
                image4 = ImageIO.read(new File("img/solveQ2.png"));
                image5 = ImageIO.read(new File("img/Line2.png"));
                image6 = ImageIO.read(new File("img/round2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // 배경 색상 설정
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            // 이미지 그리기
            drawImage(g, image, 252, 60, 0.8); 
            drawImage(g, image2, 768, 60, 0.8);
            drawImageWithStretch(g, image3, 767, 60, 1.0, 0.205); //세로 선
            drawImage(g, image4, 1175, 90, 0.9); //solveQ 로고
            drawImage(g, image5, 790, 450, 0.9); //가로 선
            drawImage(g, image6, 880, 445, 0.9); //동그라미
            drawImage(g, image5, 1185, 450, 0.9); //가로 선
            drawImage(g, image6, 1150, 445, 0.9); //동그라미

            // 텍스트 그리기
            drawText(g, "나의 고민", 450, 260, 30);
            drawText(g, "해결책", 980, 260, 30);
        }

        private void drawImage(Graphics g, BufferedImage img, int x, int y, double scale) {
            if (img != null) {
                int newWidth = (int) (img.getWidth() * scale);
                int newHeight = (int) (img.getHeight() * scale);
                g.drawImage(img, x, y, newWidth, newHeight, this);
            }
        }

        private void drawImageWithStretch(Graphics g, BufferedImage img, int x, int y, double scaleX, double scaleY) {
            if (img != null) {
                int newWidth = (int) (img.getWidth() * scaleX);
                int newHeight = (int) (img.getHeight() * scaleY);
                g.drawImage(img, x, y, newWidth, newHeight, this);
            }
        }

        private void drawText(Graphics g, String text, int x, int y, float fontSize) {
            Font font;
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Pretendard-Bold.otf"));
                font = font.deriveFont(fontSize);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
                font = new Font("Serif", Font.BOLD, (int) fontSize);
            }

            g.setFont(font);
            g.setColor(Color.decode("#6B6B6B"));
            g.drawString(text, x, y);
        }
    }
}
