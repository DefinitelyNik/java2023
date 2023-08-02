package org.game;

import entity.Entity;
import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Класс, полностью отрисовывающий пользовательский интерфейс
 * В интерфейс входят: худ, различные экраны, некоторые объекты, окна, шрифты и т.д
 */
public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;// Кастомный шрифт
    BufferedImage heart_full, heart_half, heart_blank; // 3 изображения объекта "сердце"(здоровье игрока)
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0; // frames
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; // 0: первый страница загрузочного экрана 1: вторая страница и т.д.

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        // Создание hud'а
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    /**
     * Метод, показывающий сообщение на экране
     */
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    /**
     * Метод, отрисовывающий интерфейс и экраны
     */
    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        // Загрузочный экран
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // Состояние игры(идёт игра)
        if(gp.gameState == gp.playState) {
            drawPlayerLife();
        }
        // Состояние игры(пауза)
        if(gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        // Состояние игры(диалог)
        if(gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
    }

    /**
     * Метод, отрисовывающий здоровье игрока
     */
    public void drawPlayerLife() {
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        // Прорисовка пустых сердец
        while(i < gp.player.maxLife/2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // Возвращаем исходные значения
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        // Прорисовка текущего количества хп
        while(i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;

            if(i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }

            i++;
            x += gp.tileSize;
        }
    }

    /**
     * Метод, отрисовавающий начальный экран
     */
    public void drawTitleScreen() {

        if(titleScreenState == 0) {
            // Цвет фона
            g2.setColor(new Color(0,0,0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // Название игры на загрузочном экране
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Sleepytale";
            int x = getXForCenteredText(text);
            int y = gp.tileSize * 3;

            // Тень текста
            g2.setColor(Color.darkGray);
            g2.drawString(text, x+5, y+5);

            // Основной цвет текста
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // Картинка персонажа
            x = gp.screenWidth/2 - (gp.tileSize*2)/2;
            y += gp.tileSize*2;
            g2.drawImage(gp.player.straight, x, y, gp.tileSize*2, gp.tileSize*2, null);

            // Меню
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            // Строка новой игры
            text = "NEW GAME";
            x = getXForCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            if(commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            // Строка загрузки сохранения
            text = "LOAD GAME";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            // Строка выхода из игры
            text = "QUIT";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
        else if(titleScreenState == 1) {

            // Второе меню с выбором класса персонажа
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            // Строка выбора класса
            String text = "Select your class!";
            int x = getXForCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            // Строка класса "Fighter"
            text = "Fighter";
            x = getXForCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if(commandNum == 0) {
                g2.drawString(">", x-gp.tileSize, y);
            }

            // Строка класса "Thief"
            text = "Thief";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1) {
                g2.drawString(">", x-gp.tileSize, y);
            }

            // Строка класса "Sorcerer"
            text = "Sorcerer";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2) {
                g2.drawString(">", x-gp.tileSize, y);
            }

            // Строка возврата на первый начальный экран
            text = "Back";
            x = getXForCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if(commandNum == 3) {
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
    }

    /**
     * Метод, отрисовывающий экран паузы
     */
    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    /**
     * Метод, отрисовывающий диалоги
     */
    public void drawDialogueScreen() {
        // Окно диалога
        int x = gp.tileSize;
        int y = gp.screenHeight - gp.tileSize * 5;
        int width = gp.screenWidth - (gp.tileSize * 2);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 38F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    /**
     * Метод, отрисовывающий "подокна"(нужны для окон диалогов)
     */
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0,0,0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    /**
     * Метод, позволяющий вычислить координату х,
     * чтобы правильно выровнять текст на экране посередине(в зависимости от длинны строки)
     */
    public int getXForCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }
}
