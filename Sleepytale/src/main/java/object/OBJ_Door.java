package object;

import entity.Entity;
import org.game.GamePanel;

/**
 * Класс объекта "дверь"
 * Нужен для создания объектов этого класа
 * Устанавливает картинку объекта и масштабирует ее при помощи uTool'а
 * По сути, это обычная дверь, которая стоит на карте и изчезает, если к ней подходит игрок с ключом
 */
public class OBJ_Door extends Entity
{

    public OBJ_Door(GamePanel gp) {

        super(gp);

        name = "Door";
        down1 = setup("/objects/door");
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
