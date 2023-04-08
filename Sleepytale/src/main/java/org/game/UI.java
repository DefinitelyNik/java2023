package org.game;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

/**
 * This class will handle all screen UI
 */
public class UI {
    GamePanel gp;
    Font arial_40;// arial 40px font
    Font arial_80B;// arial 80px bold font
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0; // frames
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        if(gameFinished) {
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // return length of the text variable
            x = GamePanel.SCREEN_WIDTH /2 - textLength/2;
            y = GamePanel.SCREEN_HEIGHT /2 - (GamePanel.TILE_SIZE *3);
            g2.drawString(text, x, y);

            text = "Your time is :" + dFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // return length of the text variable
            x = GamePanel.SCREEN_WIDTH /2 - textLength/2;
            y = GamePanel.SCREEN_HEIGHT /2 + (GamePanel.TILE_SIZE);
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // return length of the text variable
            x = GamePanel.SCREEN_WIDTH /2 - textLength/2;
            y = GamePanel.SCREEN_HEIGHT /2 - (GamePanel.TILE_SIZE);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        } else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, GamePanel.TILE_SIZE /4, GamePanel.TILE_SIZE /4, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
            g2.drawString("x " + gp.player.hasKey, 80, 65);

            // Время
            playTime += (double)1/60;
            g2.drawString("Time: " + dFormat.format(playTime), GamePanel.TILE_SIZE *13, 65);

            // Сообщение
            if(messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, GamePanel.TILE_SIZE /4, GamePanel.TILE_SIZE *5);

                messageCounter++;

                if(messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
