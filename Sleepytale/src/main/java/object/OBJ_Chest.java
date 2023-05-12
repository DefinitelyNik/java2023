package object;

import org.game.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * Класс объекта "сундук"
 * Нужен для создания объектов этого класа
 * Устанавливает картинку объекта и масштабирует ее при помощи uTool'а
 * По сути, это обычный сундук, который должен стоять где-то на карте и хранить что-то в себе
 */
public class OBJ_Chest extends SuperObject{

    GamePanel gp;

    public OBJ_Chest(GamePanel gp) {

        this.gp = gp;

        name = "Chest";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
