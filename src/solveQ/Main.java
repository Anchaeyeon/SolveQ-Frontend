package src.solveQ;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public Main() {
        setTitle("SolveQ");
        setSize(1920, 1080); // 화면 크기 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 동작 설정

        cardLayout = new CardLayout(); // 카드 레이아웃 설정
        cardPanel = new JPanel(cardLayout); // 카드 레이아웃을 담을 패널

        // 화면들을 카드 레이아웃에 추가
        Start startScreen = new Start(this);
        cardPanel.add(startScreen, "Start");

        Keyword keywordScreen = new Keyword(this);
        cardPanel.add(keywordScreen, "Keyword");

        LovePage loveScreen = new LovePage();
        cardPanel.add(loveScreen, "Love");

        FriendshipPage friendshipScreen = new FriendshipPage();
        cardPanel.add(friendshipScreen, "Friendship");

        FamilyPage familyScreen = new FamilyPage();
        cardPanel.add(familyScreen, "Family");

        SchoolPage schoolScreen = new SchoolPage();
        cardPanel.add(schoolScreen, "School");

        OtherPage otherScreen = new OtherPage();
        cardPanel.add(otherScreen, "Other");

        Loding lodingScreen = new Loding();
        cardPanel.add(lodingScreen, "Loding");

        DiaryCover diaryCoverScreen = new DiaryCover(this);
        cardPanel.add(diaryCoverScreen, "DiaryCover");

        // GiveSolve 화면 추가
        GiveSolve giveSolveScreen = new GiveSolve();
        cardPanel.add(giveSolveScreen, "GiveSolve");

        setContentPane(cardPanel); // 패널을 프레임의 콘텐츠 패널로 설정

        // 처음에 Start 화면을 보여줌
        cardLayout.show(cardPanel, "Start");

        setVisible(true); // 프레임을 보여줌
    }

    // 화면 전환 메서드들
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

    public void showOtherScreen() {
        cardLayout.show(cardPanel, "Other");
    }

    public void showDiaryCoverScreen() {
        cardLayout.show(cardPanel, "DiaryCover");
    }

    // GiveSolve 화면으로 전환하는 메서드
    public void showGiveSolveScreen() {
        cardLayout.show(cardPanel, "GiveSolve");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}