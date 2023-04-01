package org.example;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //настройки
    final int originalTileSize = 64; // 64х64 - оригинальный размер плитки
    final int scale = 1;
    final int tileSize = originalTileSize * scale; // 64х64 - итоговый размер плитки
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 1024 пикселей
    final int screenHeight = tileSize * maxScreenRow; // 768 пикселей

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

    }
}
