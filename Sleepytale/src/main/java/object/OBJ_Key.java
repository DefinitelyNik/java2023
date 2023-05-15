package object;

import entity.Entity;
import org.game.GamePanel;

/**
 * Класс объекта "ключ"
 * Нужен для создания объектов этого класа
 * Устанавливает картинку объекта и масштабирует ее при помощи uTool'а
 * По сути, это ключ, который должен лежать где-то на карте.
 * Его может поднять игрок для открытия дверей или сундуков
 */
public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gp) {
        super(gp);

        name = "Key";
        down1 = setup("/objects/key");
    }
}
