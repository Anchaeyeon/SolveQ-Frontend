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

        // 사랑 페이지 추가
        LovePage loveScreen = new LovePage();
        cardPanel.add(loveScreen, "Love");

        // 우정 페이지 추가
        FriendshipPage friendshipScreen = new FriendshipPage();
        cardPanel.add(friendshipScreen, "Friendship");

        // 가족 페이지 추가
        FamilyPage familyScreen = new FamilyPage();
        cardPanel.add(familyScreen, "Family");

        // 학교 페이지 추가
        SchoolPage schoolScreen = new SchoolPage();
        cardPanel.add(schoolScreen, "School");

        // 기타 페이지 추가
        OtherPage otherScreen = new OtherPage();
        cardPanel.add(otherScreen, "Other");

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
    public void showFriendshipScreen() {
        cardLayout.show(cardPanel, "Friendship");
    }
    public void showFamilyScreen() {
        cardLayout.show(cardPanel, "Family");
    }
    public void showSchoolScreen() {
        cardLayout.show(cardPanel, "School");
    }
    public void showOtherlScreen() {
        cardLayout.show(cardPanel, "Other");
    }

    public static void main(String[] args) {
        new Main();
    }
}
