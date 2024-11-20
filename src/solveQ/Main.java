package src.solveQ;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Main extends JFrame {
    public Main() {
        setTitle("300x300 스윙 프레임 만들기");
        setSize(300,300); // 프레임 크기 300x300
        setVisible(true); // 프레임 출력
    }
    public static void main(String[] args) {
        Main frame = new Main();
    }
}