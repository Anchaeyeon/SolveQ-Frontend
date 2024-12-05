package src.solveQ;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Diary extends JPanel {
    private BufferedImage image;
    private BufferedImage image2;
    private BufferedImage image3;
    private BufferedImage image4;
    private BufferedImage image5;
    private BufferedImage image6;
    private String latestAdvice; // 최신 조언 저장 변수
    private String latestWorry; // 최신 고민 저장 변수

    public Diary() {
        loadImages(); // 이미지 로딩 메서드 호출
        updateLatestAdvice(); // 최신 조언 불러오기
        updateLatestWorry(); // 최신 고민 불러오기
    }

    // 이미지 로딩 메서드
    private void loadImages() {
        try {
            image = ImageIO.read(new File("img/DiaryLeft.png"));
            image2 = ImageIO.read(new File("img/DiaryRight.png"));
            image3 = ImageIO.read(new File("img/Line3.png"));
            image4 = ImageIO.read(new File("img/solveQ2.png"));
            image5 = ImageIO.read(new File("img/Line2.png"));
            image6 = ImageIO.read(new File("img/round2.png"));
        } catch (IOException e) {
            System.err.println("이미지 로딩 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 최신 조언을 업데이트하는 메서드
    public void updateLatestAdvice() {
        solveRandomdb db = new solveRandomdb();
        latestAdvice = db.getLatestAdvice(); // 데이터베이스에서 최신 조언 가져오기
        repaint(); // 화면을 다시 그려 최신 조언 표시
    }

    // 최신 고민을 업데이트하는 메서드
    public void updateLatestWorry() {
        worryInsertdb db = new worryInsertdb();
        latestWorry = db.getLatestWorry(); // 데이터베이스에서 최신 고민 가져오기
        repaint(); // 화면을 다시 그려 최신 고민 표시
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // 이미지 그리기
        drawImage(g, image, 252, 60, 0.8); // 'book_left' 이미지
        drawImage(g, image2, 768, 60, 0.8); // 'book_right' 이미지
        drawImageWithStretch(g, image3, 767, 60, 1.0, 0.205);
        drawImage(g, image4, 1175, 90, 0.9);

        // 텍스트 그리기 (이미지 위에 표시)
        drawText(g, "나의 고민", 450, 260, 30);
        drawText(g, "해결책", 980, 260, 30);

        // 'book_left' 이미지 위에 최신 고민 표시
        if (latestWorry != null) {
            drawCenteredText(g, latestWorry,
                    252 + (int) (image.getWidth() * 0.8) / 2,
                    60 + (int) (image.getHeight() * 0.8) / 2,
                    25);
        } else {
            drawCenteredText(g, "최근 고민이 없습니다.",
                    252 + (int) (image.getWidth() * 0.8) / 2,
                    60 + (int) (image.getHeight() * 0.8) / 2,
                    25);
        }

        // 'book_right' 이미지 위에 최신 조언 표시
        if (latestAdvice != null) {
            drawCenteredText(g, latestAdvice,
                    768 + (int) (image2.getWidth() * 0.8) / 2,
                    60 + (int) (image2.getHeight() * 0.8) / 2,
                    25);
        } else {
            drawCenteredText(g, "최근 조언이 없습니다.",
                    768 + (int) (image2.getWidth() * 0.8) / 2,
                    60 + (int) (image2.getHeight() * 0.8) / 2,
                    25);
        }
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
            g.setColor(Color.decode("#6B6B6B")); // 글씨 색깔 설정
        } catch (FontFormatException | IOException e) {
            System.err.println("폰트 로딩 오류: " + e.getMessage());
            e.printStackTrace();
            font = new Font("Serif", Font.BOLD, (int) fontSize);
            g.setColor(Color.decode("#6B6B6B"));
        }

        g.setFont(font);
        g.drawString(text, x, y);
    }

    private void drawCenteredText(Graphics g, String text, int centerX, int centerY, float fontSize) {
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Pretendard-Bold.otf"));
            font = font.deriveFont(fontSize);
            g.setColor(Color.decode("#6B6B6B"));
        } catch (FontFormatException | IOException e) {
            System.err.println("폰트 로딩 오류: " + e.getMessage());
            e.printStackTrace();
            font = new Font("Serif", Font.BOLD, (int) fontSize);
            g.setColor(Color.decode("#6B6B6B"));
        }

        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();

        // 텍스트를 중심에 맞추기
        int textX = centerX - textWidth / 2;
        int textY = centerY + metrics.getAscent() / 2 - metrics.getDescent() / 2;

        g.drawString(text, textX, textY);
    }
}
