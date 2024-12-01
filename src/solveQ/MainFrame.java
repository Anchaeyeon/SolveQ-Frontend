package src.solveQ;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public MainFrame() {
        setTitle("SolveQ Application");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 창을 화면 중앙에 위치시킴

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // start 화면 추가
        start startScreen = new start();
        cardPanel.add(startScreen, "Start");

        // keyword 화면 추가
        keyword keywordScreen = new keyword();
        cardPanel.add(keywordScreen, "Keyword");

        // 패널을 프레임의 콘텐츠로 설정
        add(cardPanel);
        setVisible(true);

        // 처음에 start 화면을 보여줌
        cardLayout.show(cardPanel, "Start");
    }

    public static void main(String[] args) {
        new MainFrame();
    }

    // 필요한 경우 화면 전환을 위한 메서드
    public void showKeywordScreen() {
        cardLayout.show(cardPanel, "Keyword");
    }
}
