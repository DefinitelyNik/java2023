package org.game;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Настройки экрана
    final int originalTileSize = 64; // 64х64 - оригинальный размер плитки
    final int scale = 1;
    public final int tileSize = originalTileSize * scale; // 64х64 - итоговый размер плитки
    public final int maxScreenCol = 16; // максимальное количество плиток по горизонтали(колонок)
    public final int maxScreenRow = 12; // максимальное количество плиток по вертикали(строк)
    public final int screenWidth = tileSize * maxScreenCol; // ширина окна(1024 пикселей)
    public final int screenHeight = tileSize * maxScreenRow; // высота окна(768 пикселей)

    // Настройки мира
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60; // значение FPS в игре

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10]; // Будет отрисовываться максимум по 10 объектов за раз


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    /**
     * добавляет объекты в игру(на карту)
     */
    public void setupGame() {
        aSetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // 0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        //Game loop
        while(gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }


            //Checking the actual FPS
            if(timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }


    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        // Tile
        tileM.draw(g2);

        // Object
        for (SuperObject superObject : obj) {
            if (superObject != null) superObject.draw(g2, this);
        }

        // Player
        player.draw(g2);

        g2.dispose();
    }
}
