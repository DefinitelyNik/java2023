package object;

import org.game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Родительский класс для всех объектов игры
 * Расставляет объекты по карте и рисует их модели
 */
public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,64,64); // Зона коллизии объекта (64х64 пикселя)
    public int solidAreaDefaultX = 0;
    public int getSolidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + GamePanel.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
                worldX - GamePanel.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
                worldY + GamePanel.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
                worldY - GamePanel.TILE_SIZE < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        }
    }
}
