package org.game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Класс для различных функций(например, масштабирование изображения)
 */
public class UtilityTool {
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {

        BufferedImage scaledImage = new BufferedImage(width, height, 2); // imageType равен 2, так как original.getType() вызывал странную ошибку
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }
}
