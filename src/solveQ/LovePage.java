package src.solveQ;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;

public class LovePage extends JPanel {
    private JTextArea inputField; // 입력을 받을 텍스트 필드
    private Image image; // 맨 위에 표시할 이미지
    private JButton nextButton; // '해결책 보기' 버튼

    public LovePage() {
        // 이미지 로드
        try {
            image = ImageIO.read(new File("img/love.png")); // 이미지 경로
        } catch (IOException e) {
            e.printStackTrace();
            image = null; // 이미지 로드 실패 시 null
        }

        // 패널 설정
        setLayout(null);
        setSize(1920, 1080); // 패널 크기
        setBackground(new Color(0, 0, 0, 0)); // 투명 배경을 위해 설정

        // '해결책 보기' 버튼 생성 및 설정
        nextButton = new JButton("해결책 보기");
        nextButton.setBounds(600, 680, 300, 60);
        nextButton.setBackground(Color.WHITE);
        nextButton.setForeground(Color.decode("#41116D"));
        nextButton.setFocusPainted(false);
        nextButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // 버튼의 폰트 설정
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Pretendard-Bold.otf"));
            customFont = customFont.deriveFont(30f); // 원하는 크기로 설정
            nextButton.setFont(customFont);
        } catch (Exception e) {
            e.printStackTrace();
            nextButton.setFont(new Font("Serif", Font.BOLD, 30)); // 기본 폰트 설정
        }

        // 버튼 클릭 이벤트 리스너 추가
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputField != null && !inputField.getText().isEmpty()) {
                    // 입력된 내용을 데이터베이스에 저장
                    worryInsertdb.saveWorry(inputField.getText());
                } else {
                    JOptionPane.showMessageDialog(LovePage.this, "고민을 입력해 주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
                }

                // Main 클래스 인스턴스를 가져와서 화면 전환 호출
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(LovePage.this);
                if (topFrame instanceof Main) {
                    Main mainFrame = (Main) topFrame;
                    mainFrame.showDiaryCoverScreen();
                }
            }
        });

        // 버튼을 패널에 추가
        this.add(nextButton);

        // 마우스 클릭 이벤트 리스너 추가 (입력 필드 활성화)
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (new Rectangle(280, 250, 954, 400).contains(e.getPoint())) {
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

        // 이미지 표시 (이미지가 있는 경우)
        if (image != null) {
            g.drawImage(image, 740, 40, 40, 35, this); // 이미지 크기 & 위치 조정
        }

        // 날짜 표시
        g.setColor(Color.WHITE);
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Pretendard-Bold.otf"));
            font = font.deriveFont(30f);
        } catch (Exception e) {
            e.printStackTrace();
            font = new Font("Serif", Font.BOLD, 30);
        }
        g.setFont(font);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        g.drawString(currentDate, 670, 120); // 날짜 위치 설정

        // 입력할 사각형
        g.setColor(Color.white);
        g.fillRoundRect(280, 250, 954, 400, 20, 20);

        // 해결책 보는 버튼 사각형
        g.setColor(Color.white);
        g.fillRoundRect(600, 680, 300, 60, 60, 60);

        g.setFont(font);
        drawCenteredText(g, "당신의 고민을 적어주세요.", 670, 50, 195, 170, Color.white, 50f);
        drawCenteredText(g, "해결책 보기", 650, 660, 200, 20, Color.decode("#41116D"), 30f);
    }

    private void drawCenteredText(Graphics g, String text, int x, int y, int width, int height, Color color, float fontSize) {
        Font originalFont = g.getFont();
        Font newFont = originalFont.deriveFont(fontSize); // 매개변수로 받은 글씨 크기 설정
        g.setFont(newFont);

        g.setColor(color);

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
            inputField.setFont(new Font("fonts/Pretendard-Bold.otf", Font.PLAIN, 20));
            inputField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            inputField.setBounds(280 + 10, 250 + 10, 954 - 20, 400 - 20);

            // 입력 필드를 패널에 추가하고 포커스를 설정
            this.add(inputField);
            inputField.setVisible(true);
            inputField.requestFocus();
        }
    }
}
