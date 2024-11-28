package src.solveQ;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class signup extends JFrame {
    public signup() {
        setTitle("Login");
        setSize(1920, 1080); // 프레임 크기
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 버튼 동작 설정
        setLayout(null); // 레이아웃 설정 (null로 설정해서 절대 위치 사용)

        // 배경색을 하얀색으로 설정
        getContentPane().setBackground(Color.WHITE);

        // 이미지 로드
        ImageIcon imageIcon = null;
        try {
            File imgFile = new File("img/singup.png"); // 경로를 직접 지정
            if (imgFile.exists()) {
                imageIcon = new ImageIcon(imgFile.getAbsolutePath());
            } else {
                throw new Exception("이미지 파일이 존재하지 않습니다: " + imgFile.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (imageIcon != null) {
            JLabel imageLabel = new JLabel(imageIcon);
            // 이미지 위치 및 크기 설정
            imageLabel.setBounds(700, 100, imageIcon.getIconWidth(), imageIcon.getIconHeight());
            add(imageLabel);
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new signup(); // 프레임 생성
    }
}
