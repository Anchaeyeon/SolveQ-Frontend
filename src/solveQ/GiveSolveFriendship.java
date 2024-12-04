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

public class GiveSolveFriendship {
    public static void main(String[] args) {
        // JFrame 생성
        JFrame frame = new JFrame("GiveSolve 화면(우정)");
        frame.setSize(1920, 1080); // 프레임 크기
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 동작 설정

        // 커스텀 JPanel을 만들어서 프레임에 설정
        GradientPanel panel = new GradientPanel();
        frame.setContentPane(panel); // JPanel을 프레임의 컨텐츠 패널로 설정
        frame.setVisible(true); // 프레임을 화면에 출력
    }

    // 우정 관련 이미지를 그리고, 그라데이션과 텍스트를 표시하는 커스텀 JPanel 클래스
    static class GradientPanel extends JPanel {
        private BufferedImage image1, image2, image3, image4, image5; // 5개의 이미지
        private String friendshipAdvice; // 랜덤 우정 조언

        private final int xOffset1 = -550;
        private final int xOffset2 = -440;
        private final int xOffset3 = 550;
        private final int xOffset4 = 440;

        public GradientPanel() {
            try {
                // 이미지 로드
                image1 = ImageIO.read(new File("img/Line.png"));
                image2 = ImageIO.read(new File("img/round.png"));
                image3 = ImageIO.read(new File("img/Line.png"));
                image4 = ImageIO.read(new File("img/round.png"));
                image5 = ImageIO.read(new File("img/plug.png"));

                // 랜덤 우정 조언 로드
                friendshipAdvice = getRandomAdvice("keywordText/KeywordFriendship.txt");
            } catch (IOException e) {
                e.printStackTrace();
                friendshipAdvice = "우정에 대한 조언을 불러오지 못했습니다.";
            }
        }

        // 텍스트 파일에서 랜덤 조언을 가져오는 메서드
        private String getRandomAdvice(String filename) {
            try {
                // 텍스트 파일에서 모든 라인 읽기
                List<String> lines = Files.readAllLines(Paths.get(filename));

                if (!lines.isEmpty()) {
                    // 랜덤 인덱스 생성
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

            // 배경 그라데이션 설정
            Color startColor = Color.decode("#B365FD"); // 시작 색상
            Color endColor = Color.decode("#41116D");   // 끝 색상
            int width = getWidth();
            int height = getHeight();

            GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, height, endColor);
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, width, height); // 그라데이션 배경 채우기

            // 이미지를 그리는 부분
            drawImage(g, image1, xOffset1);
            drawImage(g, image2, xOffset2);
            drawImage(g, image3, xOffset3);
            drawImage(g, image4, xOffset4);

            // 다섯 번째 이미지를 고정 위치에 그리기
            if (image5 != null) {
                int imgWidth = image5.getWidth();
                int imgHeight = image5.getHeight();
                int x = 1420; // x좌표
                int y = 700;  // y좌표
                g.drawImage(image5, x, y, this);
            }

            // 랜덤 우정 조언 텍스트 그리기
            g.setColor(Color.WHITE);
            try {
                Font pretendardFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Pretendard-Bold.otf"))
                        .deriveFont(40f);
                g.setFont(pretendardFont);
            } catch (FontFormatException | IOException e) {
                g.setFont(new Font("Serif", Font.BOLD, 40));
            }

            FontMetrics metrics = g.getFontMetrics();
            int textWidth = metrics.stringWidth(friendshipAdvice);
            g.drawString(friendshipAdvice, (width - textWidth) / 2, height / 2); // 텍스트 중앙에 그리기
        }

        // 이미지를 그리는 메서드
        private void drawImage(Graphics g, BufferedImage image, int xOffset) {
            if (image != null) {
                int imgWidth = image.getWidth();
                int imgHeight = image.getHeight();
                int x = (getWidth() - imgWidth) / 2 + xOffset; // x좌표 조정
                int y = (getHeight() - imgHeight) / 2; // y좌표 중앙
                g.drawImage(image, x, y, this);
            }
        }
    }
}
