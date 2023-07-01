package org.game;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Игровая панель.
 * Позволяет игре работать(запускает её, обновляет данные о карте, сущностях(включая игрока) и объектах).
 * Задает начальные параметры игры(разрешение, размер моделей сущностей и объектов и другие)
 * Отрисовывает карту, сущности и объекты.
 * Карта состоит из определенного числа плиток заданного размера.
 * Сущность и объект представляет собой 1 плитку заданного размера.
 */
public class GamePanel extends JPanel implements Runnable {

    //Настройки экрана
    final int originalTileSize = 64; // 64х64 - оригинальный размер плитки
    final int scale = 1; // Коэфицент масштабирования
    public final int tileSize = originalTileSize * scale; // 64х64 - итоговый размер плитки
    public final int maxScreenCol = 16; // максимальное количество плиток по горизонтали(колонок)
    public final int maxScreenRow = 12; // максимальное количество плиток по вертикали(строк)
    public final int screenWidth = tileSize * maxScreenCol; // ширина окна(1024 пикселей)
    public final int screenHeight = tileSize * maxScreenRow; // высота окна(768 пикселей)

    // Настройки мира
    public final int maxWorldCol = 50; // Максимальное количество плиток на карте по вертикали
    public final int maxWorldRow = 50; // Максимальное количество плиток на карте по горизонтали

    int fps = 60; // значение FPS в игре


    /**
     * Вызов различных хендлеров
     */
    TileManager tileM = new TileManager(this); // Отрисовывает всю карту
    public KeyHandler keyH = new KeyHandler(this); // Хендлер нажатия клавиш
    Sound music = new Sound(); // Хендлер музыки
    Sound se = new Sound(); // Хендлер звуков(sound effect)
    public CollisionChecker cChecker = new CollisionChecker(this); // Проверка коллизий
    public AssetSetter aSetter = new AssetSetter(this); // Расставляет объекты по карте, созданные этим сеттером
    public UI ui = new UI(this); // Рисует окна, худ, объекты, шрифт и т.д.
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    // Сущности и объекты
    public Player player = new Player(this, keyH); // Хеднлер игрока
    public Entity[] obj = new Entity[10]; // Количество одновременно отображаемых в игре объектов
    public Entity[] npc = new Entity[10]; // Количество одновременно отображаемых в игре сущностей
    public Entity[] monster = new Entity[20]; // Количество одновременно отображаемых в игре монстров
    ArrayList<Entity> entityList = new ArrayList<>();

    // Состояние игры(пауза и т.д.)
    public int gameState;
    public final int titleState = 0; // загрузочный экран
    public final int playState = 1; // игровой процесс
    public final int pauseState = 2; // пауза
    public final int dialogueState = 3; // диалоги


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    /**
     * Метод, добавляющий объекты в игру(на карту), поигрывающий музыку(если нужно) и т.д.
     */
    public void setupGame() {
        aSetter.setObject(); // добавляем объекты в игру
        aSetter.setNPC(); // добавляем npc в игру
        aSetter.setMonster(); // добавляем монстров в игру
        //playMusic(0);
        //stopMusic();
        gameState = titleState;
    }

    /**
     * Метод, запускающий game loop
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/(double)fps; // 0.01666 seconds
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


            // Проверка фпс
            if(timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    /**
     * Метод, обновляющий данные об игроке
     * Тем самым игрок двигается, прогружается карта вокруг него и т.д.
     */
    public void update(){
        if(gameState == playState) {
            // Обновляем игрока
            player.update();

            // Обновляем npc
            for (Entity entity : npc)
                if (entity != null) {
                    entity.update();
                }
            //Обновляем монстров
            for (Entity entity : monster)
                if (entity != null) {
                    entity.update();
                }
        }
        if(gameState == pauseState) {

        }
    }

    /**
     * Отрисовывает всё(карту, модели, объекты и т.д.)
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // Debug
        long drawStart = 0;
        if(keyH.checkDrawTime) {
            drawStart = System.nanoTime();
        }

        // Загрузочный экран
        if(gameState == titleState) {
            ui.draw(g2);
        }
        // Другое
        else {
            // Плитки
            tileM.draw(g2);

            // Сущности
            entityList.add(player);

            for (Entity entity : npc) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            for (Entity object : obj) {
                if (object != null) {
                    entityList.add(object);
                }
            }

            for (Entity entity : monster) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            // Сортировка
            entityList.sort(Comparator.comparingInt(e -> e.worldY));

            // Прорисовка сущностей
            for (Entity entity : entityList) {
                entity.draw(g2);
            }

            entityList.clear();

//            // Очистка списка сущностей
//            for(int i = 0; i < entityList.size(); i++) {
//                entityList.remove(i);
//            }

            // Интерфейс
            ui.draw(g2);
        }

        // Debug
        if(keyH.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose();
    }

    /**
     * Метод, проигрывающий музыку
     * Получает на вход индекс звукового файла и зацикливает его
     */
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    /**
     * Метод, останавливающий музыку
     */
    public void stopMusic() {
        music.stop();
    }

    /**
     * Метод, проигрывающий звуковой эффект(SE - sound effect)
     * Получает индекс звукового файла и проигрывает его
     */
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
