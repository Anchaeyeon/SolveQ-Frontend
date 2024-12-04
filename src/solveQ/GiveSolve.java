package src.solveQ;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

public class GiveSolve extends JPanel {
    private BufferedImage image1; // 첫 번째 이미지
    private BufferedImage image2; // 두 번째 이미지
    private BufferedImage image3; // 세 번째 이미지
    private BufferedImage image4; // 네 번째 이미지
    private BufferedImage image5; // 다섯 번째 이미지

    private final int xOffset1 = -550; // 첫 번째 이미지 왼쪽으로 이동할 오프셋 (음수값)
    private final int xOffset2 = -440; // 두 번째 이미지 왼쪽으로 이동할 오프셋 (음수값)
    private final int xOffset3 = 550;  // 세 번째 이미지 오른쪽으로 이동할 오프셋 (양수값)
    private final int xOffset4 = 440;  // 네 번째 이미지 오른쪽으로 이동할 오프셋 (양수값)

    private String loveAdvice; // 랜덤 조언을 저장할 변수

    public GiveSolve() {
        // JPanel 생성 시 호출되는 생성자
        setLayout(null); // 절대 위치 레이아웃 사용

        try {
            // 이미지 로드
            image1 = ImageIO.read(new File("img/Line.png"));
            image2 = ImageIO.read(new File("img/round.png"));
            image3 = ImageIO.read(new File("img/Line.png"));
            image4 = ImageIO.read(new File("img/round.png"));
            image5 = ImageIO.read(new File("img/plug.png"));

            // 랜덤 조언 로드
            loveAdvice = getRandomAdvice("keywordText/KeywordLove.txt");
        } catch (IOException e) {
            e.printStackTrace();
            loveAdvice = "사랑에 대한 조언을 불러오지 못했습니다.";
        }
    }

    // 텍스트 파일에서 랜덤 조언을 가져오는 메서드
    private String getRandomAdvice(String filename) {
        try {
            // 파일에서 모든 라인 읽기 (상대 경로 사용)
            List<String> lines = Files.readAllLines(Paths.get(filename));  // "KeywordText/KeywordLove.txt" 파일 경로

            // 라인이 비어있지 않은지 확인
            if (!lines.isEmpty()) {
                // 랜덤 인덱스 생성
                Random random = new Random();
                int randomIndex = random.nextInt(lines.size());

                // 랜덤 라인 반환
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

        // 그라데이션 색상 설정
        Color startColor = Color.decode("#B365FD"); // HEX 색상 #B365FD
        Color endColor = Color.decode("#41116D");   // HEX 색상 #41116D
        int width = getWidth();
        int height = getHeight();

        // 세로 방향으로 그라데이션 적용
        GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, height, endColor);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height); // 그라데이션으로 배경 채우기

        // 첫 번째 이미지 그리기
        if (image1 != null) {
            int imgWidth = image1.getWidth();
            int imgHeight = image1.getHeight();
            int x = (width - imgWidth) / 2 + xOffset1; // 첫 번째 이미지의 x좌표 계산
            int y = (height - imgHeight) / 2; // y좌표는 화면 중앙으로
            g.drawImage(image1, x, y, this);
        }

        // 두 번째 이미지 그리기
        if (image2 != null) {
            int imgWidth = image2.getWidth();
            int imgHeight = image2.getHeight();
            int x = (width - imgWidth) / 2 + xOffset2; // 두 번째 이미지의 x좌표 계산
            int y = (height - imgHeight) / 2; // y좌표는 화면 중앙으로
            g.drawImage(image2, x, y, this);
        }

        // 세 번째 이미지 그리기
        if (image3 != null) {
            int imgWidth = image3.getWidth();
            int imgHeight = image3.getHeight();
            int x = (width - imgWidth) / 2 + xOffset3; // 세 번째 이미지의 x좌표 계산
            int y = (height - imgHeight) / 2; // y좌표는 화면 중앙으로
            g.drawImage(image3, x, y, this);
        }

        // 네 번째 이미지 그리기
        if (image4 != null) {
            int imgWidth = image4.getWidth();
            int imgHeight = image4.getHeight();
            int x = (width - imgWidth) / 2 + xOffset4; // 네 번째 이미지의 x좌표 계산
            int y = (height - imgHeight) / 2; // y좌표는 화면 중앙으로
            g.drawImage(image4, x, y, this);
        }

        // 다섯 번째 이미지 고정 위치에 그리기
        if (image5 != null) {
            int imgWidth = image5.getWidth();
            int imgHeight = image5.getHeight();
            int x = 1420; // 다섯 번째 이미지의 x좌표
            int y = 700;  // 다섯 번째 이미지의 y좌표
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
        g.drawString(loveAdvice, (width - textWidth) / 2, height / 2); // 텍스트 중앙에 그리기
    }
}