package object;

import entity.Entity;
import org.game.GamePanel;

/**
 * Класс объекта "ботинок"
 * Нужен для создания объектов этого класа
 * Устанавливает картинку объекта и масштабирует ее при помощи uTool'а
 * По сути, это предмет, который даёт дополнительные статы игроку при его получении
 */
public class OBJ_Boots extends Entity {

    public OBJ_Boots(GamePanel gp) {

        super(gp);

        name = "Boots";
        down1 = setup("/objects/boots");
    }
}
