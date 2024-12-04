package src.solveQ;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Keyword extends JPanel implements MouseListener {
    private Main mainFrame;
    // 이미지
    private BufferedImage loveImage, friendshipImage, familyImage, schoolImage, otherImage;

    // 클릭 영역
    private final Rectangle loveRect = new Rectangle(180, 350, 195, 240);
    private final Rectangle friendshipRect = new Rectangle(425, 350, 195, 240);
    private final Rectangle familyRect = new Rectangle(665, 350, 195, 240);
    private final Rectangle schoolRect = new Rectangle(905, 350, 195, 240);
    private final Rectangle otherRect = new Rectangle(1145, 350, 195, 240);

    public Keyword(Main mainFrame) {
        this.mainFrame = mainFrame;
        setPreferredSize(new Dimension(1920, 1080));
        addMouseListener(this);

        try {
            // 배경 이미지와 각 키워드 이미지를 로드
            loveImage = ImageIO.read(new File("img/love.png"));
            friendshipImage = ImageIO.read(new File("img/friendship.png"));
            familyImage = ImageIO.read(new File("img/family.png"));
            schoolImage = ImageIO.read(new File("img/school.png"));
            otherImage = ImageIO.read(new File("img/other.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();

        if (loveRect.contains(p)) {
            mainFrame.showLoveScreen(); // 사랑 페이지로 이동
        } else if (friendshipRect.contains(p)) {
            mainFrame.showFriendshipScreen();
        } else if (familyRect.contains(p)) {
            mainFrame.showFamilyScreen();
        } else if (schoolRect.contains(p)) {
            mainFrame.showSchoolScreen();
        } else if (otherRect.contains(p)) {
            mainFrame.showOtherScreen();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // 그라데이션 설정
        Color startColor = Color.decode("#B365FD");
        Color endColor = Color.decode("#41116D");
        int width = getWidth();
        int height = getHeight();

        // 위에서 아래로 향하는 그라데이션
        GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, height, endColor);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);

        Font pretendardFont;
        try {
            pretendardFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Pretendard-Bold.otf"));
            pretendardFont = pretendardFont.deriveFont(30f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            pretendardFont = new Font("Serif", Font.BOLD, 30); // 대체 폰트
        }
        g.setFont(pretendardFont);

        choiceKeyword(g, "키워드를 선택해주세요.", 670, 50, 195, 240);

        // 각 키워드와 이미지 그리기
        g.setColor(Color.white);
        g.fillRoundRect(180, 350, 195, 240, 10, 10); // 사랑 키워드
        drawCenteredString(g, "사랑", loveImage, 180, 350, 195, 240);
        g.fillRoundRect(425, 350, 195, 240, 10, 10); // 우정 키워드
        drawCenteredString(g, "우정", friendshipImage, 425, 350, 195, 240);
        g.fillRoundRect(665, 350, 195, 240, 10, 10); // 가족 키워드
        drawCenteredString(g, "가족", familyImage, 665, 350, 195, 240);
        g.fillRoundRect(905, 350, 195, 240, 10, 10); // 학교 키워드
        drawCenteredString(g, "학교", schoolImage, 905, 350, 195, 240);
        g.fillRoundRect(1145, 350, 195, 240, 10, 10); // 기타 키워드
        drawCenteredString(g, "기타", otherImage, 1145, 350, 195, 240);
    }

    private void drawCenteredString(Graphics g, String text, BufferedImage image, int x, int y, int width, int height) {
        g.setColor(Color.decode("#7D20D4"));
        if (image != null) {
            int imgWidth = image.getWidth();
            int imgHeight = image.getHeight();
            int imgX = x + (width - imgWidth) / 2;
            int imgY = y + (height - imgHeight) / 2 - 30;
            g.drawImage(image, imgX, imgY, this);
        }
        FontMetrics metrics = g.getFontMetrics();
        int textX = x + (width - metrics.stringWidth(text)) / 2;
        int textY = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent() + 40;
        g.drawString(text, textX, textY);
    }

    private void choiceKeyword(Graphics g, String text, int x, int y, int width, int height) {
        g.setColor(Color.white);
        Font originalFont = g.getFont();
        Font newFont = originalFont.deriveFont(50f);
        g.setFont(newFont);

        FontMetrics metrics = g.getFontMetrics();
        int textX = x + (width - metrics.stringWidth(text)) / 2;
        int textY = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent() + 40;
        g.drawString(text, textX, textY);

        g.setFont(originalFont);
    }
}
