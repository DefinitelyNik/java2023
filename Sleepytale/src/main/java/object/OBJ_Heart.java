package object;

import org.game.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * Класс объекта "сердце"
 * Нужен для создания объектов этого класа
 * Устанавливает картинку объекта и масштабирует ее при помощи uTool'а
 * У здоровья есть 3 картинки(пустое сердце, половинка сердца, полное сердце)
 * По сути, это здоровье игрока, которое отображается на экране
 */
public class OBJ_Heart extends SuperObject{

    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {

        this.gp = gp;

        name = "Heart";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_full.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_half.png")));
            image3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_blank.png")));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
