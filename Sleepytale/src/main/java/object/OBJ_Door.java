package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * Класс дверь
 * Нужен для создания объектов этого класса
 */
public class OBJ_Door extends SuperObject{
    public OBJ_Door() {
        name = "Door";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
