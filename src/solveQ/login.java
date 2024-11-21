package src.solveQ;

import javax.swing.*;
import java.awt.Color; // Color 클래스를 임포트

public class login extends JFrame {
    public login() {
        setTitle("Login");
        setSize(1920, 1080); // 프레임 크기
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 버튼 동작 설정
        setLayout(null); // 레이아웃 설정 (null로 설정해서 절대 위치 사용)

        // 배경색을 하얀색으로 설정
        getContentPane().setBackground(Color.WHITE); // 콘텐츠 패널의 배경색을 흰색으로 설정

        // 이미지 로드
        ImageIcon imageIcon = new ImageIcon("img/LOGIN.png"); // 이미지 경로 설정
        JLabel imageLabel = new JLabel(imageIcon); // JLabel을 사용해 이미지 표시

        // 이미지 위치 및 크기 설정
        imageLabel.setBounds(720, 200, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        add(imageLabel); // 이미지 라벨을 프레임에 추가

        setVisible(true); // 프레임 출력
    }

    public static void main(String[] args) {
        new login(); // 프레임 생성
    }
}
