package src.solveQ;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.io.File;

public class lovePage extends JFrame {
    private JTextArea inputField; // 입력을 받을 텍스트 필드

    public lovePage() {
        setTitle("lovePage");
        setSize(1920, 1080); // 프레임 크기
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 동작 설정

        // 커스텀 패널 추가
        GradientPanel panel = new GradientPanel();
        setContentPane(panel); // 프레임의 기본 컨텐츠 패널로 설정
        setVisible(true); // 프레임 출력
    }

    // 그라데이션을 그리는 커스텀 패널 클래스
    class GradientPanel extends JPanel {
        // 클릭 영역 정의 (사각형)
        private final Rectangle inputRect = new Rectangle(280, 250, 954, 400);

        public GradientPanel() {
            // 마우스 클릭 이벤트 리스너 추가
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (inputRect.contains(e.getPoint())) {
                        showInputField();
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // 그라데이션 설정
            Color startColor = Color.decode("#B365FD");
            Color endColor = Color.decode("#41116D");
            int width = getWidth();
            int height = getHeight();
            GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, height, endColor);
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, width, height);

            Font pretendardFont;
            try {
                pretendardFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Pretendard-Bold.otf"));
                pretendardFont = pretendardFont.deriveFont(30f);
            } catch (Exception e) {
                e.printStackTrace();
                pretendardFont = new Font("Serif", Font.BOLD, 30);
            }
            g.setFont(pretendardFont);

            worryWriteText(g, "당신의 고민을 적어주세요.", 670, 50, 195, 170);
            // 사각형 그리기
            g.setColor(Color.white);
            g.fillRoundRect(inputRect.x, inputRect.y, inputRect.width, inputRect.height, 20, 20);
        }

        private void worryWriteText(Graphics g, String text, int x, int y, int width, int height) {
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
        private void showInputField() {
            // 입력 필드가 이미 있으면 새로 생성하지 않음
            if (inputField == null) {
                inputField = new JTextArea();
                inputField.setLineWrap(true);
                inputField.setWrapStyleWord(true);
                inputField.setBackground(Color.WHITE);
                inputField.setFont(new Font("Serif", Font.PLAIN, 20));
                inputField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                inputField.setBounds(inputRect.x + 10, inputRect.y + 10, inputRect.width - 20, inputRect.height - 20);

                // 입력 필드를 패널에 추가하고 포커스를 설정
                this.setLayout(null);
                this.add(inputField);
                inputField.setVisible(true);
                inputField.requestFocus();
            }
        }
    }

    public static void main(String[] args) {
        new lovePage();
    }
}
