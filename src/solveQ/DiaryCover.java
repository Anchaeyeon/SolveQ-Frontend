package src.solveQ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DiaryCover extends JPanel {
    private Main mainFrame; // Main 인스턴스를 받아 화면 전환을 위해 사용
    private DiaryPanel diaryPanel; // DiaryPanel 인스턴스

    public DiaryCover(Main mainFrame) {
        this.mainFrame = mainFrame;

        // DiaryPanel 생성 및 추가
        diaryPanel = new DiaryPanel();
        this.setLayout(new BorderLayout());
        this.add(diaryPanel, BorderLayout.CENTER);

        // 클릭 이벤트 처리 (마우스 리스너)
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // 클릭 시 GiveSolve 화면으로 전환
                mainFrame.showGiveSolveScreen();
            }
        });
    }

    // DiaryPanel 클래스 정의
    static class DiaryPanel extends JPanel {
        private BufferedImage bookImg;
        private BufferedImage rogoImg;
        private BufferedImage moonImg;
        private BufferedImage circle;
        private BufferedImage line;
        private double imageScale = 0.8; // 기본 이미지 크기 비율

        public DiaryPanel() {
            try {
                // 이미지 파일 로드
                bookImg = ImageIO.read(new File("img/book_cover.png"));
                rogoImg = ImageIO.read(new File("img/solveQ.png"));
                moonImg = ImageIO.read(new File("img/moon.png"));
                circle = ImageIO.read(new File("img/circle.png"));
                line = ImageIO.read(new File("img/book_line.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 슬라이더 생성
            JSlider slider = new JSlider(1, 200, 90); // 슬라이더 범위 (1% to 200%)
            slider.setMajorTickSpacing(10);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            slider.addChangeListener(e -> {
                imageScale = slider.getValue() / 100.0; // 슬라이더 값을 비율로 변환
                repaint(); // 이미지 크기 변경 시 재그리기
            });

            // 슬라이더 추가
            this.setLayout(new BorderLayout());
            this.add(slider, BorderLayout.SOUTH);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // 배경 색상 설정
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            // 이미지 그리기
            drawImage(g, bookImg, 540, 60, imageScale);
            drawImage(g, rogoImg, 635, 180, imageScale);
            drawImage(g, moonImg, 750, 370, imageScale);
            drawImage(g, circle, 570, 85, imageScale);
            drawImage(g, circle, 1000, 85, imageScale);
            drawImage(g, circle, 570, 700, imageScale);
            drawImage(g, circle, 1000, 700, imageScale);
            drawImageWithStretch(g, line, 575, 120, 1.0, 0.75);

            // 텍스트 그리기
            drawText(g, "당신의 고민을 들어 드립니다.", 670, 310, 20);
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
            g.setColor(Color.white);
            g.drawString(text, x, y);
        }
    }
}
