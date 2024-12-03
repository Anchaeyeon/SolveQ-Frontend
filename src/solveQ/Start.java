package src.solveQ;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Start extends JPanel {
    private BufferedImage image;
    private Main mainFrame; // Main 클래스의 참조

    // 생성자에서 초기화
    public Start(Main mainFrame) {
        this.mainFrame = mainFrame; // Main 클래스 참조 저장

        // 이미지 로드
        try {
            image = ImageIO.read(new File("img/solveQ.png")); // 이미지 파일 경로
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 클릭 이벤트 추가
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isImageClicked(e.getX(), e.getY())) {
                    // Main 클래스의 화면 전환 메서드 호출
                    mainFrame.showKeywordScreen();
                }
            }
        });
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
        g.setColor(Color.WHITE);
        FontMetrics metrics = g.getFontMetrics();
        String text = "로고를 클릭해주세요";
        int textX = (width - metrics.stringWidth(text)) / 2;
        int textY = imgBottomY + metrics.getHeight() + 10; // 이미지 아래 위치
        g.drawString(text, textX, textY);
    }

    private boolean isImageClicked(int mouseX, int mouseY) {
        if (image != null) {
            int width = getWidth();
            int height = getHeight();
            int imgX = (width - image.getWidth()) / 2;
            int imgY = (height - image.getHeight()) / 2;

            // 클릭 좌표가 이미지 영역 안에 있는지 확인
            return mouseX >= imgX && mouseX <= imgX + image.getWidth()
                    && mouseY >= imgY && mouseY <= imgY + image.getHeight();
        }
        return false;
    }
}
