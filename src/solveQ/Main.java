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
        Start startScreen = new Start(this);
        cardPanel.add(startScreen, "Start");

        // keyword 화면 추가
        Keyword keywordScreen = new Keyword(this);
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

        // 로딩 화면 추가
        Loding lodingScreen = new Loding();
        cardPanel.add(lodingScreen, "Loding");

        // 키워드 선택해서 각각 고민을 적고 해결책 보기를 누르면 DiaryCover로 이동
        DiaryCover diaryCoverScreen = new DiaryCover();
        cardPanel.add(diaryCoverScreen, "DiaryCover");

        setContentPane(cardPanel);

        // 처음에 start 화면을 보여줌
        cardLayout.show(cardPanel, "Start");

        setVisible(true);
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

    public void showOtherlScreen() {
        cardLayout.show(cardPanel, "Other");
    }

    public void showDiaryCoverScreen() {
        // 먼저 로딩 화면을 보여줌
        cardLayout.show(cardPanel, "Loding");

        // 일정 시간 후 DiaryCover 화면으로 전환
        Timer timer = new Timer(2000, e -> {
            cardLayout.show(cardPanel, "DiaryCover");
        });
        timer.setRepeats(false); // 한 번만 실행
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}