package org.game;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

/**
 * Этот класс создаёт объекты и назначает координаты их положения на карте
 */
public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 23 * GamePanel.TILE_SIZE;
        gp.obj[0].worldY = 7 * GamePanel.TILE_SIZE;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 23 * GamePanel.TILE_SIZE;
        gp.obj[1].worldY = 40 * GamePanel.TILE_SIZE;

        gp.obj[2] = new OBJ_Key();
        gp.obj[2].worldX = 38 * GamePanel.TILE_SIZE;
        gp.obj[2].worldY = 8 * GamePanel.TILE_SIZE;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 10 * GamePanel.TILE_SIZE;
        gp.obj[3].worldY = 11 * GamePanel.TILE_SIZE;

        gp.obj[4] = new OBJ_Door();
        gp.obj[4].worldX = 8 * GamePanel.TILE_SIZE;
        gp.obj[4].worldY = 28 * GamePanel.TILE_SIZE;

        gp.obj[5] = new OBJ_Door();
        gp.obj[5].worldX = 12 * GamePanel.TILE_SIZE;
        gp.obj[5].worldY = 22 * GamePanel.TILE_SIZE;

        gp.obj[6] = new OBJ_Chest();
        gp.obj[6].worldX = 10 * GamePanel.TILE_SIZE;
        gp.obj[6].worldY = 7 * GamePanel.TILE_SIZE;

        gp.obj[7] = new OBJ_Boots();
        gp.obj[7].worldX = 37 * GamePanel.TILE_SIZE;
        gp.obj[7].worldY = 42 * GamePanel.TILE_SIZE;
    }
}
