package org.game;

public class EventHandler {
    GamePanel gp;
    EventRect[][] eventRect;
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = gp.tileSize;
            eventRect[col][row].height = gp.tileSize;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent() {

        // Проверяем, находится ли игрок на расстроянии 1 плитки от последнего ивента
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize) {
            canTouchEvent = true;
        }

        // Ивенты
        if(canTouchEvent) {
            if(hit(27,16,"right")) {damagePit(27, 16, gp.dialogueState);}
            if(hit(23, 12, "up")) {healingPool(23, 12, gp.dialogueState);}
        }
    }

    /**
     * Метод, проверяющий, находится ли игрок на плитке с квадратом, который вызывает ивент
     * Также метод проверяет, куда смотрит игрок(таким образом, можно реализовать триггер ивента,
     * только если игрок смотрит в правильную сторону и находится на желаемой плитке)
     */
    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    /**
     * Метод, который по своей сути является ивентом, наносящим урон игроку
     * Игрок должен встать на определенную плитку, которая нанесет ему урон
     */
    public void damagePit(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit..";
        gp.player.life -= 1;
        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }

    /**
     * Метод, который по своей сути является ивентом, пополняющим очки здоровья игрока
     * Игрок должен подойти к определенной плитке, нажать на кнопку, после чего его здоровье станет максимальным
     */
    public void healingPool(int col, int row, int gameState) {
        if(gp.keyH.ePressed) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drink the water. You've gained some hp!";
            gp.player.life = gp.player.maxLife;
        }
    }
}
