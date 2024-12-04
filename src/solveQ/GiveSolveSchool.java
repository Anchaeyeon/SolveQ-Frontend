package src.solveQ;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class GiveSolveSchool {
    public static void main(String[] args) {
        // 프레임 생성
        JFrame frame = new JFrame("GiveSolve 화면(학교)");
        frame.setSize(1920, 1080); // 프레임 크기
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 동작 설정

        // 커스텀 패널 생성
        GradientPanel panel = new GradientPanel();
        frame.setContentPane(panel); // 커스텀 패널을 프레임의 컨텐츠 패널로 설정
        frame.setVisible(true); // 프레임 출력
    }

    // 학교 화면용 그라데이션과 이미지를 그리는 커스텀 JPanel 클래스
    static class GradientPanel extends JPanel {
        private BufferedImage image1, image2, image3, image4, image5; // 이미지 파일들

        // 이미지 위치 조정을 위한 오프셋 값
        private final int xOffset1 = -550; // 첫 번째 이미지 왼쪽 이동
        private final int xOffset2 = -440; // 두 번째 이미지 왼쪽 이동
        private final int xOffset3 = 550;  // 세 번째 이미지 오른쪽 이동
        private final int xOffset4 = 440;  // 네 번째 이미지 오른쪽 이동

        private String schoolAdvice; // 랜덤 조언을 저장할 변수

        public GradientPanel() {
            try {
                // 이미지 로드
                image1 = loadImage("img/Line.png");
                image2 = loadImage("img/round.png");
                image3 = loadImage("img/Line.png");
                image4 = loadImage("img/round.png");
                image5 = loadImage("img/plug.png");

                // 랜덤 학교 조언 로드
                schoolAdvice = getRandomAdvice("keywordText/KeywordSchool.txt");
            } catch (IOException e) {
                e.printStackTrace();
                schoolAdvice = "학교에 대한 조언을 불러오지 못했습니다.";
            }
        }

        // 이미지 로드 메서드
        private BufferedImage loadImage(String path) throws IOException {
            return ImageIO.read(new File(path));
        }

        // 텍스트 파일에서 랜덤 조언을 가져오는 메서드
        private String getRandomAdvice(String filename) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(filename));
                if (!lines.isEmpty()) {
                    Random random = new Random();
                    return lines.get(random.nextInt(lines.size()));
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

            // 그라데이션 설정 (요청된 색상 유지)
            Color startColor = Color.decode("#B365FD"); // HEX 색상 #B365FD
            Color endColor = Color.decode("#41116D");   // HEX 색상 #41116D
            int width = getWidth();
            int height = getHeight();
            GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, height, endColor);
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, width, height);

            // 이미지 그리기 (요청된 위치 유지)
            drawImage(g, image1, width, height, xOffset1); // 첫 번째 이미지
            drawImage(g, image2, width, height, xOffset2); // 두 번째 이미지
            drawImage(g, image3, width, height, xOffset3); // 세 번째 이미지
            drawImage(g, image4, width, height, xOffset4); // 네 번째 이미지
            drawImage(g, image5, width, height, 1420, 700); // 다섯 번째 이미지 고정 위치

            // 텍스트 그리기 (요청된 위치 유지)
            drawAdviceText(g, schoolAdvice, width, height);
        }

        private void drawImage(Graphics g, BufferedImage image, int width, int height, int xOffset) {
            if (image != null) {
                int imgWidth = image.getWidth();
                int imgHeight = image.getHeight();
                int x = (width - imgWidth) / 2 + xOffset;
                int y = (height - imgHeight) / 2;
                g.drawImage(image, x, y, this);
            }
        }

        private void drawImage(Graphics g, BufferedImage image, int width, int height, int x, int y) {
            if (image != null) {
                g.drawImage(image, x, y, this);
            }
        }

        private void drawAdviceText(Graphics g, String advice, int width, int height) {
            try {
                // Pretendard 폰트를 사용하여 텍스트 렌더링
                Font font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Pretendard-Bold.otf")).deriveFont(40f);
                g.setFont(font);
            } catch (FontFormatException | IOException e) {
                g.setFont(new Font("Serif", Font.BOLD, 40)); // 기본 폰트 대체
            }

            g.setColor(Color.WHITE);
            FontMetrics metrics = g.getFontMetrics();
            int textWidth = metrics.stringWidth(advice);
            g.drawString(advice, (width - textWidth) / 2, height / 2); // 텍스트 중앙에 그리기
        }
    }
}
