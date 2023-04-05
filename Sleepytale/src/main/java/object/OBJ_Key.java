package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * Класс ключ
 * Нужен для создания объектов этого класса
 */
public class OBJ_Key extends SuperObject{
    public OBJ_Key() {
        name = "Key";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
