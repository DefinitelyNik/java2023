package object;

import org.game.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * Класс объекта "ключ"
 * Нужен для создания объектов этого класа
 * Устанавливает картинку объекта и масштабирует ее при помощи uTool'а
 * По сути, это ключ, который должен лежать где-то на карте.
 * Его может поднять игрок для открытия дверей или сундуков
 */
public class OBJ_Key extends SuperObject{
    GamePanel gp;

    public OBJ_Key(GamePanel gp) {

        this.gp = gp;

        name = "Key";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
