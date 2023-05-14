package org.game;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect; // квадрат который будет указывать точку триггера ивента
    int eventRectDefaultX, eventRectDefaultY; // деволтные координаты x и y lkz eventRect

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = gp.tileSize;
        eventRect.height = gp.tileSize;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {
        if(hit(27,16,"right")) {damagePit(gp.dialogueState);}
        if (hit(23, 12, "up")) {healingPool(gp.dialogueState);}
    }

    /**
     * Метод, проверяющий, находится ли игрок на плитке с квадратом, который вызывает ивент
     * Также метод проверяет, куда смотрит игрок(таким образом, можно реализовать триггер ивента,
     * только если игрок смотрит в правильную сторону и находится на желаемой плитке)
     */
    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol * gp.tileSize + eventRect.x;
        eventRect.y = eventRow * gp.tileSize + eventRect.y;

        if(gp.player.solidArea.intersects(eventRect)) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    /**
     * Метод, который по своей сути является ивентом, наносящим урон игроку
     * Игрок должен встать на определенную плитку, которая нанесет ему урон
     */
    public void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit..";
        gp.player.life -= 1;
    }

    /**
     * Метод, который по своей сути является ивентом, пополняющим очки здоровья игрока
     * Игрок должен подойти к определенной плитке, нажать на кнопку, после чего его здоровье станет максимальным
     */
    public void healingPool(int gameState) {
        if(gp.keyH.ePressed) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drink the water. You've gained some hp!";
            gp.player.life = gp.player.maxLife;
        }
    }
}
