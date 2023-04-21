package entity;

import org.game.GamePanel;
import org.game.KeyHandler;
import org.game.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Класс игрока
 * Является сущностью, поэтому наследует этот класс
 * Тут устанавливается положение игрока на карте, его скорость, коллизия и др.
 * Устанавливаются анимации его передвижений
 * Обрабатываются его передвижения по карте
 * Отрисовывается сам игрок и его анимации
 */
public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX; // координата игрока по оси Х
    public final int screenY; // координата игрока по оси Y

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth /2 - (gp.tileSize /2);
        screenY = gp.screenHeight /2 - (gp.tileSize /2);

        solidArea = new Rectangle(26, 32, 12, 24);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }


    /**
     * Метод, устанавливающий дефолтные параметры игрока
     */
    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    /**
     * Метод, получающий картинки для анимации передвижения игрока
     */
    public void getPlayerImage() {
        up1 = setup("player_up_1");
        up2 = setup("player_up_2");
        down1 = setup("player_down_1");
        down2 = setup("player_down_2");
        left1 = setup("player_left_1");
        left2 = setup("player_left_2");
        right1 = setup("player_right_1");
        right2 = setup("player_right_2");
    }

    /**
     * Настройка изображения модели игрока(масштабирование)
     */
    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/" + imageName +".png")));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * Метод, обновляющий положение игрока на карте, его коллизию и анимации передвижения
     */
    public void update() {

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if(keyH.upPressed) {
                direction = "up";
            }
            else if(keyH.downPressed) {
                direction = "down";
            }
            else if(keyH.leftPressed) {
                direction = "left";
            }
            else if(keyH.rightPressed) {
                direction = "right";
            }

            // Проверка коллизии плитки
            collisionOn = false;
            gp.cChecker.CheckTile(this);

            // Проверка коллизии объектов
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // Если коллизии нет, игрок может двигаться
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
    }

    public void pickUpObject(int i) {
        if(i != 999) {
            //Тут будут новые объекты
        }
    }

    /**
     * Метод, отрисовывающий анимации игрока
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

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
        g2.drawImage(image, screenX, screenY, null);
    }
}
