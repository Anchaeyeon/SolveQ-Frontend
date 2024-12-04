package src.solveQ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.nio.file.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

public class GiveSolve extends JPanel {
    private BufferedImage image1; // 첫 번째 이미지
    private BufferedImage image2; // 두 번째 이미지
    private BufferedImage image3; // 세 번째 이미지
    private BufferedImage image4; // 네 번째 이미지
    private BufferedImage image5; // 다섯 번째 이미지

    private final int xOffset1 = -550; // 첫 번째 이미지의 x 오프셋
    private final int xOffset2 = -440; // 두 번째 이미지의 x 오프셋
    private final int xOffset3 = 550;  // 세 번째 이미지의 x 오프셋
    private final int xOffset4 = 440;  // 네 번째 이미지의 x 오프셋

    private String loveAdvice; // 랜덤 조언 저장 변수
    private solveRandomdb dbHandler = new solveRandomdb(); // 데이터베이스 핸들러 인스턴스

    public GiveSolve() {
        // JPanel 설정
        setLayout(null);

        try {
            // 이미지 로드
            image1 = ImageIO.read(new File("img/Line.png"));
            image2 = ImageIO.read(new File("img/round.png"));
            image3 = ImageIO.read(new File("img/Line.png"));
            image4 = ImageIO.read(new File("img/round.png"));
            image5 = ImageIO.read(new File("img/plug.png"));

            // 랜덤 조언 로드 및 데이터베이스에 저장
            loveAdvice = getRandomAdvice("keywordText/KeywordLove.txt");
            dbHandler.saveAdviceToDatabase(loveAdvice); // 데이터베이스에 조언 저장
        } catch (IOException e) {
            e.printStackTrace();
            loveAdvice = "사랑에 대한 조언을 불러오지 못했습니다.";
        }

        // 마우스 클릭 이벤트 리스너 추가 (Diary 화면으로 이동)
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showDiaryScreen(); // Diary 화면 전환
            }
        });
    }

    // 텍스트 파일에서 랜덤 조언을 가져오는 메서드
    private String getRandomAdvice(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename)); // 파일 경로 사용

            if (!lines.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(lines.size());
                return lines.get(randomIndex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "조언을 찾을 수 없습니다.";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // 그라데이션 배경 설정
        Color startColor = Color.decode("#B365FD");
        Color endColor = Color.decode("#41116D");
        int width = getWidth();
        int height = getHeight();
        GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, height, endColor);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);

        // 이미지 그리기
        drawImage(g, image1, xOffset1, width, height);
        drawImage(g, image2, xOffset2, width, height);
        drawImage(g, image3, xOffset3, width, height);
        drawImage(g, image4, xOffset4, width, height);

        // 다섯 번째 이미지 고정 위치에 그리기
        if (image5 != null) {
            int imgWidth = image5.getWidth();
            int imgHeight = image5.getHeight();
            int x = 1420; // 고정 x좌표
            int y = 700;  // 고정 y좌표
            g.drawImage(image5, x, y, this);
        }

        // 랜덤 조언 텍스트 그리기
        try {
            Font pretendardFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Pretendard-Bold.otf"))
                    .deriveFont(40f);
            g.setFont(pretendardFont);
        } catch (FontFormatException | IOException e) {
            g.setFont(new Font("Serif", Font.BOLD, 40));
        }

        g.setColor(Color.WHITE);
        FontMetrics metrics = g.getFontMetrics();
        int textWidth = metrics.stringWidth(loveAdvice);
        g.drawString(loveAdvice, (width - textWidth) / 2, height / 2);
    }

    private void drawImage(Graphics g, BufferedImage image, int xOffset, int width, int height) {
        if (image != null) {
            int imgWidth = image.getWidth();
            int imgHeight = image.getHeight();
            int x = (width - imgWidth) / 2 + xOffset;
            int y = (height - imgHeight) / 2;
            g.drawImage(image, x, y, this);
        }
    }

    // Diary 화면으로 전환하는 메서드
    private void showDiaryScreen() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (topFrame instanceof Main) {
            Main mainFrame = (Main) topFrame;
            mainFrame.showDiaryScreen(); // Diary 화면 전환
        }
    }
}
