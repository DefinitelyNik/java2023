package entity;

import org.game.GamePanel;
import org.game.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Класс игрока
 * Является сущностью, поэтому наследует этот класс
 * Тут устанавливается положение игрока на карте, его скорость, коллизия и др.
 * Устанавливаются анимации его передвижений
 * Обрабатываются его передвижения по карте
 * Отрисовывается сам игрок и его анимации
 */
public class Player extends Entity{
    KeyHandler keyH;

    public final int screenX; // координата игрока по оси Х
    public final int screenY; // координата игрока по оси Y
    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);

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
        direction = "straight";

        // Статус игрока
        maxLife = 6; // 1 жизнь = 1 половина сердечка на экране
        life = maxLife;
    }

    /**
     * Метод, получающий картинки для анимации передвижения игрока
     */
    public void getPlayerImage() {
        straight = setup("/player/player_straight");
        up1 = setup("/player/player_up_1");
        up2 = setup("/player/player_up_2");
        down1 = setup("/player/player_down_1");
        down2 = setup("/player/player_down_2");
        left1 = setup("/player/player_left_1");
        left2 = setup("/player/player_left_2");
        right1 = setup("/player/player_right_1");
        right2 = setup("/player/player_right_2");
    }

    /**
     * Метод, обновляющий положение игрока на карте, его коллизию и анимации передвижения
     */
    public void update() {

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.ePressed) {

            if(keyH.upPressed) {
                direction = "up";
            }
            if(keyH.downPressed) {
                direction = "down";
            }
            if(keyH.leftPressed) {
                direction = "left";
            }
            if(keyH.rightPressed) {
                direction = "right";
            }

            // Проверка коллизии плитки
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Проверка коллизии объектов
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // Проверка коллизии сущностей
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // Проверка коллизии монстров
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // Проверка ивентов
            gp.eHandler.checkEvent();

            gp.keyH.ePressed = false;

            // Если коллизии нет, игрок может двигаться
            if(!collisionOn) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            // Выбор анимации модельки игрока
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

        if(invincible) {
            invincibleCounter++;
            if(invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    /**
     * Метод, позволяющий игроку поднимать предметы(объекты)
     */
    public void pickUpObject(int i) {
        if(i != 999) {
            //Тут будут новые объекты
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key" -> {
                    hasKey++;
                    gp.obj[i] = null;
                }
                case "Door" -> {
                    if (hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                    }
                }
            }
        }
    }

    /**
     * Метод, позволяющий взаимодействовать с другими сущностями
     * Например, можно поговорить с кем-либо
     */
    public void interactNPC(int i) {
        if(i != 999) {
            if(gp.keyH.ePressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    public void contactMonster(int i) {
        if(i != 999) {
            if(!invincible) {
                life -= 1;
                invincible = true;
            }
        }
    }

    /**
     * Метод, отрисовывающий анимации игрока
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "straight" -> {
                image = straight;
            }
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

        // Визуальные эффекты получения урона
        if(invincible) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        g2.drawImage(image, screenX, screenY, null);

        // reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}