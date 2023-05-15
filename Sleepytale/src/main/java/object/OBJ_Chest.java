package object;

import entity.Entity;
import org.game.GamePanel;

/**
 * Класс объекта "сундук"
 * Нужен для создания объектов этого класа
 * Устанавливает картинку объекта и масштабирует ее при помощи uTool'а
 * По сути, это обычный сундук, который должен стоять где-то на карте и хранить что-то в себе
 */
public class OBJ_Chest extends Entity {

    public OBJ_Chest(GamePanel gp) {

        super(gp);

        name = "Chest";
        down1 = setup("/objects/chest");
    }
}
