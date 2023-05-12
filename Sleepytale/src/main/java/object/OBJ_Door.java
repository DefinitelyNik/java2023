package object;

import org.game.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * Класс объекта "дверь"
 * Нужен для создания объектов этого класа
 * Устанавливает картинку объекта и масштабирует ее при помощи uTool'а
 * По сути, это обычная дверь, которая стоит на карте и изчезает, если к ней подходит игрок с ключом
 */
public class OBJ_Door extends SuperObject{

    GamePanel gp;

    public OBJ_Door(GamePanel gp) {

        this.gp = gp;

        name = "Door";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
