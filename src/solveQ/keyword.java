package src.solveQ;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        //이미지
        private BufferedImage loveImage;
        private BufferedImage friendshipImage;
        private BufferedImage familyImage;
        private BufferedImage schoolImage;
        private BufferedImage otherImage;

        // 클릭 영역
        private final Rectangle loveRect = new Rectangle(180, 350, 195, 240);
        private final Rectangle friendshipRect = new Rectangle(425, 350, 195, 240);
        private final Rectangle familyRect = new Rectangle(665, 350, 195, 240);
        private final Rectangle schoolRect = new Rectangle(905, 350, 195, 240);
        private final Rectangle otherRect = new Rectangle(1145, 350, 195, 240);

        public GradientPanel() {
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

            // 마우스 클릭 이벤트 추가
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    handleMouseClick(e.getPoint());
                }
            });
        }

        private void handleMouseClick(Point point) {
            if (loveRect.contains(point)) {
                new lovePage(); // 사랑 페이지로 이동
            } else if (friendshipRect.contains(point)) {
                new friendshipPage(); // 우정 페이지로 이동
            } else if (familyRect.contains(point)) {
                new familyPage(); // 가족 페이지로 이동
            } else if (schoolRect.contains(point)) {
                new schoolPage(); // 학교 페이지로 이동
            } else if (otherRect.contains(point)) {
                new otherPage(); // 기타 페이지로 이동
            }
        }

        private void openNewPage(String title) {
            // 새 페이지를 위한 JFrame 생성
            JFrame newPage = new JFrame(title);
            newPage.setSize(800, 600);
            newPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 닫으면 현재 페이지만 닫음
            newPage.add(new JLabel(title, SwingConstants.CENTER), BorderLayout.CENTER);
            newPage.setVisible(true);
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
            drawCenteredString(g, "사랑", loveImage, 180, 350, 195, 240);

            // 우정 키워드 사각형
            g.setColor(Color.white);
            g.fillRoundRect(425, 350, 195, 240, 10, 10);
            drawCenteredString(g, "우정", friendshipImage, 425, 350, 195, 240);

            // 가족 키워드 사각형
            g.setColor(Color.white);
            g.fillRoundRect(665, 350, 195, 240, 10, 10);
            drawCenteredString(g, "가족", familyImage, 665, 350, 195, 240);

            // 학교 키워드 사각형
            g.setColor(Color.white);
            g.fillRoundRect(905, 350, 195, 240, 10, 10);
            drawCenteredString(g, "학교", schoolImage, 905, 350, 195, 240);

            // 기타 키워드 사각형
            g.setColor(Color.white);
            g.fillRoundRect(1145, 350, 195, 240, 10, 10);
            drawCenteredString(g, "기타", otherImage, 1145, 350, 195, 240);
        }

        private void drawCenteredString(Graphics g, String text, BufferedImage image, int x, int y, int width, int height) {
            g.setColor(Color.decode("#7D20D4")); // 글씨 색 설정
            if (image != null) {
                int imgWidth = image.getWidth();
                int imgHeight = image.getHeight();
                int imgX = x + (width - imgWidth) / 2;
                int imgY = y + (height - imgHeight) / 2 - 30; // 이미지 위치 조정
                g.drawImage(image, imgX, imgY, this);
            }
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