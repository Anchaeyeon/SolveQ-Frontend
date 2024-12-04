package src.solveQ;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Diary extends JPanel {
    private BufferedImage image;
    private BufferedImage image2;
    private BufferedImage image3;
    private BufferedImage image4;
    private BufferedImage image5;
    private BufferedImage image6;
    private String latestWorry;

    public Diary() {
        try {
            image = ImageIO.read(new File("img/DiaryLeft.png"));
            image2 = ImageIO.read(new File("img/DiaryRight.png"));
            image3 = ImageIO.read(new File("img/Line3.png"));
            image4 = ImageIO.read(new File("img/solveQ2.png"));
            image5 = ImageIO.read(new File("img/Line2.png"));
            image6 = ImageIO.read(new File("img/round2.png"));
        } catch (IOException e) {
            System.err.println("Image loading error: " + e.getMessage());
            e.printStackTrace();
        }
        // 최근 고민을 데이터베이스에서 가져오기
        this.latestWorry = worryInsertdb.getLatestWorry();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        drawImage(g, image, 252, 60, 0.8);
        drawImage(g, image2, 768, 60, 0.8);
        drawImageWithStretch(g, image3, 767, 60, 1.0, 0.205);
        drawImage(g, image4, 1175, 90, 0.9);
        drawImage(g, image5, 790, 450, 0.9);
        drawImage(g, image6, 880, 445, 0.9);
        drawImage(g, image5, 1185, 450, 0.9);
        drawImage(g, image6, 1150, 445, 0.9);

        drawText(g, "나의 고민", 450, 260, 30);
        drawText(g, "해결책", 980, 260, 30);

        if (latestWorry != null) {
            drawText(g, latestWorry, 450, 300, 20);
        } else {
            drawText(g, "최근 고민이 없습니다.", 450, 300, 20);
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
        } catch (FontFormatException | IOException e) {
            System.err.println("Font loading error: " + e.getMessage());
            e.printStackTrace();
            font = new Font("Serif", Font.BOLD, (int) fontSize);
        }

        g.setFont(font);
        g.setColor(Color.decode("#6B6B6B"));
        g.drawString(text, x, y);
    }
}
