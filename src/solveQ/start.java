package src.solveQ;

import javax.swing.*;
public class start extends JFrame {
    public start() {
        setTitle("스윙 프레임 만들기");
        setSize(1440,1024); // 프레임 크기
        setVisible(true); // 프레임 출력
    }
    public static void main(String[] args) {
        start frame = new start();
    }
}