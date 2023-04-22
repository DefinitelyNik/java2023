package org.game;

import entity.NPC_OldMan;

/**
 * Этот класс создаёт объекты и назначает координаты их положения на карте
 */
public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        //Тут будут новые объекты
    }

    public void setNPC() {
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;
    }
}
