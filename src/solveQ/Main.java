package src.solveQ;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public Main() {
        setTitle("SolveQ");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // start 화면 추가
        start startScreen = new start(this);
        cardPanel.add(startScreen, "Start");

        // keyword 화면 추가
        keyword keywordScreen = new keyword(this);
        cardPanel.add(keywordScreen, "Keyword");

        // lovePage 추가
        LovePage loveScreen = new LovePage();
        cardPanel.add(loveScreen, "Love");

        setContentPane(cardPanel);

        // 처음에 start 화면을 보여줌
        cardLayout.show(cardPanel, "Start");

        setVisible(true);
    }

    // 화면 전환 메서드
    public void showKeywordScreen() {
        cardLayout.show(cardPanel, "Keyword");
    }

    public void showLoveScreen() {
        cardLayout.show(cardPanel, "Love");
    }

    public static void main(String[] args) {
        new Main();
    }
}
