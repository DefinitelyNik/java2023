package entity;

import org.game.GamePanel;
import org.game.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Абстрактный класс для любый сущностей(игрок, монстр и т.д.)
 * Содержит данные о всех сущностях в игре, включая игрока
 */
public class Entity {
    GamePanel gp;
    public int worldX, worldY; // Координаты игрока на карте
    public int speed; // Скорость передвижения игрока по карте
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // Переменнные, отвечающие за направление движения игрока
    public String direction; // Направление движения игрока
    public int spriteCounter = 0; // Переменная для отрисовки анимации передвижения сущностей
    public int spriteNum = 1; // Переменная для отрисовки анимации передвижения сущностей
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // Область коллизии сущности(Квадрат, размер которого меньше, чем размер модели сущности)
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false; // Есть коллизия или нет
    public int actionLockCounter = 0; // Переменная, которая нужна для того, чтобы сущности не совершали миллион действий в секунду(что-то типа задержки между действиями)

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {

    }

    public void update() {
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);

        if(!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }

        spriteCounter++;
        if(spriteCounter > 12) {
            if(spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch (direction) {
                case "up" -> {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                case "down" -> {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                case "left" -> {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                case "right" -> {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    /**
     * Настройка изображения модели сущности(масштабирование)
     */
    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
