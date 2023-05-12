package object;

import org.game.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * Класс объекта "ботинок"
 * Нужен для создания объектов этого класа
 * Устанавливает картинку объекта и масштабирует ее при помощи uTool'а
 * По сути, это предмет, который даёт дополнительные статы игроку при его получении
 */
public class OBJ_Boots extends SuperObject {

    GamePanel gp;

    public OBJ_Boots(GamePanel gp) {

        this.gp = gp;

        name = "Boots";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
